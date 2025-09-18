package com.william.bobofooddeliveryapp.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.william.bobofooddeliveryapp.R;
import com.william.bobofooddeliveryapp.UI.LoginActivity;

import java.util.List;


public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.ViewHolder> {

    private List<Integer> layouts;
    private LayoutInflater inflater;

    public OnboardingAdapter(Context context, List<Integer> layouts) {
        this.layouts = layouts;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("index ->"+viewType);

        View view = inflater.inflate(layouts.get(viewType), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == layouts.size() - 1) {
            Button btnEmpezar = holder.itemView.findViewById(R.id.btn_view_Login);
            if (btnEmpezar != null) {
                btnEmpezar.setOnClickListener(v -> {
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                    ((Activity) context).finish(); // cerrar Onboarding
                });
            }
        }
    }


    @Override
    public int getItemCount() {
        return layouts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
