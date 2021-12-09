package ataie.sina.dong;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.skydoves.powerspinner.PowerSpinnerView;
import com.skydoves.powerspinner.SpinnerAnimation;

import java.util.ArrayList;
import java.util.List;

import ataie.sina.dong.fragments.Home;
import ataie.sina.dong.models.Model_Pays;
import ataie.sina.dong.models.Model_Spends;

public class Result_Show extends AppCompatActivity {
        int index_table=0;
        Home home;
        EditText detail,pay;
        LinearLayout add;
        List<Model_Pays>list;
        List<Model_Spends>list_spends;
        List<String>list_names;
        PowerSpinnerView powerSpinnerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_show);
        SetUpViews();
        SetUps();
        Get_Data_Database();
        //SetUpViews1();
        //Handles();
    }

        private void SetUpViews(){
            detail=findViewById(R.id.result_text_detail);
            pay=findViewById(R.id.result_text_pay);
            add=findViewById(R.id.result_linear_add);
            powerSpinnerView=findViewById(R.id.result_text_who);
        }

    private void SetUps() {
        int index=getIntent().getIntExtra("index",-1);
        if(index==-1){
            Get_ID_IF_NULL();
        }else {
            index_table=index;
        }
        list=new ArrayList<>();
        list_spends=new ArrayList<>();
        list_names=new ArrayList<>();
        home=new Home();
        list_names.add("sina"); list_names.add("srf");
        list_names.add("سینا");
        list_names.add("ممد");
        powerSpinnerView.setItems(list_names);
    }

    private void Handles() {


    }


    void Get_ID_IF_NULL(){
        Home home=new Home();
        Cursor cursor= home.database.getinfo(0);
        cursor.moveToLast();
        index_table=cursor.getInt(0);
    }
    void Get_Data_Database(){
        /////////////database pays
       Cursor cursor=home.database.getinfo_raw(1,index_table);
        if(cursor!=null){
            //////////in for
            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
            {   //get data from database
                int id=cursor.getInt(0);
                int id_table=cursor.getInt(1);
                int id_name=cursor.getInt(2);
                String name=cursor.getString(3);
                int pays=cursor.getInt(5);
                String detail=cursor.getString(4);
                //set to arraylist
               Model_Pays model_pays=new Model_Pays();
                model_pays.setId(id);
                model_pays.setId_table(id_table);
                model_pays.setId_person(id_name);
                model_pays.setName(name);
                model_pays.setPays(pays);
                model_pays.setDetail(detail);
                list.add(model_pays);
                //
            }
            ///////////out for
        }

        //////////////////database spends

        Cursor cursor_1=home.database.getinfo_raw(0,index_table);
        if(cursor_1!=null){
            //////////in for
            for(cursor_1.moveToFirst();!cursor_1.isAfterLast();cursor_1.moveToNext())
            {   //get data from database
                int id=cursor_1.getInt(0);
                String details=cursor_1.getString(1);
                String users=cursor_1.getString(2);
                String date=cursor_1.getString(3);
                int result=cursor_1.getInt(4);
                //set to arraylist
                Model_Spends model_spends=new Model_Spends();
                model_spends.setId(id);
                model_spends.setDetail(details);
                model_spends.setDate(date);
                model_spends.setResult(result);
                model_spends.setUsers(users);
                list_spends.add(model_spends);
                //
            }
            ///////////out for
        }

        ///////////////////////
    }
    private void SetUpViews1() {


    }




}