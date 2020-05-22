package com.example.mycelebrityapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.angmarch.views.NiceSpinner;

import java.util.Objects;

public class GameFragment extends Fragment

{
    private Difficulty level;

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
    }

    Difficulty getLevel() { return  level; }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_game, container, false);
        final NiceSpinner spinner = view.findViewById(R.id.spDifficulty);

        view.findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selection = spinner.getSelectedItem().toString();
                level = Difficulty.valueOf(selection.toUpperCase());
                ((MainActivity) Objects.requireNonNull(getActivity())).setSettings(State.START_GAME);
            }
        });

        return view;
    }

}
