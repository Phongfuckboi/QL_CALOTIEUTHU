package com.example.congnghephanmembtl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import DTO.eat;
import fragment.HomeFragment;
import fragment.PersonFragment;
import fragment.favouritFragment;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation;
    private ActionBar actionBar;
    String id,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        eat eat=new eat();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("Eat").push().setValue(eat);


        Intent intent= getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");





//        meowBottomNavigation= findViewById(R.id.bottom_navagation);
//
//        //add item in bottom navagation
//        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_home_24));
//        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_favorite_24));
//        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_person_pin_24));

        actionBar = getSupportActionBar();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
       navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
       loadfragment(new favouritFragment());

    }


//        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener()
//    {
//        @Override
//        public void onShowItem (MeowBottomNavigation.Model item){
//        Fragment fragment = null;
//        //check id
//        switch (item.getId()) {
//
//            case 1:
//                fragment = new HomeFragment();
//                break;
//            case 2:
//                fragment = new favouritFragment();
//                break;
//            case 3:
//                fragment = new PersonFragment();
//                break;
//
//
//        }
//        loadfragment(fragment);
//
//    }
//    }

//}
//        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
//            @Override
//            public void onClickItem(MeowBottomNavigation.Model item) {
//
//                switch (item.getId()) {
//                    case 1:
//                        Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                        Toast.makeText(MainActivity.this, "favourit", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 3:
//                        Toast.makeText(MainActivity.this, "Person", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        });



        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.navigation_overview:
                        fragment = new favouritFragment();
                        Bundle bundle2=new Bundle();
                        bundle2.putString("id",id);
                        fragment.setArguments(bundle2);
                        loadfragment(fragment);
                        break;
                    case R.id.navigation_home:
                        fragment = new HomeFragment();
                        Bundle bundl1=new Bundle();
                        bundl1.putString("id",id);
                        fragment.setArguments(bundl1);
                        loadfragment(fragment);
                        break;
                    case R.id.navigation_person:
                        fragment = new PersonFragment();
                        Bundle bundle=new Bundle();
                        bundle.putString("id",id);
                        bundle.putString("name",name);
                        fragment.setArguments(bundle);
                        loadfragment(fragment);
                        break;

                }

                return false;
            }
        };
    private void loadfragment (Fragment fragment){
        //rerplace fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    }

