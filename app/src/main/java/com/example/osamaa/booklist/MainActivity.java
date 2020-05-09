package com.example.osamaa.booklist;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{


    EditText search_edit;

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
            Intent myintent=new Intent(MainActivity.this, BooksList.class).putExtra("searchWord",searchWord.trim());
            startActivity(myintent);
            }
        });

    }

}
