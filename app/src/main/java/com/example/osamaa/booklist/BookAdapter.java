package com.example.osamaa.booklist;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {
    private static final String bublishedAt = "Published in :";



    Bitmap bmp;

    ImageView bookImageView;

    public BookAdapter(@NonNull Context context, @NonNull List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItemView = convertView;
        Book currentBook = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_book_item, parent, false);
        }

        if (currentBook != null)
        {
            TextView titleTextView = ListItemView.findViewById(R.id.title);
            titleTextView.setText(currentBook.getTitle());

            bookImageView = ListItemView.findViewById(R.id.book_image);
            bookImageView.setImageBitmap(currentBook.getImage());


            TextView publisDateTextView = ListItemView.findViewById(R.id.publish_date);
            String publishDate = bublishedAt+currentBook.getPublishDate();
            publisDateTextView.setText(publishDate);

        }

        return ListItemView;
    }
}

