package com.example.congnghephanmembtl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DoimatkhauActivity extends AppCompatActivity {

    private  TextView txt_tennd;
    private ImageView img_admin;
    private  Button btn_thoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimatkhau);
        txt_tennd=(TextView)  findViewById(R.id.txt_ten);
        img_admin=(ImageView) findViewById(R.id.img_admin);

        Intent intent=getIntent();
        String tennd=intent.getStringExtra("name");
        txt_tennd.setText("HELLO:"+tennd);

        img_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog =new Dialog(DoimatkhauActivity.this);
                dialog.setContentView(R.layout.diolog_xoadata);
                dialog.show();
                Button btn_ok= (Button) dialog.findViewById(R.id.btn_xoadata);
                EditText edt_maxacnhan= (EditText) dialog.findViewById(R.id.edt_maxacnhan);

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ((edt_maxacnhan.getText().toString()).equals("992000"))
                        {
                            Intent intent=new Intent(DoimatkhauActivity.this,adminActiviti.class);
                            Toast.makeText(DoimatkhauActivity.this,"phong",Toast.LENGTH_LONG).show();
                            startActivity(intent);

                        }

                    }
                });
            }
        });
    }
}