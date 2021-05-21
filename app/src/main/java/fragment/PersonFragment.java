package fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.example.congnghephanmembtl.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import DTO.History;
import DTO.overview;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonFragment extends Fragment {
    private DatabaseReference ref_history, ref_overview;
    Date today = new Date();
    ArrayList <History>arrayhistory;
    ArrayAdapter arrayAdapterhis;
    ListView list_history;
    Button btn_chonngay;
    CalendarView calendarView;
    String ngaychon;
    TextView txt_ngaychon ,txt_tenNV,test;
    Button Btn_chonngay1;
    static int sumOfCalories;
    static int sumOfMoveCal;
    static int sumOfEatCal;
    String id;
    String name;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonFragment newInstance(String param1, String param2) {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_person, container, false);

        //chon ngay

        test=(TextView) view.findViewById(R.id.test);
        txt_tenNV=(TextView) view.findViewById(R.id.txt_tenND);
        btn_chonngay=(Button) view.findViewById(R.id.choosedate);

        //
        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            id=bundle.getString("id");
            name=bundle.getString("name");
            txt_tenNV.setText("HELLO:"+name);

        }
        else {
           System.out.println("loi");
        }



        //
        btn_chonngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog();

            }
        });

        ////get dữ liệu
        final String date1 = today.getYear() + 1900 + "/" + (1 + today.getMonth()) + "/" +( today.getDate());
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        ref_history=FirebaseDatabase.getInstance().getReference();
        arrayhistory=new ArrayList<>();
        list_history=(ListView) view.findViewById(R.id.historyListView) ;
        ref_history.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for(DataSnapshot history:snapshot.child("History").getChildren())
               {
                   History history1=history.getValue(History.class);
                   String datehis=history1.getDate().toString();
                   String Ktid=history1.getId().toString();

                   if(date1.equals(datehis)&& id.equals(Ktid) ) {
                       arrayhistory.add(history1);
                   }

               }

               arrayAdapterhis=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,arrayhistory);
               list_history.setAdapter(arrayAdapterhis);
               arrayAdapterhis.notifyDataSetInvalidated();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

        return view;

    }

    public  void Dialog()
    {
        Dialog dialog=new Dialog(getContext());
        dialog.setContentView(R.layout.chon_ngay);
        dialog.show();
        calendarView=(CalendarView) dialog.findViewById(R.id.clendar);
        txt_ngaychon=(TextView) dialog.findViewById(R.id.txt_ngaychon);
        Btn_chonngay1=(Button) dialog.findViewById(R.id.btn_ok);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                ngaychon= year + "/" + (month+1) + "/" + (dayOfMonth);
                txt_ngaychon.setText(ngaychon);

            }
        });
        Btn_chonngay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayhistory.clear();
                final String date2 = today.getYear() + 1900 + "/" + (1 + today.getMonth()) + "/" + today.getDate();
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                ref_history=FirebaseDatabase.getInstance().getReference();
                ref_history.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arrayhistory.clear();
                        for(DataSnapshot history:snapshot.child("History").getChildren())
                        {
                            String a=txt_ngaychon.getText().toString();
                            History history1=history.getValue(History.class);
                            String datehis=history1.getDate().toString();
                            String iduser=history1.getId().toString();
                            if(datehis.equals(a)&& iduser.equals(id)) {
                                arrayhistory.add(history1);
                                tinhtong();

                            }

                        }
                        arrayAdapterhis=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,arrayhistory);
                        list_history.setAdapter(arrayAdapterhis);
                        arrayAdapterhis.notifyDataSetInvalidated();

                    }

                    private void tinhtong() {
                        sumOfEatCal = 0;
                        sumOfMoveCal = 0;
                        for (int i = 0; i < arrayhistory.size(); i++) {
                            sumOfCalories += Integer.valueOf(arrayhistory.get(i).getTotalCalories());
                            if (Integer.valueOf(arrayhistory.get(i).getTotalCalories()) > 0) {
                                sumOfEatCal += Integer.valueOf(arrayhistory.get(i).getTotalCalories());

                            } else {
                                sumOfMoveCal += Integer.valueOf(arrayhistory.get(i).getTotalCalories());
                            }
                        }
                    }
            @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });
                dialog.dismiss();
            }
        });


    }


}