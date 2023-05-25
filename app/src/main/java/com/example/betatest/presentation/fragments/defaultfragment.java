package com.example.betatest.presentation.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.betatest.R;
import com.example.betatest.databinding.FragmentDefaultfragmentBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class defaultfragment extends Fragment {
    private SharedPreferences prefs;
    private AppBarConfiguration mAppBarConfiguration;
    private FragmentDefaultfragmentBinding mBinding;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDefaultfragmentBinding.inflate(inflater);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        prefs = getActivity().getSharedPreferences("MyPrefs", MODE_PRIVATE);

        NavHostFragment navHostFragment = (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerView2);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            //final NavController navController = Navigation.findNavController(requireView());
            BottomNavigationView navigationBar = mBinding.bottomNav;
            AppBarConfiguration appBarConfiguration =
                    new AppBarConfiguration.Builder(navController.getGraph()).build();
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.inflateMenu(R.menu.toolbardj);
            toolbar.setTitle(""); // убираем заголовок по умолчанию

            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case (R.id.tool_feedback):
                            break;
                        case (R.id.tool_logout):

                            mAuth.signOut();
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.remove("email");
                            editor.remove("password");
                            editor.remove("rememberMe");
                            editor.apply();



                            Navigation.findNavController(requireView()).navigate(R.id.action_defaultfragment_to_vhod1);
                    }
                    return false;
                }
            });
            NavigationUI.setupWithNavController(
                    toolbar, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(navigationBar, navController);
        }
       // mainmenu fragment = new mainmenu();
       // fragment.onViewCreated(view.findViewById(R.id.fragmentContainerView2), savedInstanceState);
    }
}