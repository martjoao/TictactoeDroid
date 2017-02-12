package com.martins.joao.tictactoedroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by João on 12/02/2017.
 */

public class TictactoeCellAdapter extends RecyclerView.Adapter<TictactoeCellAdapter.TictactoeCellViewHolder>{


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

    @Override
    public int getItemCount() {
        return 9;
    }

    public class TictactoeCellViewHolder extends RecyclerView.ViewHolder {

        private TextView mCellOwnerTextView;

        public TictactoeCellViewHolder(View itemView) {
            super(itemView);
            mCellOwnerTextView = (TextView) itemView.findViewById(R.id.tv_cell_owner);
        }

        public void bind(int position) {
            mCellOwnerTextView.setText(String.valueOf(position));
        }
    }
}

