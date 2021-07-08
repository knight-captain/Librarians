package com.example.biblio_tech_mark_3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddBooksRecyclerViewAdapter extends RecyclerView.Adapter<AddBooksRecyclerViewAdapter.ViewHolder> {

    public static final String TAG = "AddBooksRVAdapter: ";

    private List<Book> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    AddBooksRecyclerViewAdapter(Context context, List<Book> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.add_books_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AddBooksRecyclerViewAdapter.ViewHolder holder, int position) {
        Book book = mData.get(position);
        holder.myTextView.setText(book.toString());
    }

    // total number of rows
    @Override
    public int getItemCount() { return mData.size(); }

    // convenience method for getting data at click position
    Book getItem(int id) { return mData.get(id); }

    // allows clicks events to be caught
    @SuppressLint("LongLogTag")
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
        Log.i(TAG,"set" + mClickListener);
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.addBookRowName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
                // this sends the click to ViewBooksActivity.onItemClick()
            } else {
                Log.i(TAG, "onClick was null " + mClickListener);
            }
        }
    }
}