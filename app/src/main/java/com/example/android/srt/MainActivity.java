package com.example.android.srt;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score = 100; //Value for the Players Score
    boolean answer = true; //creates boolean for the corret answer true = right, false = wrong
    int questionCounter = 1; //Chooses question

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
        if(checkBox.isChecked()){
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
        if(checkBox.isChecked()){
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
         if((hasPressedRight) && (hasPressedWrong)){
             return;
         }
        if((!hasPressedRight) && (!hasPressedWrong)){
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

    /**
     * Changes to the next question
     */
    public void loadQuestion(){
        int color = 0;
        TextView textView = (TextView) findViewById(R.id.question);
        LinearLayout bgElement = (LinearLayout) findViewById(R.id.activity_main);
        String question = getString(R.string.questionStart);
        switch (questionCounter){

            case 1:  question = getString(R.string.question1);
                answer = true;
                color = ContextCompat.getColor(this, R.color.orange);
                break;
            case 2: question = getString(R.string.question2);
                color = ContextCompat.getColor(this, R.color.blue);
                answer = false;
                break;
            case 3: question = getString(R.string.question3);
                color = ContextCompat.getColor(this, R.color.yellow);
                answer = true;
                break;
            case 4: question = getString(R.string.question4);
                color = ContextCompat.getColor(this, R.color.red);
                answer = true;
                break;
            default: question = getString(R.string.questionEnd);
                color = ContextCompat.getColor(this, R.color.blue_gray);
                answer = true;
                questionCounter = 0;
                break;
        }
        questionCounter = questionCounter + 1;
        textView.setText(question);
        bgElement.setBackgroundColor(color);
    }
    public void displayScore(){
        if(score <= 0){
            return;
        }
        TextView scoreView = (TextView) findViewById(R.id.score);
        String scoreString = getString(R.string.plus)+String.valueOf(score);
        scoreView.setText(scoreString);
    }
}

