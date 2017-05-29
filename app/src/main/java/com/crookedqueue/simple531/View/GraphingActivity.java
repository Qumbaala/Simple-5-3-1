package com.crookedqueue.simple531.View;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.Presenter.NavigationPresenter;
import com.crookedqueue.simple531.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GraphingActivity extends AppCompatActivity implements FragmentInterractor {

    @Bind(R.id.toolbar_graphing)
    Toolbar toolbar;
    @Bind(R.id.nav_view_graphing)
    NavigationView navView;
    @Bind(R.id.drawer_layout_graphing)
    DrawerLayout drawer;
    @Bind(R.id.coordinator_graphing)
    CoordinatorLayout coordinator;
    @Bind(R.id.fragment_frame_graphing)
    FrameLayout fragFrame;
    NavigationPresenter navPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphing);
        ButterKnife.bind(this);
        toolbar.setTitle("Training Max Progression");
        setSupportActionBar(toolbar);
        navPresenter = new NavigationPresenter(this, drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(navPresenter);
        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(fragFrame.getId(), new MaxProgressionGraphingFragment()).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        navPresenter = null;
    }

    @Override
    public void setToolbarTitle (String s){
        toolbar.setTitle(s);
    }
}


