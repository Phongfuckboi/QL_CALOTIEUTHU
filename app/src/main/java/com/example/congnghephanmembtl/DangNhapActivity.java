package com.example.congnghephanmembtl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    EditText edt_name,edt_pass;
    Button btn_acess;
    TextView txt_dangki;
    private DatabaseReference mdata;
    ArrayList arrayListdn;
    String  id;
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
                String ktname;
                String ktmk;
                for(DataSnapshot datadn: snapshot.child("User").getChildren())
                {
                    user user=datadn.getValue(DTO.user.class);
                    arrayListdn.add(user);
                    ktname= user.getName();
                     ktmk=user.getPass();

                    if((edt_name.getText().toString().equals(ktname))&&(edt_pass.getText().toString().equals(ktmk)))
                    {
                        id=user.getId();
                        Toast.makeText(DangNhapActivity.this,"let's go",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(DangNhapActivity.this,MainActivity.class);
                        intent.putExtra("name",ktname);
                        intent.putExtra("id",id);
                        startActivity(intent);

                    }
                    else
                        Toast.makeText(DangNhapActivity.this,"erorr pass or name",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}