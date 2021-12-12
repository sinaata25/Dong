package ataie.sina.dong.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ataie.sina.dong.R;
import ataie.sina.dong.models.Model_Pays;

public class Adapter_Recycler_ListHazine extends RecyclerView.Adapter<Adapter_Recycler_ListHazine.viewholder> {

    Context context;
    List<Model_Pays> list;
    public Adapter_Recycler_ListHazine(List<Model_Pays>list, Context context){
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recycle_list_hazine,parent,false);;
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Model_Pays model_pays=list.get(position);
        holder.txt_name.setText(model_pays.getName());
        holder.txt_detail.setText(model_pays.getDetail());
        holder.txt_date.setText(model_pays.getDate());
        holder.txt_haazine.setText(model_pays.getPays()+"");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewholder extends RecyclerView.ViewHolder{
        TextView txt_name,txt_detail,txt_date,txt_haazine;
        ImageView delete;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txt_name=itemView.findViewById(R.id.list_txt_name);
            txt_date=itemView.findViewById(R.id.list_txt_date);
            txt_detail=itemView.findViewById(R.id.list_txt_detail);
            txt_haazine=itemView.findViewById(R.id.list_txt_price);
            delete=itemView.findViewById(R.id.list_img_delete);

        }
    }







}
