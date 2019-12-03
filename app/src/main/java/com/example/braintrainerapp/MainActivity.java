package com.example.braintrainerapp;

import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button startButton,button1,button2,button3,button4,playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView,pointsTextView,sumTextView,timerTextView;
    RelativeLayout gameRelativeLayout;

    int score = 0;
    int total = 0;

    int locationOfCorrectAnswer;

    public void playAgain(View view){

        score = 0;
        total = 0;
        timerTextView.setText("30s");
        sumTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Your Score: "+Integer.toString(score)+"/"+Integer.toString(total));

            }
        }.start();
    }

    public void generateQuestion()
    {
        Random rand = new Random();

        int a = rand.nextInt(101);
        int b = rand.nextInt(101);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        for(int i =0;i<4;i++){

            if(i == locationOfCorrectAnswer){

                answers.add(a+b);

            }
            else{

                incorrectAnswer = rand.nextInt(201);

                while(incorrectAnswer == a+b){

                    incorrectAnswer = rand.nextInt(201);

                }

                answers.add(incorrectAnswer);

            }

        }
        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view){

        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){

            score++;
            resultTextView.setText("Correct!");

        }
        else{

            resultTextView.setText("Incorrect!");

        }
        total++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(total));
        generateQuestion();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.startButton);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton= (Button) findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);

    }
}
