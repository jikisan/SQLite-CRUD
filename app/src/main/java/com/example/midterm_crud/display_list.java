package com.example.midterm_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class display_list extends AppCompatActivity {

    ListView lv_prodList;
    EditText et_searchID;
    Button btn_searchByID, btn_searchAll, btn_delete;

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    OpenHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        DB = new OpenHelper(this);

        setRef();
        listItem = new ArrayList<>();
        viewButton();
        searchAllIDButton();
        delete();

        lv_prodList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = lv_prodList.getItemAtPosition(i).toString();
                Toast.makeText(display_list.this, "" + text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void searchAllIDButton() {
        btn_searchAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listItem.clear();
                searchAllButton();
            }
        });
    }

    private void delete() {
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String ID = et_searchID.getText().toString();

                    Boolean checkdeletedata = DB.deleteUserData(ID);
                    if(checkdeletedata == true)
                        Toast.makeText(display_list.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(display_list.this, "Delete Failed", Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    System.out.println("Error Delete " + e.getMessage());
                    Toast.makeText(display_list.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void viewButton() {
        btn_searchByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listItem.clear();
                searchID();

            }
        });
    }

    private void searchAllButton() {

        et_searchID.setText("");
        Cursor cursor = DB.getData();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listItem.add("\n\nID:" + cursor.getString(0));
                listItem.add("Name: " + cursor.getString(1));
                listItem.add("Description: " + cursor.getString(2));

                listItem.add("Price: " + cursor.getString(3));

                listItem.add("Quantity: " + cursor.getString(4));

                listItem.add("Status: " + cursor.getString(5) + "\n\n");

            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            lv_prodList.setAdapter(adapter);
        }
    }


    private void searchID() {

        String searchID = et_searchID.getText().toString();

        if(TextUtils.isEmpty(searchID))
        {
            et_searchID.setError("Empty field");
        }

        Cursor cursor = DB.viewData(searchID);

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            listItem.clear();

        }else{
            while (cursor.moveToNext()){
                listItem.add("\n\nID:" + cursor.getString(0));
                listItem.add("Name: " + cursor.getString(1));
                listItem.add("Description: " + cursor.getString(2));

                listItem.add("Price: " + cursor.getString(3));

                listItem.add("Quantity: " + cursor.getString(4));

                listItem.add("Status: " + cursor.getString(5) + "\n\n");

            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            lv_prodList.setAdapter(adapter);
        }
    }

    private void setRef() {
        lv_prodList = findViewById(R.id.lv_prodList);
        et_searchID = findViewById(R.id.et_searchID);
        btn_searchByID = findViewById(R.id.btn_searchByID);
        btn_searchAll = findViewById(R.id.btn_searchAll);
        btn_delete = findViewById(R.id.btn_delete);


    }
}