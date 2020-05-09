package com.example.osamaa.booklist;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private static final String LOG_TAG = BookLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public BookLoader(@NonNull Context context,String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of Books.
        List<Book> BookList = BookUtils.extractBooks(mUrl);
        return BookList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
