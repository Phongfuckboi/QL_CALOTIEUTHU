package com.example.congnghephanmembtl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.ArrayList;

import DTO.History;

public class TinhCaloDiChuyenActivity extends AppCompatActivity {

    Button btn_thoathd,btn_xemtruocalo, btn_themvaolshd;
    TextView txt_tenhd,txt_kq;
    EditText edt_thoigian;
    ArrayList historyArrayList;
    DatabaseReference ref_history;
    Date today= new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_calo_di_chuyen);


        anhxa();

        //
        Intent intent;
        intent=getIntent();
        String tenhoatdong=intent.getStringExtra("tenhd");
        String calohoatdong=intent.getStringExtra("calohd");
        int chisocalo=Integer.parseInt(calohoatdong);
        txt_tenhd.setText(tenhoatdong);
        //
        //thoat
        btn_thoathd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //XEm truoc calo
        btn_xemtruocalo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String thoigian=edt_thoigian.getText().toString();
                int chisothoigian=Integer.parseInt(thoigian);
                try{
                    txt_kq.setText("-"+(chisocalo*chisothoigian));
                }catch (Exception e)
                {
                    Toast.makeText(TinhCaloDiChuyenActivity.this,"Bạn NHập Thiếu Thời Gian Hoạt Động!!!",Toast.LENGTH_SHORT).show();
                }
             }
        });

        //Thêm VÀO lịch sử
        btn_themvaolshd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edt_thoigian.getText().toString().equals("")||(edt_thoigian.getText().toString()==null)))
                {
                    Toast.makeText(TinhCaloDiChuyenActivity.this,"Bạn Điền Thiếu kìa!!!",Toast.LENGTH_SHORT).show();
                }
                else {

                    historyArrayList = new ArrayList<>();
                    ref_history = FirebaseDatabase.getInstance().getReference();
                    final String date = today.getYear() + 1900 + "/" + (1 + today.getMonth()) + "/" + today.getDate();

                    Intent intent1 = getIntent();
                    String tenhd = intent1.getStringExtra("tenhd");
                    String calohd = intent1.getStringExtra("calohd");
                    String iduser=intent.getStringExtra("id");

                    int a = Integer.parseInt(calohd);
                    int b = Integer.parseInt(edt_thoigian.getText().toString());
                    int tong = (-a * b);
                    String tongcalo2 = String.valueOf(tong);
                    String id = iduser;

                    History history = new History(id, date, tenhd, tongcalo2);
                    ref_history.child("History").push().setValue(history);
                    Toast.makeText(TinhCaloDiChuyenActivity.this, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void anhxa() {
        btn_thoathd=(Button) findViewById(R.id.thoathd);
        btn_themvaolshd=(Button) findViewById(R.id.btn_themvaols);
        btn_xemtruocalo=(Button) findViewById(R.id.btn_tinhcalohd);
        txt_kq=(TextView) findViewById(R.id.tatalcalorieshd);
        txt_tenhd=(TextView) findViewById(R.id.Itemhd);
        edt_thoigian=(EditText) findViewById(R.id.thoigianhd);

    }
}