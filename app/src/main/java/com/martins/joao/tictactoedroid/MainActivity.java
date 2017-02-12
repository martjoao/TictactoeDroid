package com.martins.joao.tictactoedroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TictactoeCellAdapter.GameGridAdapterOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

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

        mGameAdapter = new TictactoeCellAdapter(this);
        mGameGridRecyclerView.setAdapter(mGameAdapter);
    }

    @Override
    public void onClick(int position) {
        Log.d(TAG, "ENTERING ONCLICK");
        Toast.makeText(this, "Clicked on: " + position, Toast.LENGTH_LONG).show();
    }
}
