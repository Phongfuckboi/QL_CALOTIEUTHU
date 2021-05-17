package com.example.congnghephanmembtl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import DTO.user;

public class DangNhapActivity extends AppCompatActivity {

    EditText edt_name,edt_pass;
    Button btn_acess;
    TextView txt_dangki;
    private DatabaseReference mdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        //
        txt_dangki=(TextView) findViewById(R.id.txt_dangki);
        edt_name= (EditText) findViewById(R.id.edTenDangNhapDN);
        edt_pass= (EditText) findViewById(R.id.edMatKhauDN);
        btn_acess=(Button) findViewById(R.id.btnDongYDN) ;

        //
        user user=new user("123","phongthdz","1") ;
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        mdata=FirebaseDatabase.getInstance().getReference();
//        mdata.child("User").push().setValue(user);

        kiemtraDN();
        Dangki();

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
        mdata.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                user usern=snapshot.getValue(user.class);
                String ktname =usern.getName();
                String ktmk=usern.getPass();
                btn_acess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if((edt_name.getText().toString().equals(ktname))&&(edt_pass.getText().toString().equals(ktmk)))
                        {

                            Toast.makeText(DangNhapActivity.this,"let's go",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(DangNhapActivity.this,MainActivity.class);
                            startActivity(intent);

                        }
                        else
                            Toast.makeText(DangNhapActivity.this,"erorr pass or name",Toast.LENGTH_SHORT).show();
                    }
                });


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}