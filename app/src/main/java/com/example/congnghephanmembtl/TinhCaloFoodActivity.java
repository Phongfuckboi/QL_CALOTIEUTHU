package com.example.congnghephanmembtl;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.History;

public class TinhCaloFoodActivity extends AppCompatActivity {

    TextView txt_ckeckedItem, txt_gram;
    EditText edt_kq;
    Button btn_tinhcalo, btn_themvaols, btn_thoat;
    History history;
    ListView list_history;
    DatabaseReference ref_history;
    List<History> historyArrayList;
    Date today = new Date();
    String food;
    String calo;
    String iduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_calo_food);
        anhxa();

        //lay du lieu tu inten
        Intent intent = getIntent();
         food = intent.getStringExtra("food");
         calo=intent.getStringExtra("calo");
         iduser=intent.getStringExtra("id");
        int calo1=Integer.parseInt(calo);
        txt_ckeckedItem.setText(food+"*100g");

        //thoat
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //tinh calo
        btn_tinhcalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int gramkq=Integer.valueOf(edt_kq.getText().toString());
                String tongcalo=String.valueOf(gramkq*calo1);
                txt_gram.setText(tongcalo);

            }
        });
        //them vao lich su
        btn_themvaols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edt_kq.getText().toString().equals("")||(edt_kq.getText().toString()==null)))
                {
                    Toast.makeText(TinhCaloFoodActivity.this,"Bạn Điền Thiếu kìa!!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    themvaols();
                    historyArrayList = new ArrayList<>();
                    ref_history = FirebaseDatabase.getInstance().getReference();
                    final String date = today.getYear() + 1900 + "/" + (1 + today.getMonth()) + "/" + today.getDate();

                    Intent intent1 = getIntent();
                    String tenmondachon = intent1.getStringExtra("food");
                    String calomondachon = intent1.getStringExtra("calo");

                    int a = Integer.parseInt(calomondachon);
                    int b = Integer.parseInt(edt_kq.getText().toString());
                    int tong = (a * b);

                    String tongcalo2 = String.valueOf(tong);
                    String id = iduser;

                    history = new History(id, date, tenmondachon, tongcalo2);
                    ref_history.child("History").push().setValue(history);
                    Toast.makeText(TinhCaloFoodActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
    private void themvaols() {

    }
    private void anhxa() {
        list_history =(ListView) findViewById(R.id.historyListView);
        txt_ckeckedItem = (TextView) findViewById(R.id.clickedItem);
        txt_gram = (TextView) findViewById(R.id.tatalcalories);
        edt_kq = (EditText) findViewById(R.id.gram);
        btn_thoat = (Button) findViewById(R.id.btn_thoat);
        btn_themvaols = (Button) findViewById(R.id.btn_themvaols);
        btn_tinhcalo = (Button) findViewById(R.id.tinh_calo);
    }
    //lay du lieu tu inten



}
