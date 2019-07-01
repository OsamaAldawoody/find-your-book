package com.example.osamaa.booklist;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Book_item extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Books>> {


    BookAdapter adapter;

    ImageView notfoundResult;


    private static final int BOOK_LOADER_ID = 1;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        notfoundResult = findViewById(R.id.notfound);

        url = getIntent().getStringExtra("URL");


        ListView bookListView = (ListView) findViewById(R.id.list_item);



        adapter = new BookAdapter(this,new ArrayList<Books>());


        bookListView.setAdapter(adapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected Books.

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Books currentBook = adapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)


                if (currentBook != null){
                String url = currentBook.getUrl();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            notfoundResult.setImageResource(R.drawable.problem_connection);
        }

    }

    @NonNull
    @Override
    public Loader<List<Books>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new BookLoader(this,url);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Books>> loader, List<Books> books) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);


        // Set empty state text to display "No earthquakes found."
        notfoundResult.setImageResource(R.drawable.emty_library);

        // Clear the adapter of previous book data
        adapter.clear();

        // If there is a valid list of {@link book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            notfoundResult.setVisibility(View.GONE);
            adapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Books>> loader) {

        adapter.clear();
    }

}
