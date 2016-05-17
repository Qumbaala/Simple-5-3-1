package com.crookedqueue.simple531.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.crookedqueue.simple531.Presenter.FragmentInterractor;
import com.crookedqueue.simple531.Presenter.NavigationPresenter;
import com.crookedqueue.simple531.R;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.crookedqueue.simple531.View.ExerciseListFragment.*;

public class ExerciseActivity extends AppCompatActivity implements FragmentInterractor {
    @Bind(R.id.fragment_frame_exercise_activity)
    FrameLayout fragFrame;
    @Bind(R.id.drawer_layout_exercise)
    DrawerLayout drawer;
    @Bind(R.id.nav_view_exercise)
    NavigationView navView;
    @Bind(R.id.toolbar_exercise)
    Toolbar toolbar;
    private static final String TOOLBAR_LABEL = "Exercise List";
    NavigationPresenter navPresenter;
    int liftLabel;
    int setType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        ButterKnife.bind(this);
        toolbar.setTitle(TOOLBAR_LABEL);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navPresenter = new NavigationPresenter(this, drawer);
        navView.setNavigationItemSelectedListener(navPresenter);
        Bundle extras = getIntent().getExtras();
        //gets the extras, passed in the intent to launch this activity
        //these will be passed into the fragment and handed to the presenter
        //we're using fragments because we may want to add a bottom bar for more functionality in the future, such as user defined assistance work
        if (extras != null) {
            liftLabel = extras.getInt(LIFT_LABEL);
            setType = extras.getInt(SET_TYPE);
        }
        FragmentManager fragMan = getSupportFragmentManager();
        fragMan.beginTransaction().replace(fragFrame.getId(),
                ExerciseListFragment.newInstance(liftLabel, setType)).commit();
    }

    @Override
    public void setToolbarTitle(String s) {
        toolbar.setTitle(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navPresenter = null;
        ButterKnife.unbind(this);
    }
}
