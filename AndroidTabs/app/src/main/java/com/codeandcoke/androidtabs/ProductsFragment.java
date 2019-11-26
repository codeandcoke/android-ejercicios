package com.codeandcoke.androidtabs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProductsFragment extends Fragment {

    public ProductsFragment() {
    }

    /**
     * Alternativa al constructor con paso de parámetros
     * @param position
     * @return
     */
    public static ProductsFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        ProductsFragment fragment = new ProductsFragment();
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
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        return view;
    }
}
