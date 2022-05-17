package com.example.projectt1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class activity_dasborad extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  DrawerLayout drawerLayout;
  ActionBarDrawerToggle drawerToggle;
  NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dasborad);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
   about about = new about();
    contact contact=new contact();
    profile profile=new profile();
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch ((item.getItemId())){
            case R.id.About:
                 getSupportFragmentManager().beginTransaction().replace(R.id.flContainer,about).commit();
                break;

            case R.id.Logout:
                Toast.makeText(this, "LogOut", Toast.LENGTH_SHORT).show();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(activity_dasborad.this,materialdesian.class));
                finish();
                return true;

            case R.id.Profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer,profile).commit();
                break;

            case R.id.Contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.flContainer,contact).commit();
                break;

        }
        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                finishAffinity();

            }else {
                super.onBackPressed();
            }
        }
    }
}