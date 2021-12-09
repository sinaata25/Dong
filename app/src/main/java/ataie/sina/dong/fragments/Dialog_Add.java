package ataie.sina.dong.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import ataie.sina.dong.Result_Show;
import ataie.sina.dong.adapters.Adapter_Recycler_Add;
import ataie.sina.dong.adapters.Adapter_Recycler_Home;
import ataie.sina.dong.free.Utilities;

public class Dialog_Add extends DialogFragment {
    EditText name,detail;
    ImageView add;
    LinearLayout go;
    RecyclerView recyclerView;
    Adapter_Recycler_Add adapter;
    String json_susers;
    Utilities utilities;

    Home home;
    public static News news;
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
        home=new Home();
        utilities=new Utilities();
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
                        dismiss();
                        Intent intent=new Intent(getContext(), Result_Show.class);
                        startActivity(intent);
                        make_json();
                        String date= utilities.getCurrentShamsidate();
                        home.database.addinfo(detail.getText().toString(),json_susers,date,0);
                        news.item_added();

                    }else {
                        Toast.makeText(getContext(),"یه عنوان اضافه کن...",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(),"هیچ همدنگیو اضاف نکردی!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        ///////////////////////////
        adapter.remove_add=new Adapter_Recycler_Add.Remove_Add() {
            @Override
            public void delete(int pos) {
                list.remove(pos);
                recyclerView.setAdapter(adapter);
            }
        };


    }



    void make_json(){
        json_susers="[";
        for(int i=0;i<list.size();i++){
            if(i!=list.size()-1){
                String s="{"+"\"name\":\""+list.get(i)+"\","+"\"id\":\""+i+"\","+"\"number\":\""+"\""+"}";
                json_susers+=s;
                json_susers+=",";
            }else {
                String s="{"+"\"name\":\""+list.get(i)+"\","+"\"id\":\""+i+"\","+"\"number\":\""+"\""+"}";
                json_susers+=s;
            }

        }

        json_susers+="]";

    }


    public interface News{
        void item_added();
    }




}
