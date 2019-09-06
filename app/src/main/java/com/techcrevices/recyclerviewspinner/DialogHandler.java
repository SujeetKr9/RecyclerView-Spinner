package com.techcrevices.recyclerviewspinner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DialogHandler {

    public static void createSingleItemDialog(Activity activity, List<String> list, String title, OnClick listener){
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_single_item_dialog,null);
        TextView titleTextView = view.findViewById(R.id.title_textview);
        titleTextView.setText(title);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        AlertDialog dialog = prepareDialog(activity,view);

        final SingleItemAdapter adapter = new SingleItemAdapter(activity,list,dialog,listener);
        recyclerView.setAdapter(adapter);

        dialog.show();
    }

    private static AlertDialog prepareDialog(Activity activity, View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(view);
        return builder.create();
    }

    private static class SingleItemAdapter extends RecyclerView.Adapter<SingleItemAdapter.ViewHolder>{
        List<String> list;
        AlertDialog dialog;
        OnClick listener;
        Activity activity;

        SingleItemAdapter(Activity activity, List<String> list, AlertDialog dialog, OnClick listener){
            this.list = list;
            this.dialog = dialog;
            this.listener = listener;
            this.activity = activity;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(activity).inflate(R.layout.layout_single_item,viewGroup,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
            String item = list.get(i);
            holder.itemNameTextView.setText(item);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView itemNameTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemNameTextView = itemView.findViewById(R.id.item_textview);
                itemNameTextView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                listener.onClick(list.get(getAdapterPosition()));
                dialog.dismiss();
            }
        }
    }

    public interface OnClick {
        public void onClick(String item);
    }

}
