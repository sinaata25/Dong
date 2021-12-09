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
import ataie.sina.dong.models.Model_Spends;

public class Adapter_Recycler_Add extends RecyclerView.Adapter<Adapter_Recycler_Add.viewholder> {

    public static Remove_Add remove_add;
    Context context;
    List<String> list;
    FragmentManager fragmentManager;
    public Adapter_Recycler_Add(List<String>list, Context context, FragmentManager fragmentManager){
        this.context=context;
        this.list=list;
        this.fragmentManager=fragmentManager;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recycle_add,parent,false);;
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        String data=list.get(position);
        holder.name.setText(data);
        Handle_click(holder,position);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewholder extends RecyclerView.ViewHolder{
      TextView name;
      ImageView image_remove;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            image_remove=itemView.findViewById(R.id.delete_add);
            name=itemView.findViewById(R.id.name_add);
        }
    }


        void Handle_click(viewholder holder,int pos){
        holder.image_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    remove_add.delete(pos);
            }
        });

        }


        public interface Remove_Add{
        void delete(int pos);
        }


}
