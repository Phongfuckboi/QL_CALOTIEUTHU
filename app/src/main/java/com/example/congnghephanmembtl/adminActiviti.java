package com.example.congnghephanmembtl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import DTO.user;

public class adminActiviti extends AppCompatActivity {

    private ListView lst_nguowidung;
    private Button btn_thoat;
    private ArrayList<user> arrayList;
    private ArrayAdapter arrayAdapter;
    DatabaseReference ref_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lst_nguowidung=(ListView) findViewById(R.id.lst_ngươidung);
        btn_thoat=(Button) findViewById(R.id.btn_thpat_quanli);
        arrayList= new ArrayList<user>();
        arrayAdapter=new ArrayAdapter(adminActiviti.this,android.R.layout.simple_list_item_1,arrayList);

        Hienthiuser();
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lst_nguowidung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog= new Dialog(adminActiviti.this);
                dialog.setContentView(R.layout.dialog_xoa_user);
                dialog.show();


                Button btn_xoa= (Button) dialog.findViewById(R.id.btn_xaouser);
                Button btn_thoat=(Button) dialog.findViewById(R.id.btn_thoat_xoa_user) ;
                btn_thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_xoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<String> keyList = new ArrayList<String>();
                        user user= (DTO.user) lst_nguowidung.getItemAtPosition(position);
                        String id_user=user.getId().toString();
                        Toast.makeText(adminActiviti.this,"phoong"+id_user,Toast.LENGTH_LONG).show();
                        Query query= ref_user.child("User").orderByChild("id").equalTo(id_user);
                        query.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                                {
                                    dataSnapshot.getRef().removeValue();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e(String.valueOf(this), "onCancelled", error.toException());
                            }

                        });
                        Toast.makeText(adminActiviti.this,"Xóa Thành Công",Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                    }
                });




            }
        });




    }

    private void Hienthiuser() {
        ref_user= FirebaseDatabase.getInstance().getReference();
        ref_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data_user: snapshot.child("User").getChildren())
                {
                    user user=data_user.getValue(DTO.user.class);
                    arrayList.add(user);

                }
                lst_nguowidung.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetInvalidated();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}