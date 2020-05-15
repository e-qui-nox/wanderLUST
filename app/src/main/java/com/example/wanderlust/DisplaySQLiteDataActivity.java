package com.example.wanderlust;

import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class DisplaySQLiteDataActivity extends AppCompatActivity {

    QLiteHelper qLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;
    FloatingActionButton floatingActionButton;
    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> PHONE_NUMBER_Array;
    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_trip);
        ID_Array = new ArrayList<String>();
        NAME_Array = new ArrayList<String>();
        PHONE_NUMBER_Array = new ArrayList<String>();
        qLiteHelper = new QLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData.Item item = (ClipData.Item)listAdapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), ShowSingleRecordActivity.class);
                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());
                startActivity(intent);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplaySQLiteDataActivity.this, TripActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        ShowSQLiteDBdata() ;
        super.onResume();
    }

    private void ShowSQLiteDBdata() {
        sqLiteDatabase = qLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ QLiteHelper.TABLE_NAME+"", null);
        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();

        if (cursor.moveToFirst()) {
            do {
                ID_Array.add(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_ID)));
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_ID)));
                NAME_Array.add(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_1_Name)));
                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_2_PhoneNumber)));
            } while (cursor.moveToNext());
        }
        listAdapter = new ListAdapter(DisplaySQLiteDataActivity.this,
                ID_Array,
                NAME_Array,
                PHONE_NUMBER_Array
        );

        LISTVIEW.setAdapter(listAdapter);
        cursor.close();
    }
}