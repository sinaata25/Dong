package ataie.sina.dong.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ataie.sina.dong.R;
import ataie.sina.dong.adapters.Adapter_Recycler_Home;
import ataie.sina.dong.database.Database;
import ataie.sina.dong.models.Model_Spends;

public class Home extends Fragment {
    List<Model_Spends>list;
    public static   Database database;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    Adapter_Recycler_Home adapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.home,container,false);
        SetupViews();
        Get_Data();
        Handles();
        Handle_Clicks();
        return view;
    }



    private void SetupViews() {
        database=new Database(getContext());
        list=new ArrayList<>();
        recyclerView=view.findViewById(R.id.recyclerView_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        adapter=new Adapter_Recycler_Home(list,getContext(),getParentFragmentManager());
        floatingActionButton=view.findViewById(R.id.floatingActionButton_home);


    }
    private void Get_Data() {
        Cursor cursor=database.getinfo(0);
        if(cursor!=null){
            //////////in for
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
            {   //get data from database
                int id=cursor.getInt(0);
                String details=cursor.getString(1);
                String users=cursor.getString(2);
                String date=cursor.getString(3);
                int result=cursor.getInt(4);
                //set to arraylist
                Model_Spends model_spends=new Model_Spends();
                model_spends.setId(id);
                model_spends.setDetail(details);
                model_spends.setDate(date);
                model_spends.setResult(result);
                model_spends.setUsers(users);
                list.add(model_spends);
                //
            }
            ///////////out for
        }
        Sets();

    }


    private void Sets() {
    recyclerView.setAdapter(adapter);
    }



    private void Handles() {
Dialog_Wanna_Delete dialog_wanna_delete=new Dialog_Wanna_Delete();
Dialog_Wanna_Delete.delete=new Dialog_Wanna_Delete.Delete() {
    @Override
    public void remove(int position,int pos_recycle) {
        try {
            database.delet_row(position);
            list.clear();
            Get_Data();
           // adapter.notifyItemRemoved(pos_recycle);
        }catch (Exception e){
            Log.i("TAG", "remove error..........: "+e);
        }

    }
};

////////////
Dialog_Add dialog_add=new Dialog_Add();
Dialog_Add.news=new Dialog_Add.News() {
    @Override
    public void item_added() {
        list.clear();
        Get_Data();
    }
};







    }



    private void Handle_Clicks() {

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Dialog_Add dialog_add=new Dialog_Add();
            dialog_add.show(getParentFragmentManager(),"");
            }
        });
    }










}
