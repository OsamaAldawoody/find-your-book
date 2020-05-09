package com.example.osamaa.booklist;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.LoaderManager;
import android.content.Loader;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BooksList extends AppCompatActivity {


    BookAdapter adapter;

    ImageView notfoundResult;


    private static final int BOOK_LOADER_ID = 1;

    String searchWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        notfoundResult = findViewById(R.id.notfound);

        searchWord = getIntent().getStringExtra("URL");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitInterface retrofitService = retrofit.create(RetrofitInterface.class);
        Call<List<Book>> books = retrofitService.listBooks(searchWord);
        books.enqueue();
        ListView bookListView = (ListView) findViewById(R.id.list_item);



        adapter = new BookAdapter(this,new ArrayList<Book>());


        bookListView.setAdapter(adapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected Books.

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Book currentBook = adapter.getItem(position);

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

}
