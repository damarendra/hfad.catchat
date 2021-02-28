package com.example.catchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.catchat.fragments.DraftFragment;
import com.example.catchat.fragments.InboxFragment;
import com.example.catchat.fragments.SentItemsFragment;
import com.example.catchat.fragments.TrashFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.main_drawer_layout);
        ActionBarDrawerToggle drawerToggleButton = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer
        );
        drawerLayout.addDrawerListener(drawerToggleButton);
        drawerToggleButton.syncState();

        NavigationView navigationView = findViewById(R.id.main_nav);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Fragment fragment_content = null;
        Intent intent_activity_content = null;

        switch (id) {
            case R.id.nav_inbox:
                fragment_content = new InboxFragment();
                break;
            case R.id.nav_draft:
                fragment_content = new DraftFragment();
                break;
            case R.id.nav_sent:
                fragment_content = new SentItemsFragment();
                break;
            case R.id.nav_trash:
                fragment_content = new TrashFragment();
                break;
            case R.id.nav_help:
                intent_activity_content = new Intent(this, HelpActivity.class);
                break;
            case R.id.nav_feedback:
                intent_activity_content = new Intent(this, FeedbackActivity.class);
                break;
            default:
                fragment_content = new InboxFragment();
                break;
        }

        if(fragment_content != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_content, fragment_content);
            ft.commit();
        } else {
            startActivity(intent_activity_content);
        }

        DrawerLayout drawerLayout = findViewById(R.id.main_drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.main_drawer_layout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}