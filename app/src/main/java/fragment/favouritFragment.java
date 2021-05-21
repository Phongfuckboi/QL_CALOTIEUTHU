package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.congnghephanmembtl.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DTO.History;
import DTO.Info;
import DTO.overview;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link favouritFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class favouritFragment extends Fragment {

    private DatabaseReference ref_tongquan;
    private  DatabaseReference ref_info;
    private  DatabaseReference ref_eat;
    private  DatabaseReference red_move;
    private  DatabaseReference red_his;
    int tongcalotrongngay=0;
    Date today= new Date();
    TextView txt_calodexuat , txt_calodanap, txt_calotieuthu;
    overview overview;
    String getcalo;
    int tongcaloan;
    int tongcalotieuthu;
    PieChart pieChart;
    private PieData pieData;
    ArrayList<History>  historyArrayList;
    int sumOfEatCal = 0;
    int sumOfMoveCal = 0;
    String id;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public favouritFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment favouritFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static favouritFragment newInstance(String param1, String param2) {
        favouritFragment fragment = new favouritFragment();
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
        View view=inflater.inflate(R.layout.fragment_favourit, container, false);

        //
        txt_calodexuat=(TextView) view.findViewById(R.id.basicCalories);
        txt_calodanap=(TextView)  view.findViewById(R.id.gotCalories);
        txt_calotieuthu=(TextView) view.findViewById(R.id.lostCalories);
        //
        //get id tai khoan
        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            id=bundle.getString("id");
        }
        else {
            System.out.println("loi");
        }


        //
        pieChart= view.findViewById(R.id.piechart);
        ArrayList<PieEntry> vector= new ArrayList<>();
        vector.add(new PieEntry(1250,"getcalo"));
        vector.add(new PieEntry(-1260,"lostcalo"));
        vector.add(new PieEntry(3450,"getcalo"));
        vector.add(new PieEntry(-1260,"lostcalo"));

        PieDataSet pieDataSet= new PieDataSet (vector,"Today");
        pieDataSet.setColor(ColorTemplate.COLORFUL_COLORS[0]+ColorTemplate.COLORFUL_COLORS[1]+ColorTemplate.COLORFUL_COLORS[2]+ColorTemplate.COLORFUL_COLORS[4]);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(10f);

        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("ToDay");
        pieChart.animate();


        //
        ref_info=FirebaseDatabase.getInstance().getReference();
        ref_info.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datainfo: snapshot.child("InFo").getChildren())
                {

                    Info info=datainfo.getValue(Info.class);
                    String ktid=String.valueOf(info.getId());
                    if((info.getCalorieshientai())!=0 && ktid.equals(id)) {
                        int calodexuat = info.getCaloriesmuctieu();
                        txt_calodexuat.setText(String.valueOf(calodexuat));
                    }
                    else {
                       System.out.println("loi");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //set gia tri cho overview
        setgiatriOverview();

        //Ve bieu do
        Hienthibieudo();

        return view;

    }

    private void Hienthibieudo() {

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        ArrayList <overview> arraygetlost=new ArrayList<>();
        tongcaloan=0;
        tongcalotieuthu=0;
        ref_tongquan=FirebaseDatabase.getInstance().getReference();
        ref_tongquan.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snaptongquan: snapshot.child("Overview").getChildren())
                {
                    overview overview=snaptongquan.getValue(DTO.overview.class);
                    arraygetlost.add(overview);
                    for (int i=0; i< arraygetlost.size();i++) {

                            tongcaloan += Integer.valueOf(arraygetlost.get(i).getSumOfEatCal());
                            tongcalotieuthu+=Integer.valueOf(arraygetlost.get(i).getSumOfMoveCal());

                    }
                }
//                PieDataSet pieDataSet= new PieDataSet ((List<PieEntry>) overview,"Today");
//                pieDataSet.setColor(Color.BLACK);
//                pieDataSet.setValueTextColor(Color.BLACK);
//                pieDataSet.setValueTextSize(10f);
//
//                pieData = new PieData(pieDataSet);
//                pieChart.setData(pieData);
//                pieChart.getDescription().setEnabled(false);
//                pieChart.setCenterText("ToDay");
//                pieChart.animate();



//               txt_calodanap.setText(String.valueOf(tongcaloan));
//               txt_calotieuthu.setText(String.valueOf(tongcalotieuthu));
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setgiatriOverview() {

        //
        final String ngayhientai = today.getYear() + 1900 + "/" + (1 + today.getMonth()) + "/" + today.getDate();
        //
        FirebaseDatabase data=FirebaseDatabase.getInstance();
        red_his=FirebaseDatabase.getInstance().getReference();
        red_his.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot history : snapshot.child("History").getChildren()) {
                    historyArrayList = new ArrayList<>();
                    History history1 = history.getValue(History.class);
                    String datehis = history1.getDate().toString();
                    String ktiduser=history1.getId().toString();
                    if (datehis.equals(ngayhientai)&& ktiduser.equals(id)) {
                        historyArrayList.add(history1);
                        setgiatri();
                    }

                }
                txt_calodanap.setText(""+sumOfEatCal);
                txt_calotieuthu.setText(String.valueOf(sumOfMoveCal));
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void setgiatri() {
        for (int i = 0; i < historyArrayList.size(); i++) {
            if (Integer.valueOf(historyArrayList.get(i).getTotalCalories()) > 0) {
                sumOfEatCal += Integer.valueOf(historyArrayList.get(i).getTotalCalories());

            } else {
                sumOfMoveCal += Integer.valueOf(historyArrayList.get(i).getTotalCalories());
            }

        }
    }
}
