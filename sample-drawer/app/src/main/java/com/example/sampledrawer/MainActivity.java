package com.example.sampledrawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sampledrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
                    implements NavigationView.OnNavigationItemSelectedListener, FragmentCallback {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    DrawerLayout drawer;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this
                , drawer
                , toolbar
                , R.string.navigation_drawer_open
                , R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment1)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menu1) {
            Toast.makeText(this, "첫번째메뉴", Toast.LENGTH_SHORT).show();
            onFragmentSelected(0, null);
        } else if(id == R.id.menu2) {
            Toast.makeText(this, "두번째메뉴", Toast.LENGTH_SHORT).show();
            onFragmentSelected(1, null);
        } else if(id == R.id.menu3) {
            Toast.makeText(this, "세번째메뉴", Toast.LENGTH_SHORT).show();
            onFragmentSelected(2, null);
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curFragment = null;

        if(position == 0) {
            curFragment = fragment1;
            toolbar.setTitle("첫번째화면");
        } else if(position == 1) {
            curFragment = fragment2;
            toolbar.setTitle("두번째화면");
        } else if(position == 2) {
            curFragment = fragment3;
            toolbar.setTitle("세번째화면");
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, curFragment)
                .commit();
    }
}