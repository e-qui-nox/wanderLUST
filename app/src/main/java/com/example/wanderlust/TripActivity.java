package com.example.wanderlust;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class TripActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextName;
    AutoCompleteTextView editTextPhoneNumber;
    String NameHolder, NumberHolder, SQLiteDataBaseQueryHolder;
    Button EnterData;
    Boolean EditTextEmptyHold;
    ImageView date;
    ImageView location;
    Calendar c;
    DatePickerDialog dpd;
    private List<PlaceItem> placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        fillPlaceList();

        AutoCompleteTextView editText = findViewById(R.id.actv);
        AutoCompletePlaceAdapter adapter = new AutoCompletePlaceAdapter(this, placeList);
        editText.setAdapter(adapter);

        EnterData = (Button)findViewById(R.id.button);
        editTextName = (EditText)findViewById(R.id.editText);
        editTextPhoneNumber = (AutoCompleteTextView) findViewById(R.id.actv);
        date = (ImageView)findViewById(R.id.date);
        location = (ImageView)findViewById(R.id.location);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                dpd = new DatePickerDialog(TripActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay)
                    {
                        editTextName.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                    }
                    },day,month,year);
                dpd.show();
            }
        });

        EnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                CheckEditTextStatus();
                InsertDataIntoSQLiteDatabase();
                EmptyEditTextAfterDataInsert();
                Intent intent = new Intent(TripActivity.this, DisplaySQLiteDataActivity.class);
                startActivity(intent);

            }
        });

    }

    private void fillPlaceList(){
        placeList = new ArrayList<>();
        placeList.add(new PlaceItem("Delhi", R.drawable.delhi));
        placeList.add(new PlaceItem("Bangalore", R.drawable.blr_slide_1));
        placeList.add(new PlaceItem("Chennai", R.drawable.chennai));
        placeList.add(new PlaceItem("Hyderabad", R.drawable.hydrabad));
        placeList.add(new PlaceItem("Ahmedabad", R.drawable.ahmedabad));
        placeList.add(new PlaceItem("Kolkata", R.drawable.kolkata));
        placeList.add(new PlaceItem("Jaipur", R.drawable.jaipur));
        placeList.add(new PlaceItem("Mumbai", R.drawable.mumbai));
        placeList.add(new PlaceItem("Pune", R.drawable.pune));
        placeList.add(new PlaceItem("Panjim", R.drawable.panaji));

    }
    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(QLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public void SQLiteTableBuild(){
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+ QLiteHelper.TABLE_NAME+" ("+ QLiteHelper.Table_Column_ID+" INTEGER PRIMARY KEY, "+ QLiteHelper.Table_Column_1_Name+" VARCHAR, "+ QLiteHelper.Table_Column_2_PhoneNumber+" VARCHAR)";
        sqLiteDatabaseObj.execSQL(CREATE_TABLE);
    }

    public void CheckEditTextStatus(){
        NameHolder = editTextName.getText().toString() ;
        NumberHolder = editTextPhoneNumber.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(NumberHolder)){
            EditTextEmptyHold = false ;
        }
        else {
            EditTextEmptyHold = true ;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextEmptyHold == true)
        {
            SQLiteDataBaseQueryHolder = "INSERT INTO "+ QLiteHelper.TABLE_NAME+" (name,phone_number) VALUES('"+NameHolder+"', '"+NumberHolder+"');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();
            Toast.makeText(TripActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(TripActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }
    }
    public void EmptyEditTextAfterDataInsert(){
        editTextName.getText().clear();
        editTextPhoneNumber.getText().clear();
    }
}
