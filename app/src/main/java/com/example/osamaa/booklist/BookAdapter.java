package com.example.osamaa.booklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Books> {
    static final String bublishedAt = "Published in :";
    public BookAdapter(@NonNull Context context, @NonNull List<Books> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        Books currentBook = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_book_item, parent, false);
        }

        if (currentBook != null)
        {
            TextView titleTextView = ListItemView.findViewById(R.id.title);
            titleTextView.setText(currentBook.getTitle());

            TextView authorTextView = ListItemView.findViewById(R.id.author);
            authorTextView.setText(currentBook.getAuthor());


            TextView publisDateTextView = ListItemView.findViewById(R.id.publish_date);
            String publishDate = bublishedAt+currentBook.getPublishDate();
            publisDateTextView.setText(publishDate);

        }

        return ListItemView;
    }
}
