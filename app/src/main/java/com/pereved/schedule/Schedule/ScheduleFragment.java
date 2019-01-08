package com.pereved.schedule.Schedule;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.pereved.schedule.Activity.MainActivity;
import com.pereved.schedule.R;

import java.util.Objects;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment {

    @BindView(R.id.logo_schedule)
    View logo;
    @BindDrawable(R.drawable.nav_menu)
    Drawable menu;
    @BindView(R.id.schedule_view_pager)
    MaterialViewPager scheduleViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.schedule_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initUI();
    }

    private void initUI() {
        setupToolbar();
        setupViewPager();
    }

    private void setupToolbar() {
        Objects.requireNonNull(getActivity()).setTitle("");
        Toolbar toolbar = scheduleViewPager.getToolbar();
        toolbar.setNavigationIcon(menu);
        ((MainActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        MainActivity.result.setToolbar(getActivity(), toolbar);
    }

    private void setupViewPager() {
        scheduleViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch(position % 4) {
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch(position % 4) {
                    case 0:
                        return "Понедельник";
                    case 1:
                        return "Вторник";
                    case 2:
                        return "Среда";
                    case 3:
                        return "Четверг";
                }
                return "";
            }
        });

        scheduleViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch(page) {
                    case 0:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.cyan, getResources().getDrawable(R.drawable.hd_schedule));
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green, getResources().getDrawable(R.drawable.hd_education));
                    case 2:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.cyan, getResources().getDrawable(R.drawable.hd_design));
                    case 3:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green, getResources().getDrawable(R.drawable.hd_math));
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        scheduleViewPager.getViewPager().setOffscreenPageLimit(scheduleViewPager.getViewPager().getAdapter().getCount());
        scheduleViewPager.getPagerTitleStrip().setViewPager(scheduleViewPager.getViewPager());

        if(logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scheduleViewPager.notifyHeaderChanged();
                    Toast.makeText(getContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}