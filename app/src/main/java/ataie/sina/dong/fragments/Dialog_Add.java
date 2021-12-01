package ataie.sina.dong.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ataie.sina.dong.R;
import ataie.sina.dong.adapters.Adapter_Recycler_Add;
import ataie.sina.dong.adapters.Adapter_Recycler_Home;

public class Dialog_Add extends DialogFragment {
    EditText name,detail;
    ImageView add;
    LinearLayout go;
    RecyclerView recyclerView;
    Adapter_Recycler_Add adapter;
    View view;
    List<String>list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.creat_transaaction,container,false);
        SetUpViews();
        Sets();
        Handles();

        return view;
    }



    void SetUpViews(){
        list=new ArrayList<>();
        add=view.findViewById(R.id.add_image);
        name=view.findViewById(R.id.name_text);
        detail=view.findViewById(R.id.detail_text);
        go=view.findViewById(R.id.go_add);
        recyclerView=view.findViewById(R.id.recycler_add);
    }
    private void Sets() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        adapter=new Adapter_Recycler_Add(list,getContext(),getParentFragmentManager());
    }

    private void Handles() {
        //////////
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!name.getText().toString().equals("")){
                    list.add(name.getText().toString());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyItemInserted(list.size());
                    name.setText("");
                }else {
                    Toast.makeText(getContext(),"اول یه اسم بنویس...",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ////////////
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size()!=0){
                    if(!detail.getText().toString().equals("")){
                        //go to next page...
                    }else {
                        Toast.makeText(getContext(),"یه عنوان اضافه کن...",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(),"هیچ همدنگیو اضاف نکردی!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




}
