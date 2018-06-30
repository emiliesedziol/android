package com.example.datediff;

import android.annotation.SuppressLint;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        Button mButtonDateOne;
        Button mButtonDateTwo;

        // *****************************************************
        // create overall linear layout
        // *****************************************************
        LinearLayout mainLinearLayout = new LinearLayout(this);

        // set orientation to vertical
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL);

        // set size ot parent
        LinearLayout.LayoutParams mainLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT);

        // create title text manually
        TextView titleTextView = new TextView(this);
        titleTextView.setId(1000);  // warning message disappears when '@SuppressLint*"ResourceType") is added above

        titleTextView.setText(getResources().getString(R.string.title));
        TextViewCompat.setTextAppearance(titleTextView, R.style.fontTitle);
        titleTextView.setGravity(Gravity.CENTER);

        mainLinearLayout.addView(titleTextView);

        // create buttons
        mButtonDateOne = new Button(this);
        mButtonDateOne.setText(getResources().getString(R.string.dateOneButton));
        mButtonDateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click on button one");
            }
        });
        mainLinearLayout.addView(mButtonDateOne);

        mButtonDateTwo = new Button(this);
        mButtonDateTwo.setText(getResources().getString(R.string.dateTwoButton));
        mButtonDateTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "click on button two");
            }
        });
        mainLinearLayout.addView(mButtonDateTwo);
        setContentView(mainLinearLayout);
    }
}
