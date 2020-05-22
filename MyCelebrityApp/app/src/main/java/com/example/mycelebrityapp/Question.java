package com.example.mycelebrityapp;

import android.graphics.Bitmap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Question
{
    private String celebrityName;
    private Bitmap celebrityImage;
    private String[] possibleNames;

    Question(String celebrityName, Bitmap celebrityImage, String[] possibleNames)
    {
        this.celebrityName = celebrityName;
        this.celebrityImage = celebrityImage;
        this.possibleNames = possibleNames;
    }

    boolean check(String guess)
    {
        return guess.equals(celebrityName);
    }

    Bitmap getCelebrityImage()
    {
        return celebrityImage;
    }

    String[] getPossibleNames()
    {
        List<String> shuffleList = Arrays.asList(possibleNames);
        Collections.shuffle(shuffleList);
        return shuffleList.toArray(new String[0]);
    }
}
