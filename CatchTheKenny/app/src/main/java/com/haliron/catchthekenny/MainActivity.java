package com.haliron.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    TextView textTime;
    TextView textScore;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    ImageView[] imageArray;
    int score;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTime = findViewById(R.id.textTime);
        textScore = findViewById(R.id.textScore);
        image1 = findViewById(R.id.imageView);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);
        image5 = findViewById(R.id.imageView5);
        image6 = findViewById(R.id.imageView6);
        image7 = findViewById(R.id.imageView7);
        image8 = findViewById(R.id.imageView8);
        image9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]
                {
                   image1,
                   image2,
                   image3,
                   image4,
                   image5,
                   image6,
                   image7,
                   image8,
                   image9
                };

        hideImage();

        score = 0;

        new CountDownTimer(11000,1000)
        {
            @Override
            public void onTick(long l)
            {
                textTime.setText("Time : " + l / 1000);

            }

            @Override
            public void onFinish()
            {
                textTime.setText("Time Over !");

                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Game Over !");
                alert.setMessage("Do you want restart game ?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        MainActivity.this.finish();
                    }
                });
                alert.show();
            }
        }.start();
    }

    public void score(View view)
    {
        score++;
        textScore.setText("Score : " + score);
    }

    public  void hideImage()
    {
        handler = new Handler();
        runnable = new Runnable()
        {
            @Override
            public void run()
            {
                for (ImageView image : imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(10);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);
    }
}