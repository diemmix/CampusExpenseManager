package com.example.campusexpensemanager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdapter extends FragmentStateAdapter {

    public ViewPageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return new HomeFragment();
        }
        else if (position == 1 ){
            return new ExpenseFragment();
        }
        else if (position == 2){
            return new BudgetFragment();
        }
        else if (position == 3){
            return new ProfileFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
