package com.martins.joao.tictactoedroid.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.martins.joao.tictactoedroid.R;
import com.martins.joao.tictactoedroid.model.TictactoeBoard;
import com.martins.joao.tictactoedroid.utils.ResourceManager;

import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity implements TictactoeCellAdapter.GameGridAdapterOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mGameGridRecyclerView;
    private TictactoeCellAdapter mGameAdapter;
    private TextView mGameStatusTextView;
    private TextView mErrorMessageTextView;
    private Button mRestartButton;
    private ProgressBar mLoadImgProgressBar;
    private LinearLayout mGameLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameStatusTextView   = (TextView)      findViewById(R.id.tv_game_status);
        mErrorMessageTextView = (TextView)      findViewById(R.id.tv_error_message);
        mRestartButton        = (Button)        findViewById(R.id.b_restart_game);
        mLoadImgProgressBar   = (ProgressBar)   findViewById(R.id.pb_load_images);
        mGameGridRecyclerView = (RecyclerView)  findViewById(R.id.rv_game_grid);
        mGameLinearLayout     = (LinearLayout)  findViewById(R.id.ll_game);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3); //3 columns per row
        mGameGridRecyclerView.setLayoutManager(layoutManager);
        mGameGridRecyclerView.setHasFixedSize(true);

        mGameAdapter = new TictactoeCellAdapter(this, new TictactoeBoard());
        mGameGridRecyclerView.setAdapter(mGameAdapter);

        mRestartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.mGameAdapter.setGameBoard(new TictactoeBoard());
                mGameStatusTextView.setText(getString(R.string.turn_x));
                showGameView();
            }
        });

        new DownloadImagesTask().execute();
    }

    @Override
    public void onClick(TictactoeBoard.GameState state, String message) {
        if (state == null) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
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

    public void showGameView() {
        mErrorMessageTextView.setVisibility(View.INVISIBLE);
        mGameLinearLayout.setVisibility(View.VISIBLE);
    }

    public void showErrorMessage() {
        mGameLinearLayout.setVisibility(View.INVISIBLE);
        mErrorMessageTextView.setVisibility(View.VISIBLE);
    }


    //Poderia ter utilizado alguma lib (Picasso/Glide)
    public class DownloadImagesTask extends AsyncTask<Void, Void, Void> {

        private final String CIRCLE_URL =
                "http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons-256/glossy-black-icons-symbols-shapes/018710-glossy-black-icon-symbols-shapes-shapes-circle-frame.png";
        private final String CROSS_URL =
                "http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons-256/rounded-glossy-black-icons-alphanumeric/074203-rounded-glossy-black-icon-alphanumeric-x-styled.png";


        @Override
        protected void onPreExecute() {
            MainActivity.this.mLoadImgProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL(CIRCLE_URL);
                URLConnection conn = url.openConnection();
                Bitmap bmp = BitmapFactory.decodeStream(conn.getInputStream());
                ResourceManager.INSTANCE.addImage("circle", bmp);

                url = new URL(CROSS_URL);
                conn = url.openConnection();
                bmp = BitmapFactory.decodeStream(conn.getInputStream());
                ResourceManager.INSTANCE.addImage("cross", bmp);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            MainActivity.this.mLoadImgProgressBar.setVisibility(View.INVISIBLE);
            if (ResourceManager.INSTANCE.getImage("circle") != null
                    && ResourceManager.INSTANCE.getImage("cross") != null) {
                showGameView();
            }
            else {
                showErrorMessage();
            }
        }
    }
}
