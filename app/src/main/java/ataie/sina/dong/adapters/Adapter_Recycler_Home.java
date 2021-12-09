package ataie.sina.dong.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ataie.sina.dong.R;
import ataie.sina.dong.Result_Show;
import ataie.sina.dong.fragments.Dialog_Wanna_Delete;
import ataie.sina.dong.fragments.Home;
import ataie.sina.dong.models.Model_Spends;

public class Adapter_Recycler_Home extends RecyclerView.Adapter<Adapter_Recycler_Home.viewholder> {

    List<String>list_users;
    Context context;
    List<Model_Spends> list;
    FragmentManager fragmentManager;
    public Adapter_Recycler_Home(List<Model_Spends>list, Context context,FragmentManager fragmentManager){
        this.context=context;
        this.list=list;
        this.fragmentManager=fragmentManager;
        list_users=new ArrayList<>();
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_recycler_item,parent,false);;
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final Model_Spends model=list.get(position);
        holder.detail.setText(model.getDetail());
        String result_string= String.valueOf(model.getResult()+" تومان");
        holder.result.setText(result_string);
        holder.date.setText(model.getDate());
        Handle_Users(holder,position,model);
        Handle_click(holder,position);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewholder extends RecyclerView.ViewHolder{
      TextView detail,users,result,date;
      CardView card;
      ImageView image_remove;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            detail=itemView.findViewById(R.id.detail_list);
            users=itemView.findViewById(R.id.text_names);
            result=itemView.findViewById(R.id.result_item_home);
            card=itemView.findViewById(R.id.card_item_home);
            image_remove=itemView.findViewById(R.id.remove_home);
            date=itemView.findViewById(R.id.text_date);
        }
    }


        void Handle_click(viewholder holder,int pos){
        ///////////card
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context,String.valueOf(list.get(pos).getId()),Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(context, Result_Show.class);
                    intent.putExtra("index",list.get(pos).getId());
                    context.startActivity(intent);
                }
            });
            ///////////
            holder.image_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog_Wanna_Delete dialog_wanna_delete=new Dialog_Wanna_Delete();
                    dialog_wanna_delete.show(fragmentManager,String.valueOf(list.get(pos).getId())+"/"+pos);
                }
            });
            ////////////
        }

         void Handle_Users(viewholder holder,int pos,Model_Spends model){

             try {
                 JSONArray jsonArray=new JSONArray(model.getUsers());
                 for(int i=0;i<jsonArray.length();i++){
                     JSONObject jsonObject=jsonArray.getJSONObject(i);
                        list_users.add(jsonObject.getString("name"));
                 }

             } catch (JSONException e) {
                 e.printStackTrace();
             }
             String s = "";
             for(int i=0;i<list_users.size();i++){
                 if(i!=list_users.size()-1)s+=list_users.get(i)+",";
                 else s+=list_users.get(i);
             }
             holder.users.setText(s);
             list_users.clear();
         }


}
