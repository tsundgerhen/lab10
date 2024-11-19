package com.example.lab10;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab10.DbHandler;

public class BookDetailActivity extends AppCompatActivity {

    EditText bookName, bookAuthor, bookDate;
    Button updateButton, deleteButton;
    DbHandler dbHandler;
    int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookName = findViewById(R.id.bookName);
        bookAuthor = findViewById(R.id.bookAuthor);
        bookDate = findViewById(R.id.bookDate);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        dbHandler = new DbHandler(this);

        // Get book details from intent
        bookId = Integer.parseInt(getIntent().getStringExtra("bookId"));
        bookName.setText(getIntent().getStringExtra("bookName"));
        bookAuthor.setText(getIntent().getStringExtra("bookAuthor"));
        bookDate.setText(getIntent().getStringExtra("bookDate"));

        // Update book
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bookName.getText().toString();
                String author = bookAuthor.getText().toString();
                String date = bookDate.getText().toString();

                if (!name.isEmpty() && !author.isEmpty() && !date.isEmpty()) {
                    dbHandler.updateBook(bookId, name, author, date);
                    Toast.makeText(BookDetailActivity.this, "Ном өөрчлөгдлөө!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(BookDetailActivity.this, "Бүх хэсгийг бөглөн үү!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete book
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deleteBook(bookId);
                Toast.makeText(BookDetailActivity.this, "Ном устгагдлаа!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
