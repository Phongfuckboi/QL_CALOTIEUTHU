package com.example.congnghephanmembtl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import DTO.eat;

public class ThemThucDonActivity extends AppCompatActivity {
    Button btn_them, btn_trolai;
    EditText edt_tenmon;
    EditText edt_calomon;

    private DatabaseReference data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_thuc_don);
        btn_them=(Button) findViewById(R.id.btn_them);
        btn_trolai=(Button) findViewById(R.id.btn_trolai);
        edt_tenmon=(EditText) findViewById(R.id.ten_mon);
        edt_calomon=(EditText) findViewById(R.id.calo_mon);



        FirebaseDatabase database=FirebaseDatabase.getInstance();
        data=FirebaseDatabase.getInstance().getReference();
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenmon=edt_tenmon.getText().toString();
                String calotrongmon=edt_calomon.getText().toString();
                if(tenmon==null||tenmon.equals(""))

                 {
               Toast.makeText(ThemThucDonActivity.this,"Điền Dầy Đủ Thông tin",Toast.LENGTH_LONG).show();

                    }
                else if(calotrongmon==null||calotrongmon.equals(""))
                {
                    Toast.makeText(ThemThucDonActivity.this,"Điền Dầy Đủ Thông tin",Toast.LENGTH_LONG).show();
                }
                else
                {
               eat neweat= new eat(tenmon,calotrongmon);
               data.child("Eat").push().setValue(neweat);
               Toast.makeText(ThemThucDonActivity.this,"Thêm Thành Công!!!",Toast.LENGTH_LONG).show();
                }
            }
        });


        //
        btn_trolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


    }
}