package com.example.mycelebrityapp;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.Objects;

public class QuestionFragment extends Fragment
{
    private Game currentGame;
    private Question currentQuestion;
    private int questionNumber;
    private GridView possibleAnswers;
    private ArrayAdapter<String> adapter;
    private ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_list_item_1);
        CelebrityManager celebrityManager = new CelebrityManager(Objects.requireNonNull(getContext()).getAssets(), "celebs/");
        GameBuilder gameBuilder = new GameBuilder(celebrityManager);
        Game game = gameBuilder.create((Difficulty) Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getSerializableExtra("level")));

        setGame(game);
        showNextQuestion();

    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_question, container, false);

        possibleAnswers = view.findViewById(R.id.answers);
        image = view.findViewById(R.id.image);


        possibleAnswers.setAdapter(adapter);

        possibleAnswers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
                TextView answer = (TextView) view1;
                currentGame.updateScore(currentQuestion.check(answer.getText().toString()));
                QuestionFragment.this.showNextQuestion();
            }
        });
        image.setImageBitmap(currentQuestion.getCelebrityImage());
        return view;
    }

    private void setGame(Game game)
    {
        currentGame = game;
        questionNumber = 0;
    }

    String getScore()
    {
        return currentGame.getScore();
    }

    private void showNextQuestion()
    {
        questionNumber++;
        if(questionNumber > currentGame.count())
            ((QuestionActivity) Objects.requireNonNull(getActivity())).checkState(State.GAME_OVER);
        else
        {
            ((QuestionActivity) Objects.requireNonNull(getActivity())).checkState(State.CONTINUE_GAME);
            currentQuestion = currentGame.next();
            adapter.clear();
            adapter.addAll(currentQuestion.getPossibleNames());
            if(image != null)
                image.setImageBitmap(currentQuestion.getCelebrityImage());
        }
    }

    void setVisibility(boolean isVisible)
    {
        if(isVisible)
            possibleAnswers.setVisibility(View.VISIBLE);
        else
            possibleAnswers.setVisibility(View.INVISIBLE);
    }
}
