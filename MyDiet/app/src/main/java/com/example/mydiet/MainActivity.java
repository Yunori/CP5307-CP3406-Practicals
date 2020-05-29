package com.example.mydiet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class MainActivity  extends AppCompatActivity {
    ImageView image;
    TextView textview;
    MaterialButton roll;
    int[] photos;
    int[] texts;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photos = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3};
        texts = new int[]{R.string.msg1, R.string.msg2, R.string.msg3};
        setupUI();
        roll();
    }

    private void setupUI() {
        roll = findViewById(R.id.roll);
        image = findViewById(R.id.imgRandom);
        textview = findViewById(R.id.msgRandom);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll.setEnabled(false);
                roll();
            }
        });
    }

    private void roll() {
        Random rand = new Random();
        Bitmap bImage = BitmapFactory.decodeResource(this.getResources(), photos[rand.nextInt(photos.length)]);
        image.setImageBitmap(bImage);
        textview.setText(texts[rand.nextInt(texts.length)]);
        roll.setEnabled(true);
    }
}
