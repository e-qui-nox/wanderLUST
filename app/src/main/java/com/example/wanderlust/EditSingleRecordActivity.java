package com.example.wanderlust;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class EditSingleRecordActivity extends AppCompatActivity {

    EditText name, phone_number;
    Button update;
    SQLiteDatabase sqLiteDatabase;
    QLiteHelper qLiteHelper;
    Cursor cursor;
    String IDholder;
    String SQLiteDataBaseQueryHolder ;
    SQLiteDatabase sqLiteDatabaseObj;
    Calendar c;
    DatePickerDialog dpd;
    ImageView date;
    ImageView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_record);

        name = (EditText) findViewById(R.id.EditTextName);
        phone_number = (EditText) findViewById(R.id.editText3);
        update = (Button) findViewById(R.id.buttonUpdate);
        date = (ImageView)findViewById(R.id.date);
        location = (ImageView)findViewById(R.id.location);
        qLiteHelper = new QLiteHelper(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                dpd = new DatePickerDialog(EditSingleRecordActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay)
                    {
                        name.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                    }
                },day,month,year);
                dpd.show();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String GetName = name.getText().toString();
                String GetPhoneNumber = phone_number.getText().toString();

                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "UPDATE " + QLiteHelper.TABLE_NAME + " SET "+ QLiteHelper.Table_Column_1_Name+" = '"+GetName+"' , "+ QLiteHelper.Table_Column_2_PhoneNumber+" = '"+GetPhoneNumber+"' WHERE id = " + IDholder + "";

                sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                sqLiteDatabase.close();

                Toast.makeText(EditSingleRecordActivity.this,"Data Edit Successfully", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onResume() {

        ShowSRecordInEditText();

        super.onResume();
    }

    public void ShowSRecordInEditText() {

        sqLiteDatabase = qLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("EditID");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + QLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                name.setText(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_1_Name)));

                phone_number.setText(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_2_PhoneNumber)));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(QLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

}

