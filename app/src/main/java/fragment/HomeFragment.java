package fragment;

import android.app.Dialog;
import android.content.Intent;
import android.media.midi.MidiOutputPort;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.congnghephanmembtl.R;
import com.example.congnghephanmembtl.TapLuyenactivity;
import com.example.congnghephanmembtl.ThemThucDonActivity;
import com.example.congnghephanmembtl.TinhCaloFoodActivity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import DTO.Move;
import DTO.eat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private DatabaseReference ref_eat, ref_move;
    ArrayAdapter arrayAdaptereat;
    ArrayAdapter  arrayAdapterMOve;
    ArrayList<eat>  arr_eat;
    ArrayList   arr_move;
    Button btn_themthucdon,btn_tapluyen,btn_chontuds,btn_thucdon;
    ListView lst_eat;
    String iduser;
    private  static  int QUYEN=1;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        //
       btn_themthucdon=(Button) view.findViewById(R.id.btn_themthucdon);
       btn_chontuds=(Button) view.findViewById(R.id.btn_chontuds);
       btn_tapluyen=(Button) view.findViewById(R.id.btn_tapluyen);
       btn_thucdon =(Button) view.findViewById(R.id.btn_thucdon);
       lst_eat=(ListView) view.findViewById(R.id.lst_home) ;


       //get to databasr from eat
        arr_eat = new ArrayList<>();
        arrayAdaptereat = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, arr_eat);
        lst_eat.setAdapter(arrayAdaptereat);


       //get id
        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            iduser=bundle.getString("id");

        }
        else {
            Toast.makeText(getContext(),"loi",Toast.LENGTH_SHORT).show();
        }






        btn_themthucdon.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getContext(), ThemThucDonActivity.class);
               startActivity(intent);
           }
       });

        //
        //
        thucdon();
        btn_thucdon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thucdon();
            }
        });


        btn_tapluyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getContext(), TapLuyenactivity.class);
                intent.putExtra("id",iduser);
                startActivity(intent);

            }
        });

        Intent intent = new Intent(getContext(), TinhCaloFoodActivity.class);
        lst_eat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String data= (String) arr_eat.get(position);
                eat eat=  arr_eat.get(position);
                intent.putExtra("id",iduser);
                intent.putExtra("food",eat.getFood());
                intent.putExtra("calo",eat.getCalories());
//                intent.putExtra("data",data);

                startActivity(intent);
            }
        });

        //quyen cua admin
        lst_eat.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return false;
            }
        });
        return view;
    }



    private void thucdon() {
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        ref_eat=FirebaseDatabase.getInstance().getReference();
        ref_eat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot hs : snapshot.child("Eat").getChildren()) {
                    eat eat1 = hs.getValue(DTO.eat.class);
                    arr_eat.add(eat1);
                    arrayAdaptereat.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



    }
}