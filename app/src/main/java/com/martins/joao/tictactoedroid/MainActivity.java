package com.martins.joao.tictactoedroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.martins.joao.tictactoedroid.model.TictactoeBoard;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements TictactoeCellAdapter.GameGridAdapterOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView mGameGridRecyclerView;
    TictactoeCellAdapter mGameAdapter;
    TextView mGameStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameStatusTextView   = (TextView) findViewById(R.id.tv_game_status);
        mGameGridRecyclerView = (RecyclerView) findViewById(R.id.rv_game_grid);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3); //3 columns per row
        mGameGridRecyclerView.setLayoutManager(layoutManager);
        mGameGridRecyclerView.setHasFixedSize(true);

        mGameAdapter = new TictactoeCellAdapter(this, new TictactoeBoard());
        mGameGridRecyclerView.setAdapter(mGameAdapter);
    }

    @Override
    public void onClick(TictactoeBoard.GameState state) {
        if (state == null) {
            Toast.makeText(this, "Cell is not empty", Toast.LENGTH_LONG).show();
        }
        else {
            switch (state) {
                case X_TURN:
                    mGameStatusTextView.setText(getString(R.string.turn_x));
                    break;
                case O_TURN:
                    mGameStatusTextView.setText(getString(R.string.turn_o));
                    break;
                case X_WINS:
                    mGameStatusTextView.setText(getString(R.string.win_x));
                    break;
                case O_WINS:
                    mGameStatusTextView.setText(getString(R.string.win_o));
                    break;
                case TIE:
                    mGameStatusTextView.setText(getString(R.string.tie));
                    break;
            }
        }
    }
}
