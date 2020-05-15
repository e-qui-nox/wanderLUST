package com.example.wanderlust;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ShowSingleRecordActivity extends AppCompatActivity {

    String IDholder;
    TextView id, name, phone_number;
    SQLiteDatabase sqLiteDatabase;
    QLiteHelper qLiteHelper;
    Cursor cursor;
    Button Delete, Edit;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    ImageView img_place;
    ImageButton share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);

        id = (TextView) findViewById(R.id.textViewID);
        name = (TextView) findViewById(R.id.textViewName);
        phone_number = (TextView) findViewById(R.id.textViewPhoneNumber);
        Delete = (Button)findViewById(R.id.buttonDelete);
        Edit = (Button)findViewById(R.id.buttonEdit);
        img_place = (ImageView)findViewById(R.id.img_place);
        share = (ImageButton)findViewById(R.id.share);
        qLiteHelper = new QLiteHelper(this);
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ShowSingleRecordActivity.this);
                alertDialogBuilder.setMessage("You sure, that you want to cancel");
                    alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            OpenSQLiteDataBase();
                            SQLiteDataBaseQueryHolder = "DELETE FROM "+ QLiteHelper.TABLE_NAME+" WHERE id = "+IDholder+"";
                            sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
                            sqLiteDatabase.close();
                            finish();
                        }
                    });
                    alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Planning a trip to "+phone_number.getText().toString()+" on "+name.getText().toString()+". Would you like to join?");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditSingleRecordActivity.class);
                intent.putExtra("EditID", IDholder);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        ShowSingleRecordInTextView();
        super.onResume();
    }

    public void ShowSingleRecordInTextView() {
        sqLiteDatabase = qLiteHelper.getWritableDatabase();
        IDholder = getIntent().getStringExtra("ListViewClickedItemValue");
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + QLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);
        if (cursor.moveToFirst()) {
            do {
                id.setText(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_ID)));
                name.setText(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_1_Name)));
                phone_number.setText(cursor.getString(cursor.getColumnIndex(QLiteHelper.Table_Column_2_PhoneNumber)));
                setImage();
            }
            while (cursor.moveToNext());
            cursor.close();
        }
    }

    public void setImage(){
        if(phone_number.getText().toString().equals("Delhi")){
            img_place.setImageResource(R.drawable.delhi);
        }
        else if(phone_number.getText().toString().equals("Ahmedabad")){
            img_place.setImageResource(R.drawable.ahmedabad);
        }
        else if(phone_number.getText().toString().equals("Bangalore")){
            img_place.setImageResource(R.drawable.blr_slide_1);
        }
        else if(phone_number.getText().toString().equals("Chennai")){
            img_place.setImageResource(R.drawable.chennai);
        }
        else if(phone_number.getText().toString().equals("Hyderabad")){
            img_place.setImageResource(R.drawable.hydrabad);
        }
        else if(phone_number.getText().toString().equals("Jaipur")){
            img_place.setImageResource(R.drawable.jaipur);
        }
        else if(phone_number.getText().toString().equals("Kolkata")){
            img_place.setImageResource(R.drawable.kolkata);
        }
        else if(phone_number.getText().toString().equals("Mumbai")){
            img_place.setImageResource(R.drawable.mumbai);
        }
        else if(phone_number.getText().toString().equals("Panjim")){
            img_place.setImageResource(R.drawable.panaji);
        }
        else if(phone_number.getText().toString().equals("Pune")){
            img_place.setImageResource(R.drawable.pune);
        }
    }

    public void OpenSQLiteDataBase(){
        sqLiteDatabaseObj = openOrCreateDatabase(QLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

}
