package com.martins.joao.tictactoedroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView mGameGridRecyclerView;
    TictactoeCellAdapter mGameAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameGridRecyclerView = (RecyclerView) findViewById(R.id.rv_game_grid);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3); //3 columns per row
        mGameGridRecyclerView.setLayoutManager(layoutManager);
        mGameGridRecyclerView.setHasFixedSize(true);

        mGameAdapter = new TictactoeCellAdapter();
        mGameGridRecyclerView.setAdapter(mGameAdapter);
    }
}
