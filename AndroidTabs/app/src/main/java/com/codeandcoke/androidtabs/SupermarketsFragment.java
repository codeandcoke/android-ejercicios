package com.codeandcoke.androidtabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupermarketsFragment extends Fragment {

    public SupermarketsFragment() {
    }

    /**
     * Alternativa al constructor con paso de parámetros
     * @param position
     * @return
     */
    public static SupermarketsFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        SupermarketsFragment fragment = new SupermarketsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Recogida de parámetros
        int position = getArguments().getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supermarkets, container, false);
        return view;
    }

}
