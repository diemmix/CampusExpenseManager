package com.example.campusexpensemanager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private DrawerLayout drawerLayout;

    FloatingActionButton fab;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_Home);
        }

        replaceFragment(new HomeFragment());

        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.menu_Home){
                replaceFragment(new HomeFragment());
            }
            else  if(item.getItemId() == R.id.menu_Expense){
                replaceFragment(new ExpenseFragment());
            }
            else  if(item.getItemId() == R.id.menu_Budget){
                replaceFragment(new BudgetFragment());
            }
            else  if(item.getItemId() == R.id.menu_Profile){
                replaceFragment(new ProfileFragment());
            }

            return true;
        });

       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showBottomDialog();
           }
       });
    }
    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout ExpenseLayout = dialog.findViewById(R.id.layoutExpense);
        LinearLayout BudgetLayout = dialog.findViewById(R.id.layoutBudget);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);

        ExpenseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        BudgetLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
}
