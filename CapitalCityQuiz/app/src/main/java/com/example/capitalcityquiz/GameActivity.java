package com.example.capitalcityquiz;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity {

    QuizQuestion[] mQuestion = new QuizQuestion[5];

    int mNumberOfQuestions = 0;
    int mNumberSkippedAnswers = 0;
    int mNumberCorrectAnswers = 0;
    int mNumberOfWrongAnswers = 0;
    int mCurrentQuestionIndex = 0;

    TextView mScoreTextView;
    TextView mQuestionTextView;
    TextView mProgressTextView;
    Button mPlayAgainButton;
    Button mAbortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initialiseQuestions();

        mQuestionTextView = findViewById(R.id.questionTextView);
        mProgressTextView = findViewById(R.id.progressTextView);

        updateQuestion();
        Button yesButton = findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        Button noButton = findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        Button skipButton = findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handelQuestionSkipped();
            }
        });

        mAbortButton = findViewById(R.id.abortButton);
        mAbortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopQuiz();
            }
        });

        mPlayAgainButton = findViewById(R.id.playAgainButton);
        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion();
                updateProgress();
                updateScores(false);
                hidePlayAgainButton();
                enableAbortButton();
            }
        });
        mScoreTextView = findViewById(R.id.scoreTextView);

        updateProgress();
        updateScores(false);

        hidePlayAgainButton();
        enableAbortButton();
    }

    void initialiseQuestions() {
        mQuestion[0] = new QuizQuestion("Is Paris the capital of France?", true );

        mQuestion[1] = new QuizQuestion("Is Barcelona the capital of Spain?", false );

        mQuestion[2] = new QuizQuestion("Lisbon is the capital of Sweden",false);

        mQuestion[3] = new QuizQuestion("Is Brussels a city in Belgium",true);

        mQuestion[4] = new QuizQuestion("Is New York the capital of the US",false);


        mNumberOfQuestions = mQuestion.length;
    }

    void checkAnswer(boolean userAnswered) {
        if (mQuestion[mCurrentQuestionIndex].getAnswer() == userAnswered) {
            mNumberCorrectAnswers++;
        }
        else {
            mNumberOfWrongAnswers++;
        }
        moveToNextQuestion();
    }

    void moveToNextQuestion() {
        mCurrentQuestionIndex++;
        if (mCurrentQuestionIndex > (mNumberOfQuestions -1)) {
            updateScores(true);
            newGame();
        }
        else {
            updateQuestion();
            updateProgress();
            updateScores(false);
        }

    }

    void updateScores(boolean quizOver) {
        String scoreText;
        if (quizOver) {
            scoreText = "Final Score";
        }
        else {
            scoreText = "Current Score";
        }
        scoreText = scoreText + "\n\n" + "Correct Answers = " + Integer.toString(mNumberCorrectAnswers) +
            "\n" + "Wrong Answers = " + Integer.toString(mNumberOfWrongAnswers)+
            "\n" + "Skipped Questions = " + Integer.toString(mNumberSkippedAnswers);
        mScoreTextView.setText(scoreText);
    }

    void updateQuestion() {
        mQuestionTextView.setText(mQuestion[mCurrentQuestionIndex].getQuestion());
    }

    void updateProgress() {
        String progress = "Question: " + Integer.toString(mCurrentQuestionIndex + 1) + "/" +
                Integer.toString(mNumberOfQuestions);
        mProgressTextView.setText(progress);
    }

    void newGame() {
        mNumberCorrectAnswers = 0;
        mNumberOfWrongAnswers = 0;
        mNumberSkippedAnswers = 0;
        mCurrentQuestionIndex = 0;
        showPlayAgainButton();
        disableAbortButton();
    }

    void handelQuestionSkipped() {
        mNumberSkippedAnswers++;
        moveToNextQuestion();
    }

    void stopQuiz() {
        // Make sure user wants to quit
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int choice) {
                switch (choice) {
                    case DialogInterface.BUTTON_POSITIVE:
                        String abortText = getResources().getString(R.string.quiz_aborted);
                        mScoreTextView.setText(abortText);
                        newGame();

                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setMessage(getResources().getString(R.string.abort_confirmation))
                .setPositiveButton(getResources().getString(R.string.yes), dialogClickListener)
                .setNegativeButton(getResources().getString(R.string.no), dialogClickListener).show();
    }

    void hidePlayAgainButton() {
        mPlayAgainButton.setVisibility(View.INVISIBLE);
    }
    void showPlayAgainButton() {
        mPlayAgainButton.setVisibility(View.VISIBLE);
    }

    void  enableAbortButton() {
        mAbortButton.setClickable(true);
        mAbortButton.setAlpha(1.0f);
    }
    void disableAbortButton() {
        mAbortButton.setClickable(false);
        mAbortButton.setAlpha(0.25f);
    }
}
