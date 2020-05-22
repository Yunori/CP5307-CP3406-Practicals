package com.example.mycelebrityapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class StatusFragment extends Fragment
{
    private TextView message;
    private TextView score;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        View view =  inflater.inflate(R.layout.fragment_status, container, false);

        message = view.findViewById(R.id.message);
        score = view.findViewById(R.id.score);

        return view;
    }

    void setMessage(String text) { message.setText(text); }

    void setScore(String text) { score.setText(text); }

}
