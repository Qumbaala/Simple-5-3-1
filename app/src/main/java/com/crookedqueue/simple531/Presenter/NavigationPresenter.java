package com.crookedqueue.simple531.Presenter;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.crookedqueue.simple531.R;
import com.crookedqueue.simple531.View.MainActivity;
import com.crookedqueue.simple531.View.RecordsActivity;

public class NavigationPresenter implements NavigationView.OnNavigationItemSelectedListener {
    private final AppCompatActivity activity;
    private final DrawerLayout drawer;

    public NavigationPresenter(AppCompatActivity activity, DrawerLayout drawer) {
        this.activity = activity;
        this.drawer = drawer;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Class classToLoad = null;
        if (id == R.id.nav_home) {
            classToLoad = activity.getClass() != MainActivity.class ? MainActivity.class : null;
        } else if (id == R.id.nav_lists_records) {
            classToLoad = activity.getClass() != RecordsActivity.class ? RecordsActivity.class : null;
        } else if (id == R.id.nav_share) {
            final Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out Simple 5/3/1, an easy to use 'free forever' 5/3/1 templating app!\nhttp://goo.gl/VKxugx");
            sendIntent.setType("text/plain");
            activity.startActivity(Intent.createChooser(sendIntent, "Recommend Simple 5/3/1"));
        } else if (id == R.id.nav_rate) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.crookedqueue.simple531"));
            activity.startActivity(intent);
            return true;
        } else if (id == R.id.nav_email) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:crookedqueue@gmail.com"));
            activity.startActivity(Intent.createChooser(intent, "Email Developer"));
        }
        drawer.closeDrawer(GravityCompat.START);
        if (classToLoad != null) {
            Intent intent = new Intent(activity, classToLoad);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            activity.startActivity(intent);
        }
        return true;
    }

}
