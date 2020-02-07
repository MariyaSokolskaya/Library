package com.samsung.itschool.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.bookList);
        LinkedList<Book> library = new LinkedList<>();
        library.add(new Book("Евгений Гаглоев", "Зерцалье", 2017));
        library.add(new Book("Федор Достоевский", "Преступление и наказание", 1967));
        library.add(new Book("Николай Гоголь", "Шинель", 2001));
        library.add(new Book("Айзек Азимов", "Основание", 2014));
        ArrayAdapter<Book> arrayAdapter = new ArrayAdapter(this, R.layout.list_item, library);
        listView.setAdapter(arrayAdapter);
    }
}
