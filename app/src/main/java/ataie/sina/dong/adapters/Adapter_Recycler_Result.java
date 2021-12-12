package ataie.sina.dong.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ataie.sina.dong.R;
import ataie.sina.dong.models.Model_Cal1;
import ataie.sina.dong.models.Model_Pays;

public class Adapter_Recycler_Result extends RecyclerView.Adapter<Adapter_Recycler_Result.viewholder> {

    Context context;
    List<Model_Cal1> list;
    public Adapter_Recycler_Result(List<Model_Cal1>list, Context context){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recycler_result,parent,false);;
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
            Model_Cal1 model_cal1=list.get(position);
            holder.start.setText(model_cal1.getStart_name());
            holder.end.setText(model_cal1.getEnd_name());
            holder.hazine.setText(model_cal1.getMoney()+"تومان");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewholder extends RecyclerView.ViewHolder{
        TextView start,hazine,end;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            start=itemView.findViewById(R.id.item_result_name_first);
            hazine=itemView.findViewById(R.id.item_result_hazine);
            end=itemView.findViewById(R.id.item_result_name_end);

        }
    }







}
