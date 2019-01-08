package com.pereved.schedule.Teachers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pereved.schedule.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//Created by Squirty on 16.12.2018.
public class PeoplesAdapter extends RecyclerView.Adapter<PeoplesAdapter.RecyclerViewHolder> {

    private Context context;
    private List<String> items;

    PeoplesAdapter(Context context) {
        this.context = context;
        items = new ArrayList<>();
    }

    public void setItems(List<String> data) {
        this.items.addAll(data);
        notifyDataSetChanged();
    }

    public void addItem(int position, String insertData) {
        items.add(position, insertData);
        notifyItemInserted(position);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.teachers_item, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        holder.bind(items.get(position));

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_recycler_item_show);
        holder.view.startAnimation(animation);

        AlphaAnimation aa1 = new AlphaAnimation(1.0f, 0.1f);
        aa1.setDuration(400);
        holder.rela_round.startAnimation(aa1);

        AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
        aa.setDuration(400);

        holder.rela_round.startAnimation(aa);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView title;
//        private ImageView image;
        private RelativeLayout rela_round;


        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            rela_round = itemView.findViewById(R.id.rela_round);
//            image = itemView.findViewById(R.id.image);
//            title = itemView.findViewById(R.id.title);
        }

//        void bind(TeachersItem item) {
//            image.setImageBitmap(item.bitmap);
//            title.setText(item.name);
//        }
    }
}