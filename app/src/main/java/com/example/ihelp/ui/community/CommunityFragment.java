package com.example.ihelp.ui.community;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.ihelp.R;
import com.example.ihelp.ui.community_event.CommunityEventFragment;
import com.example.ihelp.ui.community_service.CommunityServiceFragment;
import com.google.android.material.tabs.TabLayout;

public class CommunityFragment extends Fragment {
    private FragmentActivity mActivity;
    private View mView;
    private TabLayout mTabLayout;

    public CommunityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        mActivity = (FragmentActivity) activity;
        super.onAttach(activity);
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        mActivity = (FragmentActivity) context;
//        super.onAttach(context);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_community, container, false);
        initView();
        initTabBar();
        mActivity.getSupportFragmentManager().beginTransaction().replace(R.id.page_1_fragment_container, new CommunityEventFragment()).commit();
        return mView;
    }

    private void initView() {
        mTabLayout = mView.findViewById(R.id.fragment_community_tab_bar);
    }

    private void initTabBar() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment selectedFragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        selectedFragment = new CommunityEventFragment();
                        break;
                    case 1:
                        selectedFragment = new CommunityServiceFragment();
                        break;
                }
                mActivity.getSupportFragmentManager().beginTransaction().replace(R.id.page_1_fragment_container, selectedFragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //do nothing yet
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //do nothing yet
            }
        });

    }
}