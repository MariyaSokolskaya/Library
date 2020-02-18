package com.samsung.itschool.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    private OpenHelper openHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //открытие базы данных
        openHelper = new OpenHelper(getBaseContext());
        SQLiteDatabase sqlDB = openHelper.getWritableDatabase();


        listView = findViewById(R.id.bookList);
        LinkedList<Book> library = new LinkedList<>();
        library.add(new Book("Евгений Гаглоев", "Зерцалия", 2017, "Фентези"));
        library.add(new Book("Федор Достоевский", "Преступление и наказание", 1967, "Классика"));
        library.add(new Book("Николай Гоголь", "Шинель", 2001, "Классика"));
        library.add(new Book("Айзек Азимов", "Основание", 2014, "Научная фантастика"));

        //Запись информации в базу
        for (int i = 0; i < library.size(); i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(OpenHelper.LIBRARY_AUTHOR, library.get(i).author);
            contentValues.put(OpenHelper.LIBRARY_TITLE, library.get(i).title);
            contentValues.put(OpenHelper.LIBRARY_GENRE, library.get(i).genre);
            contentValues.put(OpenHelper.LIBRARY_YEAR, library.get(i).year);
            sqlDB.insert(OpenHelper.DATABASE_TABLE, null, contentValues);
        }
        //sqlDB.close();
        Cursor cursor = sqlDB.query(OpenHelper.DATABASE_TABLE, new String[]{OpenHelper.LIBRARY_AUTHOR,
                        OpenHelper.LIBRARY_TITLE, OpenHelper.LIBRARY_GENRE},null, null, null, null, null);
        ArrayList cursorList = new ArrayList();
        //TODO перенести данные из курсора в список (возможно через вспомогательный класс)
        //TODO список отразить на интерфейсе через адаптер
        
        ArrayAdapter<Book> arrayAdapter = new ArrayAdapter(this, R.layout.list_item, library);
        listView.setAdapter(arrayAdapter);
    }
}
