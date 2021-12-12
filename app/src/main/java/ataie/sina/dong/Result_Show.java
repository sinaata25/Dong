package ataie.sina.dong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.skydoves.powerspinner.PowerSpinnerView;
import com.skydoves.powerspinner.SpinnerAnimation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ataie.sina.dong.adapters.Adapter_Recycler_Result;
import ataie.sina.dong.fragments.Dialog_hazine_list;
import ataie.sina.dong.fragments.Home;
import ataie.sina.dong.free.Utilities;
import ataie.sina.dong.models.Model_Cal;
import ataie.sina.dong.models.Model_Cal1;
import ataie.sina.dong.models.Model_Pays;
import ataie.sina.dong.models.Model_Spends;

public class Result_Show extends AppCompatActivity {
        int index_table=0;
        Home home;
        RecyclerView recyclerView;
        EditText detail,pay;
        LinearLayout add,list_show;
        List<Model_Pays>list;
        List<Model_Spends>list_spends;
        List<String>list_names;
        List<Model_Cal>list_cal;
        List<Model_Cal1>list_cal_1;
        PowerSpinnerView powerSpinnerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_show);
        SetUpViews();
        SetUps();
        Get_Data_Database();
        List_to_List();
        Calculate_Dong();
        Handle();




    }


    private void SetUpViews(){
            detail=findViewById(R.id.result_text_detail);
            pay=findViewById(R.id.result_text_pay);
            add=findViewById(R.id.result_linear_add);
            powerSpinnerView=findViewById(R.id.result_text_who);
            list_show=findViewById(R.id.result_linear_list);
            recyclerView=findViewById(R.id.recycler_result);

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
        list_cal=new ArrayList<>();
        list_cal_1=new ArrayList<>();
        home=new Home();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
    }
    private void Get_ID_IF_NULL(){
        Home home=new Home();
        Cursor cursor= home.database.getinfo(0);
        cursor.moveToLast();
        index_table=cursor.getInt(0);
    }
    private void Get_Data_Database_0(){
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



    }
    private void Get_Data_Database(){
          Get_Data_Database_0();
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
    private void List_to_List() {
        String users=list_spends.get(0).getUsers();
        JSONArray jsonArray= null;
        try {
            jsonArray = new JSONArray(users);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                list_names.add(jsonObject.getString("name"));
            }
            powerSpinnerView.setItems(list_names);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    private void Handle() {
        //////////////////////////
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!detail.getText().toString().equals("")){
                    if(!pay.getText().toString().equals("")){
                        if(!powerSpinnerView.getText().toString().equals("")){
                            Utilities utilities=new Utilities();
                            home.database.addinfo_1(index_table,powerSpinnerView.getSelectedIndex(),powerSpinnerView.getText().toString(),detail.getText().toString(),Integer.parseInt(pay.getText().toString()),utilities.getCurrentShamsidate());
                           powerSpinnerView.clearSelectedItem();
                           detail.setText("");
                           pay.setText("");
                           //Calculate_Dong();
                        }else {
                            Toast.makeText(getApplicationContext(),"یه هزینه کننده اضافه کن...",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(),"مقدار هزینتو اضافه کن...",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"یه عنوان برای خریدت اضافه کن...",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //////////////////////////
        list_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_hazine_list dialog_hazine_list=new Dialog_hazine_list();
                dialog_hazine_list.show(getSupportFragmentManager(),index_table+"");
            }
        });



    }
    private void Calculate_Dong(){
        List<Integer>list_repete=new ArrayList<>();
        int sum=0;
        int avg=0;
        for(int i=0;i<list.size();i++){
            Model_Pays model_pays=list.get(i);
            sum+=model_pays.getPays();
        }
        int sum_person=0;
        for(int i=0;i<list.size();i++){
            //start for i
            Model_Pays model_pays= list.get(i);
            int id_person=model_pays.getId_person();
            if(Check_Theres(id_person,list_repete)==0) {
                list_repete.add(id_person);
                sum_person += model_pays.getPays();
                for (int j = i + 1; j < list.size(); j++) {
                    model_pays = list.get(j);
                    if (model_pays.getId_person() == id_person) {
                        sum_person += model_pays.getPays();
                    }

                }
                model_pays = list.get(i);
                Model_Cal model_cal = new Model_Cal();
                model_cal.setName(model_pays.getName());
                model_cal.setHazine(sum_person);
                list_cal.add(model_cal);
                sum_person=0;
                //end of for i
            }
        }

        for(int i=0;i<list_names.size();i++){
            int key=0;
            for(int j=0;j<list_repete.size();j++){
                if(list_names.get(i).equals(list.get(j).getName())){
                    key=1;
                    break;
                }else {
                    key=0;
                }
            }
            if(key==0){
                Model_Cal model_cal=new Model_Cal();
                model_cal.setName(list_names.get(i));
                model_cal.setHazine(0);
                list_cal.add(model_cal);
            }

        }


        /////////////////////////////////ta inja list hazine ha kamelan moratab shode
        avg=sum/list_names.size();
        for(int i=0;i<list_cal.size();i++){
            Model_Cal model_cal=list_cal.get(i);
            model_cal.setMosman(model_cal.getHazine()-avg);
        }
        //////////////////////////////////ta inja mosbat manfi haram set kardim


        for(int i=0;i<list_cal.size();i++){
            if(list_cal.get(i).getMosman()>0){
                for(int j=0;j<list_cal.size();j++){
                    if(list_cal.get(j).getMosman()<0 && list_cal.get(i).getMosman()>0 ){
                        Model_Cal1 model_cal1=new Model_Cal1();
                        model_cal1.setStart_name(list_cal.get(j).getName());
                        model_cal1.setEnd_name(list_cal.get(i).getName());
                       int s=list_cal.get(i).getMosman()+list_cal.get(j).getMosman();
                        list_cal.get(i).setMosman(s);
                        model_cal1.setMoney(list_cal.get(j).getMosman()*-1);
                        list_cal.get(j).setMosman(0);
                        list_cal_1.add(model_cal1);
                    }
                }

            }


        }

        recyclerView.setAdapter(new Adapter_Recycler_Result(list_cal_1,getApplicationContext()));



    }
    int Check_Theres(int num,List<Integer>lists){
        int what=0;
        for(int i=0;i<lists.size();i++){
            if(num==lists.get(i)){
               what=1;
            }
        }
        return what;
    }











}