package com.example.osamaa.booklist;

import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    EditText search_edit;

    String url;

    // the api url with out book name
    private static String BOOK_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button searchButton = findViewById(R.id.search_button);




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_edit =(EditText) findViewById(R.id.search_edit_frame);
                String searchWord = search_edit.getText().toString().replaceAll(" ","_").toLowerCase();

                url = BOOK_URL + searchWord;

                Log.v("MainActivity",url);
                Intent myintent=new Intent(MainActivity.this, Book_item.class).putExtra("URL",url);
                startActivity(myintent);
            }
        });

    }
}
