package com.example.jntuplacements.activity;

import android.app.ListFragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jntuplacements.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jntuplacements.fragment.HomeFragment;
import com.example.jntuplacements.fragment.VisitedFragment;
import com.example.jntuplacements.fragment.VisitingFragment;
import com.example.jntuplacements.other.AppUtil;
import com.example.jntuplacements.other.CircleTransform;

import java.net.URI;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,VisitingFragment.OnFragmentInteractionListener, VisitedFragment.OnFragmentInteractionListener{

    public static int navItemIndex = 0;

    private static final String TAG_HOME = "home";
    private static final String TAG_VISITING = "visiting";
    private static final String TAG_VISITED = "visited";
    private static String CURRENT_TAG = TAG_HOME;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View navHeader;
    private FloatingActionButton fab;
    private Handler mHandler;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private ImageView imgNavHeaderBg, imgProfile;
    private String activityTitles[];
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        txtWebsite = (TextView) navHeader.findViewById(R.id.website);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //load nav Header
        txtName.setText(getIntent().getStringExtra("UserEmail")); //logged in user data
        txtWebsite.setText("website or email");

        displaySelectedItem();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        navItemIndex = item.getItemId();
        displaySelectedItem();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displaySelectedItem(){
        Fragment fragment = null;
        switch (navItemIndex){
            case R.id.nav_home:
                navItemIndex = 0;
                fragment = new HomeFragment();
                break;
            case R.id.nav_visiting:
                navItemIndex = 1;
                fragment = new VisitingFragment();
                break;
            case R.id.nav_visited:
                navItemIndex = 2;
                fragment = new VisitedFragment();
                break;
            case R.id.nav_logout:
                navItemIndex = 3;
                AppUtil.logOut(getApplicationContext());
                finish();
                return;
            default:
                navItemIndex = 0;
                fragment = new HomeFragment();
        }

        if(navItemIndex<3) {
            getSupportActionBar().setTitle(activityTitles[navItemIndex]);
        }
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);

        if(fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, fragment);
            ft.commit();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onVisitedFragmentInteraction(Uri uri) {
        // Do different stuff
    }

    @Override
    public void onVisitingFragmentInteraction(Uri uri) {

    }
}
