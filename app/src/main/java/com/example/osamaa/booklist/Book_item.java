package com.example.osamaa.booklist;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Book_item extends AppCompatActivity {


    BookAdapter adapter;

    TextView notfoundResult;
    private ProgressBar pgsBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        notfoundResult = findViewById(R.id.notfound);

        String url = getIntent().getStringExtra("URL");


        ListView bookListView = (ListView) findViewById(R.id.list_item);



        adapter = new BookAdapter(this,new ArrayList<Books>());


        bookListView.setAdapter(adapter);

        bookListView.setEmptyView(notfoundResult);

        bookAsyncTask bookAsyncTask = new bookAsyncTask();
        bookAsyncTask.execute(url);
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

    }
    private class bookAsyncTask extends AsyncTask<String , Void , List<Books>>{

        @Override
        protected List<Books> doInBackground(String... url) {
            if (url.length < 1 || url[0] == null){
                return null;
            }

            List<Books> BookList = BookUtils.extractBooks(url[0]);
            return BookList;
        }

        @Override
        protected void onPostExecute(List<Books> books) {
            adapter.clear();

            if (books != null && !books.isEmpty()){
                adapter.addAll(books);
            }
        }
    }
}
