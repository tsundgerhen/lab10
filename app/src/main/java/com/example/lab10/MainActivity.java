package com.example.lab10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText bookName, bookAuthor, bookDate;
    Button saveButton, viewBooksButton, logoutButton;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        bookName = findViewById(R.id.bookName);
        bookAuthor = findViewById(R.id.bookAuthor);
        bookDate = findViewById(R.id.bookDate);
        saveButton = findViewById(R.id.saveButton);
        viewBooksButton = findViewById(R.id.viewBooksButton);
        logoutButton = findViewById(R.id.logoutButton);

        dbHandler = new DbHandler(this);

        // Save book
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bookName.getText().toString();
                String author = bookAuthor.getText().toString();
                String date = bookDate.getText().toString();

                if (!name.isEmpty() && !author.isEmpty() && !date.isEmpty()) {
                    dbHandler.addBook(name, author, date);
                    Toast.makeText(MainActivity.this, "Ном нэмэгдлээ!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Бүх хэсэгийн бөглөн үү!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // View books
        viewBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookListActivity.class);
                startActivity(intent);
            }
        });

        // Logout
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
