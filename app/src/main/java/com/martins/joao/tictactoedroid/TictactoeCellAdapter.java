package com.martins.joao.tictactoedroid;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.martins.joao.tictactoedroid.exception.FullCellException;
import com.martins.joao.tictactoedroid.model.TictactoeBoard;

/**
 * Created by João on 12/02/2017.
 */

public class TictactoeCellAdapter extends RecyclerView.Adapter<TictactoeCellAdapter.TictactoeCellViewHolder>{

    private static final String TAG = TictactoeCellAdapter.class.getSimpleName();

    GameGridAdapterOnClickHandler clickHandler;
    TictactoeBoard gameBoard;

    TictactoeCellAdapter(GameGridAdapterOnClickHandler clickHandler, TictactoeBoard gameBoard) {
        this.clickHandler = clickHandler;
        this.gameBoard = gameBoard;
    }

    public void setGameBoard(TictactoeBoard gameBoard) {
        this.gameBoard = gameBoard;
        notifyDataSetChanged();
    }

    @Override
    public TictactoeCellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.game_grid_item, parent, false);
        return new TictactoeCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TictactoeCellViewHolder holder, int position) {
        holder.bind(position);
    }

    public void cellClicked(int position) {
        try {
            gameBoard.play(position);
            notifyItemChanged(position);
            clickHandler.onClick(gameBoard.getGameState());
        } catch (FullCellException e) {
            clickHandler.onClick(null);
        }
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class TictactoeCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mCellOwnerTextView;

        public TictactoeCellViewHolder(View itemView) {
            super(itemView);
            mCellOwnerTextView = (TextView) itemView.findViewById(R.id.tv_cell_owner);

            itemView.setOnClickListener(this);
        }

        public void bind(int position) {
            switch(gameBoard.getCellOwner(position)) {
                case NONE:
                    mCellOwnerTextView.setText("");
                    break;
                case X:
                    mCellOwnerTextView.setText("X");
                    break;
                case O:
                    mCellOwnerTextView.setText("O");
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            int currentPosition = getAdapterPosition();
            TictactoeCellAdapter.this.cellClicked(currentPosition);
        }
    }

    public interface GameGridAdapterOnClickHandler {
        void onClick(TictactoeBoard.GameState state);
    }
}

