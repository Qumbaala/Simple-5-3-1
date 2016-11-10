package com.crookedqueue.simple531.View;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.Presenter.NavigationPresenter;
import com.crookedqueue.simple531.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements FragmentInterractor {
    @Bind(R.id.toolbar_main)
    Toolbar toolbar;
    @Bind(R.id.nav_view_main)
    NavigationView navView;
    @Bind(R.id.drawer_layout_main)
    DrawerLayout drawer;
    @Bind(R.id.coordinator_main)
    CoordinatorLayout coordinator;
    @Bind(R.id.fragment_frame_main)
    FrameLayout fragFrame;
    NavigationPresenter navPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Cycle Day");
        setSupportActionBar(toolbar);
        navPresenter = new NavigationPresenter(this, drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(navPresenter);
        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(fragFrame.getId(), new CycleDayChooserFragment()).commit();

        BottomBar bottomBar = BottomBar.attach(coordinator, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.menu_choose_cycle_day:
                        fm.beginTransaction().replace(R.id.fragment_frame_main, new CycleDayChooserFragment()).commit();
                        break;
                    case R.id.menu_edit_maxes:
                        fm.beginTransaction().replace(R.id.fragment_frame_main, new MaxManagerFragment()).commit();
                        break;
                    case R.id.menu_edit_cycle_settings:
                        fm.beginTransaction().replace(R.id.fragment_frame_main, new SettingsFragment()).commit();
                        break;
                }

            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        bottomBar.setActiveTabColor("#FF5252");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        navPresenter = null;
    }

    @Override
    public void setToolbarTitle(String s) {
        toolbar.setTitle(s);
    }
}
