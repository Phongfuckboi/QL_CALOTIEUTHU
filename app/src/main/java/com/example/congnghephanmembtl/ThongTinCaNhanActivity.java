package com.example.congnghephanmembtl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import DTO.Info;

public class ThongTinCaNhanActivity extends AppCompatActivity {

    EditText edt_hientai, edt_muctieu;
    Button btn_tinhcalo, btn_letgo;
    TextView txt_calohientai, txt_calomuctieu;
    double chisocalo;
     int id;
    int cannanghientai;
    int cannangmongmuon;
    int calohientai;
    int calomuctieu;
    private DatabaseReference ref_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);
        //
        edt_hientai=(EditText) findViewById(R.id.currentWeight);
        edt_muctieu=(EditText) findViewById(R.id.targetWeight);
        btn_tinhcalo=(Button) findViewById(R.id.calculateDayCal);
        btn_letgo=(Button) findViewById(R.id.getStarted);
        txt_calohientai=(TextView) findViewById(R.id.calorieCurrent);
        txt_calomuctieu=(TextView) findViewById(R.id.calorieTarget);
        //getintent tu ben dangki
        Intent intent=getIntent();
        id =Integer.valueOf(intent.getStringExtra("id"));





        //
        btn_letgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edt_hientai.getText().toString()).equals(""))
                {
                    Toast.makeText(ThongTinCaNhanActivity.this,"Bạn cần điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else if ((edt_muctieu.getText().toString().equals("")))
                {
                    Toast.makeText(ThongTinCaNhanActivity.this,"Bạn cần điền đầy đủ thông tin",Toast.LENGTH_SHORT).show();

                }
                else
                    getstart();
            }
        });

    }

    public  void getstart (){
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        ref_info=FirebaseDatabase.getInstance().getReference();
        Intent intent= new Intent(this,MainActivity.class);
        Info info= new Info(cannanghientai,cannangmongmuon,calohientai,calomuctieu,id);
        ref_info.child("InFo").push().setValue(info);
        intent.putExtra("id",String.valueOf(id));
        startActivity(intent);

    }



    public void onRadioclick(View view)
    {
        //kiem tra xem radio da dc click chua;
        boolean ckeck=((RadioButton) view).isChecked();
        // lua chon tinh  calo theo goii tinh

        switch (view.getId())
        {
            case R.id.radio_other:
                if (ckeck)
                {
                    chisocalo=0.95;
                    btn_tinhcalo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cannanghientai=Integer.valueOf(String.valueOf((edt_hientai.getText())));
                            calohientai=(int)(cannanghientai*chisocalo*24);
                            txt_calohientai.setText(String.valueOf((int) calohientai));

                            cannangmongmuon=Integer.valueOf(String.valueOf(edt_muctieu.getText()));
                            calomuctieu=(int)(cannangmongmuon*chisocalo*24);
                            txt_calomuctieu.setText(String.valueOf((int) calomuctieu));
                        }
                    });
                }
                break;
            case R.id.radio_male:
                if (ckeck)
                {
                    chisocalo=1;
                    btn_tinhcalo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cannanghientai=Integer.valueOf(String.valueOf((edt_hientai.getText())));
                            calohientai=(int)(cannanghientai*chisocalo*24);
                            txt_calohientai.setText(String.valueOf((int) calohientai));

                            cannangmongmuon=Integer.valueOf(String.valueOf(edt_muctieu.getText()));
                            calomuctieu=(int)(cannangmongmuon*chisocalo*24);
                            txt_calomuctieu.setText(String.valueOf((int) calomuctieu));
                        }
                    });

                }
                break;
            case R.id.radio_female:
                if (ckeck)
                {
                    chisocalo=0.9;
                    btn_tinhcalo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cannanghientai=Integer.valueOf(String.valueOf((edt_hientai.getText())));
                            calohientai=(int)(cannanghientai*chisocalo*24);
                            txt_calohientai.setText(String.valueOf((int) calohientai));

                            cannangmongmuon=Integer.valueOf(String.valueOf(edt_muctieu.getText()));
                            calomuctieu=(int)(cannangmongmuon*chisocalo*24);
                            txt_calomuctieu.setText(String.valueOf((int) calomuctieu));
                        }
                    });

                }
                break;


        }
    }


}