package com.example.android.srt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.example.android.srt.R.id.question;

public class MainActivity extends AppCompatActivity {

    int score = 100; //Value for the Players Score
    boolean answer = true; //creates boolean for the corret answer true = right, false = wrong

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("score", score);
    }

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loads old score
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("score");
        }

        //sets custom toolbar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        loadQuestion();
        displayScore();

    }

    /**
     * Right method
     */
    public boolean pressedRight() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxYes);
        Boolean hasPressedRight = checkBox.isChecked();
        if (checkBox.isChecked()) {
            checkBox.toggle();
        }
        return hasPressedRight;
    }

    /**
     * Wrong method
     */
    public boolean pressedWrong() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxNo);
        Boolean hasPressedWrong = checkBox.isChecked();
        if (checkBox.isChecked()) {
            checkBox.toggle();
        }
        return hasPressedWrong;
    }

    /**
     * Compares user inputs with answer
     */
    public void next(View v) {
        boolean hasPressedRight = pressedRight();
        boolean hasPressedWrong = pressedWrong();
        if ((hasPressedRight) && (hasPressedWrong)) {
            return;
        }
        if ((!hasPressedRight) && (!hasPressedWrong)) {
            return;
        }
        if (answer) {
            if (hasPressedRight) {
                Toast.makeText(this, getString(R.string.right), Toast.LENGTH_SHORT).show();
                score = score + 10;
                loadQuestion();
            } else {
                Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                score = score - 5;
            }
        } else {
            if (hasPressedWrong) {
                Toast.makeText(this, getString(R.string.right), Toast.LENGTH_SHORT).show();
                score = score + 10;
                loadQuestion();
            } else {
                Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
                score = score - 5;
            }
        }
        displayScore();
    }

    public void displayScore() {
        if (score <= 0) {
            return;
        }
        TextView scoreView = (TextView) findViewById(R.id.score);
        String scoreString = getString(R.string.plus) + String.valueOf(score);
        scoreView.setText(scoreString);
    }


    /**
     * Changes to the next question, changes background color
     */
    public void loadQuestion() {
        TextView textView = (TextView) findViewById(question);
        LinearLayout bgElement = (LinearLayout) findViewById(R.id.activity_main);

        int[] androidColors = getResources().getIntArray(R.array.background);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        bgElement.setBackgroundColor(randomAndroidColor);

        Random r = new Random();
        int i1 = r.nextInt(5 - 1) + 1; //1 inclusive 5 exclusive
        String chooseQuestion = "question" + i1;
        String chooseAnswer = "answer" + i1;

        String getQuestion = (String) getResources().getText(getResources().getIdentifier(chooseQuestion, "string", getPackageName()));
        //Log.v("MainActivity", "String Identifier: " + getQuestion);
        String getAnswer = (String) getResources().getText(getResources().getIdentifier(chooseAnswer, "string", getPackageName()));

        textView.setText(getQuestion);
        answer = Boolean.valueOf(getAnswer);


    }
}

