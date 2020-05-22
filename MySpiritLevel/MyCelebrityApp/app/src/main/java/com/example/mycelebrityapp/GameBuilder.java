package com.example.mycelebrityapp;

import android.graphics.Bitmap;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class GameBuilder
{
    private static final int N_EASY = 4;
    private static final int N_MEDIUM = 6;
    private static final int N_HARD = 12;
    private static final int N_EXPERT = 24;


    private CelebrityManager celebrityManager;

    GameBuilder(CelebrityManager celebrityManager)
    {
        this.celebrityManager = celebrityManager;
    }

    Game create(Difficulty difficulty)
    {
        int N;

        switch(difficulty)
        {
            case MEDIUM:
                N = N_MEDIUM;
                break;

            case HARD:
                N = N_HARD;
                break;

            case EXPERT:
                N = N_EXPERT;
                break;

            default:
                N = N_EASY;
        }

        Question[] questions = new Question[N];
        Set<Integer> indexes = new HashSet<>();

        Random random = new Random();

        while(indexes.size() != N) indexes.add(1+random.nextInt(celebrityManager.count()-2));

        String[] celebrityNames = new String[N];

        int j = 0;

        for (int i:indexes)
        {
            celebrityNames[j] = celebrityManager.getName(i);
            j++;
        }

        j = 0;

        for (int i:indexes)
        {
            String celebrityName = celebrityManager.getName(i);
            Bitmap celebrityImage = celebrityManager.get(i);

            questions[j++] = new Question(celebrityName, celebrityImage, celebrityNames);
        }

        return new Game(questions);
    }

}

