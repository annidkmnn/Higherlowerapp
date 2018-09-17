package com.example.annikadiekmann.higher_lowerapp;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Local Variables

    private ImageView mImageView;
    private int currentImageIndex = 0;
    private int[] mImageNames;

    private ArrayAdapter mAdapter;
    private ListView mListView;

    private TextView mScoretext;
    private int mScore = 0;
    private TextView mHighscoreText;
    private int mHighscore = 0;

    private List<String> mThrows;

    private FloatingActionButton mhigherButton;
    private FloatingActionButton mlowerButton;
    private Random r;
    String message;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables
        mImageView= findViewById(R.id.imageView);

        mListView=findViewById(R.id.listView_main);

        mHighscoreText=findViewById(R.id.highscoreText_maim);
        mScoretext=findViewById(R.id.scoreText_main);

        mhigherButton=findViewById(R.id.higherButton);
        mlowerButton=findViewById(R.id.lowerButton);

        mThrows = new ArrayList<>();

        mImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

        r = new Random ();


        // Define what happens when the user clicks the "higher" button
        mhigherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentImageIndex = r.nextInt(mImageNames.length);
                mImageView.setImageResource(mImageNames[currentImageIndex]);
                addToThrowlist();

                if (currentImageIndex > 2) {
                    win();
                }else {
                    lose();
                }

            }
        });

        // Define what happens when the user clicks the "lower" button
        mlowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                currentImageIndex = r.nextInt(mImageNames.length);
                mImageView.setImageResource(mImageNames[currentImageIndex]);
                addToThrowlist();

                if (currentImageIndex <= 2) {
                    win();
                }else {
                    lose();
                }

            }
        });
    }

    private void addToThrowlist(){
        int diceNumber = currentImageIndex+1;
        mThrows.add("You throw " + diceNumber + " !");
        updateUI();
    }

    //Display win message.
    private void win(){
        mScore++;
        mScoretext.setText("Score: "+mScore);
        message = "Great!";
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    //Display lose message and update Highscore.
    private void lose(){

        //Update Highscore
        if(mScore>mHighscore){
            mHighscoreText.setText("Highscore: "+mScore);
        }

        //Reset Score & Message
        mScore=0;
        mScoretext.setText("Score: "+mScore);
        message = "Wrong!";
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mThrows);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

}
