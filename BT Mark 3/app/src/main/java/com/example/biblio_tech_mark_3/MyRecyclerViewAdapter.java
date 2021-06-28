package com.example.biblio_tech_mark_3;

/*From: https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example*/

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    public static final String TAG = "!!!ViewBooksActivity!!!";

    private List<Book> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private DataBaseHelper dbh;
    private Context viewBooksActivity;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, DataBaseHelper data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data.getAllBooks();
        this.dbh = data;
        this.viewBooksActivity = context;

        System.out.println(("@ MyRecyclerViewAdapter mClickListener = " + mClickListener));
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = mData.get(position);
        holder.myTextView.setText(book.toString());
    }

    // total number of rows
    public int getItemCount() {
        return mData.size();
    }

    // convenience method for getting data at click position
    Book getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
        System.out.println(("@ setClickListener mClickListener = " + mClickListener));
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
            myTextView = itemView.findViewById(R.id.bookRowName);
            itemView.setOnClickListener(this);
        }

        //this is where the click happens
        @Override
        public void onClick(View view) {

            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
                // this sends the click to ViewBooksActivity.onItemClick()
            } else {
                System.out.println("mClickListener = " + mClickListener);
            }
        }
    }

}
