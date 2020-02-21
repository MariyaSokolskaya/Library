package com.samsung.itschool.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private OpenHelper openHelper;
    EditText titleText, authorText, yearText, genreText;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleText =  findViewById(R.id.titleBook);
        authorText = findViewById(R.id.authorBook);
        yearText = findViewById(R.id.yearBook);
        genreText = findViewById(R.id.genreBook);

        //открытие базы данных
        openHelper = new OpenHelper(getBaseContext());
        listView = findViewById(R.id.bookList);
        sqlDB = openHelper.getWritableDatabase();


/*
        listView = findViewById(R.id.bookList);
        LinkedList<Book> library = new LinkedList<>();
        library.add(new Book("Евгений Гаглоев", "Зерцалия", 2017, "Фентези"));
        library.add(new Book("Федор Достоевский", "Преступление и наказание", 1967, "Классика"));
        library.add(new Book("Николай Гоголь", "Шинель", 2001, "Классика"));
        library.add(new Book("Айзек Азимов", "Основание", 2014, "Научная фантастика"));
*/

        //Запись информации в базу
/*        for (int i = 0; i < library.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(OpenHelper.LIBRARY_AUTHOR, library.get(i).author);
            contentValues.put(OpenHelper.LIBRARY_TITLE, library.get(i).title);
            contentValues.put(OpenHelper.LIBRARY_GENRE, library.get(i).genre);
            contentValues.put(OpenHelper.LIBRARY_YEAR, library.get(i).year);
            sqlDB.insert(OpenHelper.DATABASE_TABLE, null, contentValues);
        }*/
        //sqlDB.close();

        //ArrayAdapter<Book> arrayAdapter = new ArrayAdapter(this, R.layout.list_item, library);
        //listView.setAdapter(arrayAdapter);
    }

    public void insertBookInDB(View v){
        //TODO добавить проверку наличия книги в базе
        //sqlDB = openHelper.getWritableDatabase();
        int year = Integer.parseInt(yearText.getText().toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put(OpenHelper.LIBRARY_AUTHOR, authorText.getText().toString());
        contentValues.put(OpenHelper.LIBRARY_TITLE, titleText.getText().toString());
        contentValues.put(OpenHelper.LIBRARY_GENRE, genreText.getText().toString());
        contentValues.put(OpenHelper.LIBRARY_YEAR, year);
        sqlDB.insert(OpenHelper.DATABASE_TABLE, null, contentValues);
        Toast.makeText(this, "Книга загружена в базу", Toast.LENGTH_SHORT).show();
        //sqlDB.close();
    }
    public void loadBooksFromDB(View v){
        sqlDB = openHelper.getReadableDatabase();
        Cursor cursor = sqlDB.query(OpenHelper.DATABASE_TABLE, new String[]{OpenHelper.LIBRARY_ID, OpenHelper.LIBRARY_AUTHOR,
                OpenHelper.LIBRARY_TITLE, OpenHelper.LIBRARY_GENRE, OpenHelper.LIBRARY_YEAR},null, null, null, null, null);
        String [] from = {OpenHelper.LIBRARY_ID, OpenHelper.LIBRARY_AUTHOR,
                OpenHelper.LIBRARY_TITLE, OpenHelper.LIBRARY_GENRE, OpenHelper.LIBRARY_YEAR};
        int []to = {R.id.id_book, R.id.author, R.id.title_book, R.id.genre, R.id.year};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.list_item, cursor, from, to, 1);

        listView.setAdapter(cursorAdapter);
        cursor.requery();

        //sqlDB.close();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //sqlDB.close();
    }
}
