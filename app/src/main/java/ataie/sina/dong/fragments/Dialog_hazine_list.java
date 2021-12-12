package ataie.sina.dong.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ataie.sina.dong.R;
import ataie.sina.dong.adapters.Adapter_Recycler_ListHazine;
import ataie.sina.dong.models.Model_Pays;

public class Dialog_hazine_list extends DialogFragment {
            View view;
            Home home;
            RecyclerView recyclerView;
            List<Model_Pays>list;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_list_hazine,container,false);
        SetUpViews();
        Sets();
        Get_Data_Database_0();
        return view;
    }



    private void SetUpViews() {
        recyclerView=view.findViewById(R.id.recycle_hazine_list);

    }
    private void Sets() {
        home=new Home();
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
    }



    private void Get_Data_Database_0(){
        /////////////database pays
        Cursor cursor=home.database.getinfo_raw(1,Integer.parseInt(getTag()));
        if(cursor!=null){
            //////////in for
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
            {   //get data from database
                int id=cursor.getInt(0);
                int id_table=cursor.getInt(1);
                int id_name=cursor.getInt(2);
                String name=cursor.getString(3);
                String detail=cursor.getString(4);
                String date=cursor.getString(5);
                int pays=cursor.getInt(6);

                //set to arraylist
                Model_Pays model_pays=new Model_Pays();
                model_pays.setId(id);
                model_pays.setId_table(id_table);
                model_pays.setId_person(id_name);
                model_pays.setName(name);
                model_pays.setPays(pays);
                model_pays.setDate(date);
                model_pays.setDetail(detail);
                list.add(model_pays);
                //
            }
            ///////////out for
        }

      recyclerView.setAdapter(new Adapter_Recycler_ListHazine(list,getContext()));

    }















}
