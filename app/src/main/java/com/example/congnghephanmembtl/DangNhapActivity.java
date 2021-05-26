package com.example.congnghephanmembtl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import DTO.user;

public class DangNhapActivity extends AppCompatActivity {

   private EditText edt_name,edt_pass;
   private Button btn_acess;
   private TextView txt_dangki;
   private DatabaseReference mdata;
   public   ArrayList<user> arrayListdn;
   private  String  id;
   private  static  int check;
    String ktname;
    String ktmk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        //
        txt_dangki=(TextView) findViewById(R.id.txt_dangki);
        edt_name= (EditText) findViewById(R.id.edTenDangNhapDN);
        edt_pass= (EditText) findViewById(R.id.edMatKhauDN);
        btn_acess=(Button) findViewById(R.id.btnDongYDN) ;
        arrayListdn=new ArrayList<user>();

        //
        user user=new user("123","phongthdz","1") ;
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        mdata=FirebaseDatabase.getInstance().getReference();
//        mdata.child("User").push().setValue(user);

        Dangki();

        btn_acess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kiemtraDN();
            }
        });

    }

    private void Dangki() {
        txt_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentdk= new Intent(DangNhapActivity.this,DangKiActivity.class);
                startActivity(intentdk);
            }
        });
    }


    private void kiemtraDN() {
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot datadn: snapshot.child("User").getChildren())
                {
                    user user=datadn.getValue(DTO.user.class);
                    arrayListdn.add(user);
                    ktname= user.getName();
                    ktmk=user.getPass();
                    id=user.getId();
                    if((edt_name.getText().toString().equals(ktname))&&(edt_pass.getText().toString().equals(ktmk)))
                    {

                        Toast.makeText(DangNhapActivity.this,"let's go",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(DangNhapActivity.this,MainActivity.class);
                        intent.putExtra("name",ktname);
                        intent.putExtra("id",id);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(DangNhapActivity.this,"Sai Thông tin tài Khoản hoặc MK",Toast.LENGTH_LONG).show();
                    }

                }
//                for (int i=0; i<arrayListdn.size();i++)
//                {
//                    user user=arrayListdn.get(i);
//                    if((edt_name.getText().toString()).equals(user.getName()) && (edt_pass.getText().toString().equals(user.getId())))
//                    {
//                        Toast.makeText(DangNhapActivity.this,"let's go",Toast.LENGTH_SHORT).show();
//                        Intent intent= new Intent(DangNhapActivity.this,MainActivity.class);
//                        intent.putExtra("name",ktname);
//                        intent.putExtra("id",id);
//                        startActivity(intent);
//                    }
//                    else
//                        Toast.makeText(DangNhapActivity.this,"Sai Thông tin tài Khoản hoặc MK",Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}