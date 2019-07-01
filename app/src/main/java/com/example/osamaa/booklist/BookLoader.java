package com.example.osamaa.booklist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.AsyncTaskLoader;

import java.util.List;

public class BookLoader extends AsyncTaskLoader<List<Books>> {
    private static final String LOG_TAG = BookLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public BookLoader(@NonNull Context context,String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Nullable
    @Override
    public List<Books> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of Books.
        List<Books> BookList = BookUtils.extractBooks(mUrl);
        return BookList;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
