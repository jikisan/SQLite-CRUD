package com.example.midterm_crud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    EditText et_prodName, et_prodDesc, et_prodPrice, et_prodQuantity, et_prodStatus;
    Button btn_add,btn_update,btn_view;
    OpenHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRef();
        insert();
        update();
       // delete();
        view();

    }

    private void view() {
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, display_list.class);
                startActivity(intent);
            }
        });
    }

//    private void delete() {
//        btn_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try {
//
//                    String prodName = et_prodName.getText().toString();
//
//                    Boolean checkdeletedata = DB.deleteUserData(prodName);
//                    if(checkdeletedata == true)
//                        Toast.makeText(MainActivity.this, "Delete Successful", Toast.LENGTH_SHORT).show();
//                    else
//                        Toast.makeText(MainActivity.this, "Delete Failed", Toast.LENGTH_SHORT).show();
//
//
//                }catch (Exception e){
//                    System.out.println("Error Delete " + e.getMessage());
//                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }

    private void update() {
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String prodName = et_prodName.getText().toString();
                    String prodDesc = et_prodDesc.getText().toString();
                    int prodPrice = Integer.parseInt(et_prodPrice.getText().toString());
                    int prodQuantity = Integer.parseInt(et_prodQuantity.getText().toString());
                    String prodStatus = et_prodStatus.getText().toString();

                    Boolean checkupdatedata = DB.updateUserData(prodName, prodDesc, prodPrice, prodQuantity, prodStatus);
                    if(checkupdatedata == true)
                        Toast.makeText(MainActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    System.out.println("Error Update " + e.getMessage());
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void insert() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String prodName = et_prodName.getText().toString();
                    String prodDesc = et_prodDesc.getText().toString();
                    int prodPrice = Integer.parseInt(et_prodPrice.getText().toString());
                    int prodQuantity = Integer.parseInt(et_prodQuantity.getText().toString());
                    String prodStatus = et_prodStatus.getText().toString();

                    Boolean checkupdatedata = DB.insertUserData(prodName, prodDesc, prodPrice, prodQuantity, prodStatus);
                    if(checkupdatedata == true)
                        Toast.makeText(MainActivity.this, "Insert Successful", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Insert Failed", Toast.LENGTH_SHORT).show();


                }catch (Exception e){
                    //System.out.println("(Error insert) " + e.getMessage());
                    Toast.makeText(MainActivity.this, "Empty Field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setError(String prodName, String prodDesc, String textPrice, String textQuantity, String prodStatus) {

        if(TextUtils.isEmpty(prodName))
        {
            et_prodName.setError("Empty field");
            return;
        }
        if(TextUtils.isEmpty(prodDesc))
        {
            et_prodDesc.setError("Empty field");
            return;
        }
        if(TextUtils.isEmpty(textPrice))
        {
            et_prodPrice.setError("Empty field");
            return;
        }
        if(TextUtils.isEmpty(textQuantity))
        {
            et_prodQuantity.setError("Empty field");
            return;
        }
        if(TextUtils.isEmpty(prodStatus))
        {
            et_prodStatus.setError("Empty field");
            return;
        }


    }

    private void setRef() {

        et_prodName = findViewById(R.id.et_prodName);
        et_prodDesc = findViewById(R.id.et_prodDesc);
        et_prodPrice = findViewById(R.id.et_prodPrice);
        et_prodQuantity = findViewById(R.id.et_prodQuantity);
        et_prodStatus = findViewById(R.id.et_prodStatus);
        btn_add = findViewById(R.id.btn_add);
        btn_update = findViewById(R.id.btn_update);
        btn_view = findViewById(R.id.btn_view);

        DB = new OpenHelper(this);

    }
}