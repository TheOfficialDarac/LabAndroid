package com.example.lv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {
    Context context;
    ArrayList<Student> student;
    public StudentAdapter(Context context, ArrayList<Student> student) {
        this.context = context;
        this.student = student;
    }

    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_main_row_view, parent, false);
        return new StudentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, int position) {
        holder.tvIme.setText(student.get(position).getIme());
        holder.tvPrezime.setText(student.get(position).getPrezime());
        holder.tvPredmet.setText(student.get(position).getPredmet());
    }

    @Override
    public int getItemCount() {
        return student.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvIme, tvPrezime, tvPredmet;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIme = itemView.findViewById(R.id.cardIme);
            tvPrezime = itemView.findViewById(R.id.cardPrezime);
            tvPredmet = itemView.findViewById(R.id.cardPredmet);
        }
    }
}
