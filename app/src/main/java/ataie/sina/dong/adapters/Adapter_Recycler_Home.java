package ataie.sina.dong.adapters;

import android.content.Context;
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
import java.util.List;

import ataie.sina.dong.R;
import ataie.sina.dong.fragments.Dialog_Wanna_Delete;
import ataie.sina.dong.fragments.Home;
import ataie.sina.dong.models.Model_Spends;

public class Adapter_Recycler_Home extends RecyclerView.Adapter<Adapter_Recycler_Home.viewholder> {


    Context context;
    List<Model_Spends> list;
    FragmentManager fragmentManager;
    public Adapter_Recycler_Home(List<Model_Spends>list, Context context,FragmentManager fragmentManager){
        this.context=context;
        this.list=list;
        this.fragmentManager=fragmentManager;
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
        String result_string= String.valueOf(model.getResult());
        holder.result.setText(result_string);
        Handle_click(holder,position);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewholder extends RecyclerView.ViewHolder{
      TextView detail,users,result;
      CardView card;
      ImageView image_remove;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            detail=itemView.findViewById(R.id.detail_list);
            users=itemView.findViewById(R.id.text_names);
            result=itemView.findViewById(R.id.result_item_home);
            card=itemView.findViewById(R.id.card_item_home);
            image_remove=itemView.findViewById(R.id.remove_home);
        }
    }


        void Handle_click(viewholder holder,int pos){
        ///////////card
            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,String.valueOf(list.get(pos).getId()),Toast.LENGTH_SHORT).show();
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

/*            Dialog_Wanna_Delete dialog_wanna_delete=new Dialog_Wanna_Delete();
            dialog_wanna_delete.delete=new Dialog_Wanna_Delete.Delete() {
                @Override
                public void remove(int position, int pos_recycle) {
                    notifyItemRemoved(pos_recycle);

                }
            };*/



        }




}
