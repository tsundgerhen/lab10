package com.example.lab10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class BookListActivity extends AppCompatActivity {

    ListView bookListView;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        bookListView = findViewById(R.id.bookListView);
        dbHandler = new DbHandler(this);

        // Fetch books from the database
        ArrayList<HashMap<String, String>> bookList = dbHandler.getAllBooks();

        // Set up adapter
        SimpleAdapter adapter = new SimpleAdapter(
                this,
                bookList,
                R.layout.list_row,
                new String[]{"id", "name", "author", "date"},
                new int[]{R.id.details_id, R.id.details_bname, R.id.details_bauthor, R.id.details_bdate}
        );
        bookListView.setAdapter(adapter);

        // Navigate to BookDetailActivity
        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> selectedBook = (HashMap<String, String>) parent.getItemAtPosition(position);

                Intent intent = new Intent(BookListActivity.this, BookDetailActivity.class);
                intent.putExtra("bookId", selectedBook.get("id"));
                intent.putExtra("bookName", selectedBook.get("name"));
                intent.putExtra("bookAuthor", selectedBook.get("author"));
                intent.putExtra("bookDate", selectedBook.get("date"));
                startActivity(intent);
            }
        });
    }
}
