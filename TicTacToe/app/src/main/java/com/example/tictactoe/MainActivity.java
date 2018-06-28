package com.example.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    ImageButton mCellOneButton;
    ImageButton mCellTwoButton;
    ImageButton mCellThreeButton;
    ImageButton mCellFourButton;
    ImageButton mCellFiveButton;
    ImageButton mCellSixButton;
    ImageButton mCellSevenButton;
    ImageButton mCellEightButton;
    ImageButton mCellNineButton;

    TextView mResultsText;

    Button mPlayButton;

    CellPlayedStatus[] mCellPlayedStates = new CellPlayedStatus[9];

    enum GameState {
      GAME_OVER, GAME_WON, NOUGHT_TURN, CROSS_TURN
    };

    GameState mCurrentGameState = GameState.GAME_OVER;

    int goCount = 0;  // do not allow more than 9

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // listeners for each button
        mCellOneButton = findViewById(R.id.cellOneImageButton);
        mCellOneButton.setOnClickListener(this);
        setupCellButton(mCellOneButton);

        mCellTwoButton = findViewById(R.id.cellTwoImageButton);
        mCellTwoButton.setOnClickListener(this);
        setupCellButton(mCellTwoButton);

        mCellThreeButton = findViewById(R.id.cellThreeImageButton);
        mCellThreeButton.setOnClickListener(this);
        setupCellButton(mCellThreeButton);

        mCellFourButton = findViewById(R.id.cellFourImageButton);
        mCellFourButton.setOnClickListener(this);
        setupCellButton(mCellFourButton);

        mCellFiveButton = findViewById(R.id.cellFiveImageButton);
        mCellFiveButton.setOnClickListener(this);
        setupCellButton(mCellFiveButton);

        mCellSixButton = findViewById(R.id.cellSixImageButton);
        mCellSixButton.setOnClickListener(this);
        setupCellButton(mCellSixButton);

        mCellSevenButton = findViewById(R.id.cellSevenImageButton);
        mCellSevenButton.setOnClickListener(this);
        setupCellButton(mCellSevenButton);

        mCellEightButton = findViewById(R.id.cellEightImageButton);
        mCellEightButton.setOnClickListener(this);
        setupCellButton(mCellEightButton);

        mCellNineButton = findViewById(R.id.cellNineImageButton);
        mCellNineButton.setOnClickListener(this);
        setupCellButton(mCellNineButton);

        mPlayButton = findViewById(R.id.playButton);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  clear board
                clearBoard();

                mPlayButton.setVisibility(View.INVISIBLE);
                setResultText("");

                mCurrentGameState = GameState.NOUGHT_TURN;
            }
        });

        mResultsText = findViewById(R.id.resultsTextView);
        clearBoard();

        createCellState();
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.cellOneImageButton:
                Log.d(TAG, "onClick cell 1");
                updateCellAsPlayed(1, mCellOneButton);
                break;
            case R.id.cellTwoImageButton:
                Log.d(TAG, "onClick cell 2");
                updateCellAsPlayed(2, mCellTwoButton);
                break;
            case R.id.cellThreeImageButton:
                Log.d(TAG, "onClick cell 3");
                updateCellAsPlayed(3, mCellThreeButton);
                break;
            case R.id.cellFourImageButton:
                Log.d(TAG, "onClick cell 4");
                updateCellAsPlayed(4, mCellFourButton);
                break;
            case R.id.cellFiveImageButton:
                Log.d(TAG, "onClick cell 5");
                updateCellAsPlayed(5, mCellFiveButton);
                break;
            case R.id.cellSixImageButton:
                Log.d(TAG, "onClick cell 6");
                updateCellAsPlayed(6, mCellSixButton);
                break;
            case R.id.cellSevenImageButton:
                Log.d(TAG, "onClick cell 7");
                updateCellAsPlayed(7, mCellSevenButton);
                break;
            case R.id.cellEightImageButton:
                Log.d(TAG, "onClick cell 8");
                updateCellAsPlayed(8, mCellEightButton);
                break;
            case R.id.cellNineImageButton:
                Log.d(TAG, "onClick cell 9");
                updateCellAsPlayed(9, mCellNineButton);
                break;
        }

    }

    void updateCellAsPlayed(int cellNumber, ImageButton cellButton) {
        Log.d(TAG, "image button" + cellNumber + " pressed");

        if (mCurrentGameState != GameState.GAME_OVER) {
            switch (mCellPlayedStates[cellNumber-1].getCurrentState()) {
                case NOT_PLAYED:
                    Log.d(TAG, "Not Played");

                    GameState stateBeforMove = mCurrentGameState;
                    if (mCurrentGameState == GameState.NOUGHT_TURN) {
                        cellButton.setImageResource(R.drawable.nought);
                        mCellPlayedStates[cellNumber - 1].setCurrentState(CellPlayedStatus.CellStatus.NOUGHT_PLAYED);
                        mCurrentGameState = GameState.CROSS_TURN;
                    }
                    else {
                        cellButton.setImageResource(R.drawable.cross);
                        mCellPlayedStates[cellNumber - 1].setCurrentState(CellPlayedStatus.CellStatus.CROSS_PLAYED);
                        mCurrentGameState = GameState.NOUGHT_TURN;
                    }

                    // check for winner
                    checkForWinner(cellNumber);
                    if (mCurrentGameState == GameState.GAME_WON) {
                        String winnerText;

                        if (stateBeforMove == GameState.NOUGHT_TURN) {
                            winnerText = "Nought won";
                        } else {
                            winnerText = "cross won";
                        }
                        setResultText(winnerText);
                    }
                    else {
                        goCount++;

                        if (goCount == 9) {
                            // no winners
                        }
                    }
                    break;
                case CROSS_PLAYED:
                    Log.d(TAG, "cross played");
                    break;
                case NOUGHT_PLAYED:
                    Log.d(TAG, "nought played");
                    break;
            }
        }
        else {

        }
    }

    void checkForWinner(int cellNumber) {

    }

    void setupCellButton(ImageButton cellButton) {
        cellButton.setVisibility(View.VISIBLE);
        cellButton.setBackgroundColor(Color.TRANSPARENT);

        cellButton.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    void setResultText(String text) {
        mResultsText.setText(text);
        mResultsText.setGravity(Gravity.CENTER);
    }

    void clearBoard() {
        hideButton(mCellOneButton);
        hideButton(mCellTwoButton);
        hideButton(mCellThreeButton);
        hideButton(mCellFourButton);
        hideButton(mCellFiveButton);
        hideButton(mCellSixButton);
        hideButton(mCellSevenButton);
        hideButton(mCellEightButton);
        hideButton(mCellNineButton);
    }

    void hideButton(ImageButton cellButton) {
        cellButton.setImageResource(android.R.color.transparent);
    }

    void createCellState(){
        for (int i = 0; i < 9; i++) {
            mCellPlayedStates[i] = new CellPlayedStatus(CellPlayedStatus.CellStatus.NOT_PLAYED);
        }
    }

    void resetCellStates() {
        for (int i = 0; i < 9; i++) {
            mCellPlayedStates[i].setCurrentState(CellPlayedStatus.CellStatus.NOT_PLAYED);
        }
    }
}
