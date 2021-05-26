package com.example.congnghephanmembtl;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import DTO.user;

public class DangKiActivity extends AppCompatActivity {

    EditText edt_name,edt_mk,edt_id;
    Button btn_dk;
    private DatabaseReference ref_dangki, ref_move, ref_his;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        edt_name= (EditText) findViewById(R.id.edt_tendk);
        edt_mk=(EditText) findViewById(R.id.edt_mkdk);
        edt_id=(EditText) findViewById(R.id.edt_id);
        btn_dk= (Button) findViewById(R.id.btn_dangki);


        FirebaseDatabase database_dk= FirebaseDatabase.getInstance();
        ref_dangki=FirebaseDatabase.getInstance().getReference();


        ref_move=FirebaseDatabase.getInstance().getReference();
        ref_his=FirebaseDatabase.getInstance().getReference();



        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edt_name.getText().toString();
                String mk=edt_mk.getText().toString();
                String id=edt_id.getText().toString();
                checkCMT(id);



                if(name==null||name.equals("") ) {
                    Toast.makeText(DangKiActivity.this, "Vui Lòng điền Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }
                else if(id==null ||id.equals("")|| Integer.valueOf(id)>10000||Integer.valueOf(id)<1000)
                {
                    Toast.makeText(DangKiActivity.this, "Lưu ý CMTND chỉ cần 4 số cuối", Toast.LENGTH_SHORT).show();
                }
                else if (mk==null||mk.equals(""))
                {
                    Toast.makeText(DangKiActivity.this, "Vui Lòng điền Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
                }
//                else if(checkCMT(id))
//                {
//                    Toast.makeText(DangKiActivity.this,"Id Bị trùng rồi",Toast.LENGTH_SHORT).show();
//                }
                else {
                    user user = new user(id, name, mk);
                    ref_dangki.child("User").push().setValue(user);
                    Toast.makeText(DangKiActivity.this, "Dăng Kí Thành Công", Toast.LENGTH_SHORT).show();
                    Intent intentgetstart = new Intent(DangKiActivity.this, ThongTinCaNhanActivity.class);
                    intentgetstart.putExtra("ten",name);
                    intentgetstart.putExtra("id",id);
                    startActivity(intentgetstart);


                }

            }
        });






    }
    private boolean checkCMT(String idckeck) {
        final boolean[] kt = {true};
            ref_dangki.addValueEventListener(new ValueEventListener() {
            String iduser;
            ArrayList arrayuser=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datauser : snapshot.child("User").getChildren())
                {
                    user usercked =datauser.getValue(user.class);
                    iduser=usercked.getId().toString();
                    if(idckeck.equals(iduser))
                    {
                        kt[0] =true;

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    return kt[0];
    }
}