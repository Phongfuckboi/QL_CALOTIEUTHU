package com.example.congnghephanmembtl;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import DTO.Move;

public class TapLuyenactivity extends AppCompatActivity {

    ArrayList <Move> array_move;
    ListView list_move;
    ArrayAdapter arrayAdaptermove;
    DatabaseReference ref_move;
    Move move;
    Button btn_thembt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_luyenactivity);
        //
        list_move=(ListView) findViewById(R.id.lst_move);
        btn_thembt=(Button) findViewById(R.id.btn_themvaomove);
        array_move=new ArrayList<Move>();
        arrayAdaptermove=new ArrayAdapter<Move>(this, android.R.layout.simple_list_item_1,array_move);
        //
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        ref_move=FirebaseDatabase.getInstance().getReference();
        ref_move.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                array_move.clear();
                for(DataSnapshot data: snapshot.child("Move").getChildren())
                {
                    Move move=data.getValue(Move.class);
                    array_move.add(move);
                }
                list_move.setAdapter(arrayAdaptermove);
                arrayAdaptermove.notifyDataSetInvalidated();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //su kien chonn item
        Intent intent =new Intent(this, TinhCaloDiChuyenActivity.class);
        list_move.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Move hoatdong= array_move.get(position);
                intent.putExtra("tenhd",hoatdong.getExercises());
                intent.putExtra("calohd",hoatdong.getCalories());
                startActivity(intent);
            }
        });
        btn_thembt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HienthiDialog();
            }
        });

    }
    private void HienthiDialog() {
        Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_thembt);
        dialog.show();
        EditText edt_tenbt=(EditText) dialog.findViewById(R.id.edt_tenbt);
        EditText edt_calobt=(EditText) dialog.findViewById(R.id.edt_calobt);
        Button btn_ok=(Button) dialog.findViewById(R.id.btn_thembt);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenbt=edt_tenbt.getText().toString();
                String calobt=edt_calobt.getText().toString();

                if(tenbt.equals("")|| tenbt==null)
                {
                    Toast.makeText(TapLuyenactivity.this,"Vui Lòng Điền Đầy Đủ Thông Tin!!",Toast.LENGTH_SHORT).show();
                }
                else if(calobt.equals("")|| calobt==null)
                {
                    Toast.makeText(TapLuyenactivity.this,"Vui Lòng Điền Đầy Đủ Thông Tin!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Move move1 = new Move(tenbt, calobt);

                    ref_move=FirebaseDatabase.getInstance().getReference();
                    ref_move.child("Move").push().setValue(move1);
                    Toast.makeText(TapLuyenactivity.this,"Thêm Thành Công!!",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                }
            }
        });
    }
}