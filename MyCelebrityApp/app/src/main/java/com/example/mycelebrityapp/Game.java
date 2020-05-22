package com.example.mycelebrityapp;

import java.util.Locale;

class Game
{
    private int questionNumber;
    private int score;
    private Question[] questions;

    Game(Question[] questions)
    {
        questionNumber = 0;
        score = 0;

        this.questions = questions;
    }

    String getScore()
    {
        return String.format(Locale.ENGLISH, "Score: %d/%d", score, questions.length);
    }

    Question next()
    {
        return questions[questionNumber++];
    }

    void updateScore(boolean correct)
    {
        if(correct) score++;
    }

    int count()
    {
        return questions.length;
    }
}
