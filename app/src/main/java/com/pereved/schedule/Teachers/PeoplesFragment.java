package com.pereved.schedule.Teachers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.pereved.schedule.Activity.MainActivity;
import com.pereved.schedule.R;
import com.pereved.schedule.Schedule.RecyclerViewFragment;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

//Created by Squirty on 24.12.2018.
public class PeoplesFragment extends Fragment {

    @BindView(R.id.logo_peoples)
    View logo;
    @BindView(R.id.peoples_view_pager)
    MaterialViewPager peoplesViewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.peoples_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupViewPager();
    }

    @Override
    public void onResume() {
        super.onResume();
        setupToolbar();
    }

    private void setupToolbar() {
        Objects.requireNonNull(getActivity()).setTitle("");
        Toolbar toolbar = peoplesViewPager.getToolbar();
        ((MainActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        MainActivity.result.setToolbar(getActivity(), toolbar);
    }

    private void setupViewPager() {
        peoplesViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {

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
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch(position % 2) {
                    case 0:
                        return "Преподаватели";
                    case 1:
                        return "Обучающиеся";
                }
                return "";
            }
        });

        peoplesViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch(page) {
                    case 0:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.cyan, getResources().getDrawable(R.drawable.header_peoples));
                    case 1:
                        return HeaderDesign.fromColorResAndDrawable(
                                R.color.green, getResources().getDrawable(R.drawable.hd_education));
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        peoplesViewPager.getViewPager().setOffscreenPageLimit(peoplesViewPager.getViewPager().getAdapter().getCount());
        peoplesViewPager.getPagerTitleStrip().setViewPager(peoplesViewPager.getViewPager());

        if(logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    peoplesViewPager.notifyHeaderChanged();
                    Toast.makeText(getContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
