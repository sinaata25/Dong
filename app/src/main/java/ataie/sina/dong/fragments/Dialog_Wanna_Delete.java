package ataie.sina.dong.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import ataie.sina.dong.R;

public class Dialog_Wanna_Delete extends DialogFragment {
    View view;
    TextView yes,no;
    String[] split;
    public  static Delete delete;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.dialog_wanna_delete,container,false);
        SetUpViews();
        Handle();
        return view;
    }


    private void SetUpViews() {
        yes=view.findViewById(R.id.yes);
        no=view.findViewById(R.id.no);
        split=getTag().split("/");

    }
    private void Handle() {

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            delete.remove(Integer.parseInt(split[0]),Integer.parseInt(split[1]));
            dismiss();
            }
        });


        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }


    public interface Delete{
        void remove(int position,int pos_recycle);
    }


}
