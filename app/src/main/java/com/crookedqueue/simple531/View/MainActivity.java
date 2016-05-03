package com.crookedqueue.simple531.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentInterractor {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Cycle Day");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        final FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(fragFrame.getId(), new CycleDayChooserFragment()).commit();

        BottomBar bottomBar = BottomBar.attach(coordinator, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                switch (menuItemId) {
                    case R.id.cycle_day_picker:
                        fm.beginTransaction().replace(R.id.fragment_frame_main, new CycleDayChooserFragment()).commit();
                        break;
                    case R.id.maxes_interactor:
                        fm.beginTransaction().replace(R.id.fragment_frame_main, new MaxManagerFragment()).commit();
                        break;
                    case R.id.cycle_settings:
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
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Class classToLoad = null;
        if (id == R.id.nav_home) {

        }
        else if (id == R.id.nav_lists_records) {
            classToLoad = RecordsActivity.class;
        }
        else if (id == R.id.nav_share) {
            final Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out Simple 5/3/1, an easy to use 'free forever' 5/3/1 templating app!\nhttp://goo.gl/VKxugx");
            sendIntent.setType("text/plain");
            this.startActivity(Intent.createChooser(sendIntent, "Recommend Simple 5/3/1"));
        }
        else if (id == R.id.nav_rate){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.crookedqueue.simple531"));
            startActivity(intent);
            return true;
        }
        drawer.closeDrawer(GravityCompat.START);
        if (classToLoad != null) {
            Intent intent = new Intent(this, classToLoad);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
        }
        return true;
    }

    @Override
    public void setToolbarTitle(String s) {
        toolbar.setTitle(s);
    }
}
