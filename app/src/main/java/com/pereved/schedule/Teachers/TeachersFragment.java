package com.pereved.schedule.Teachers;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.pereved.schedule.R;
import com.pereved.schedule.Schedule.ScheduleAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

//Created by Squirty on 08.01.2019.
public class TeachersFragment extends Fragment {

    @BindView(R.id.teachers_recycler) RecyclerView recyclerView;
    private List<String> data;

    public static TeachersFragment newInstance() {
        return new TeachersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.teachers_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (getScreenWidthDp() >= 1200) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else if (getScreenWidthDp() >= 800) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        recyclerView.setHasFixedSize(true);

        RecyclerView.Adapter adapter = new PeoplesAdapter(getContext());
        ((PeoplesAdapter) adapter).setItems(getData());
        recyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        recyclerView.setAdapter(adapter);
    }

    public List<String> getData() {
        data = new ArrayList<>();
        for (int i = 1; i <= 20; i++)
            data.add("");

        return data;
    }

    private int getScreenWidthDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }
}