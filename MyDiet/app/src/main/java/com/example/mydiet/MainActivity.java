package com.example.mydiet;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity  extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] photos={R.drawable.img1, R.drawable.img2,R.drawable.img3};
        int[] texts={R.string.msg1, R.string.msg2,R.string.msg3};

        ImageView image = (ImageView) findViewById(R.id.imgRandom);
        TextView textview = (TextView) findViewById(R.id.msgRandom);

        Random rand=new Random();
        image.setImageResource(photos[rand.nextInt(photos.length)]);

        textview.setText(texts[rand.nextInt(texts.length)]);

    }
}
