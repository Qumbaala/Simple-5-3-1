package com.crookedqueue.simple531.View;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecordsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentInterractor {
    @Bind(R.id.fragment_frame_records_activity)
    FrameLayout fragFrame;
    @Bind(R.id.drawer_layout_records)
    DrawerLayout drawer;
    @Bind(R.id.nav_view_records)
    NavigationView navView;
    @Bind(R.id.toolbar_records)
    Toolbar toolbar;
    private static final String TOOLBAR_LABEL = "Records";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        ButterKnife.bind(this);
        toolbar.setTitle("Records");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        FragmentManager fragMan = getSupportFragmentManager();
        fragMan.beginTransaction().replace(fragFrame.getId(), new PersonalRecordFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Class classToLoad = null;

        if (id == R.id.nav_home) {
            classToLoad = MainActivity.class;
        } else if (id == R.id.nav_lists_records) {

        } else if (id == R.id.nav_share) {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
