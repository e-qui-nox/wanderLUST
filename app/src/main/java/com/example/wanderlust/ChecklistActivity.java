package com.example.wanderlust;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ChecklistActivity extends AppCompatActivity {

    EditText edt_item;
    ListView itemlist;
    String[] ListElements = new String[] {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checklist);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#018db7"));
        actionBar.setBackgroundDrawable(colorDrawable);

        edt_item = (EditText)findViewById(R.id.edt_item);
        itemlist = (ListView)findViewById(R.id.itemlist);

        final List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChecklistActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);

        itemlist.setAdapter(adapter);

        FloatingActionButton floatingActionButton =
                (FloatingActionButton) findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListElementsArrayList.add(edt_item.getText().toString());
                adapter.notifyDataSetChanged();
                edt_item.setText("");
            }
        });
    }
}

