package com.juanjo.juanjo.clean_boilerplate.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.juanjo.juanjo.clean_boilerplate.R;
import com.juanjo.juanjo.clean_boilerplate.ui.adapter.ViewPagerAdapter;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.FavoriteFragment;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.LatestFragment;
import com.juanjo.juanjo.clean_boilerplate.ui.fragment.RandomFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        MenuItem actionSearch = menu.findItem(R.id.action_search);
        searchView = (SearchView) actionSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                clearSearchView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void initView() {
        getSupportActionBar();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new LatestFragment(),getString(R.string.tab_latest));
        viewPagerAdapter.addFrag(new RandomFragment(),getString(R.string.tab_random));
        viewPagerAdapter.addFrag(new FavoriteFragment(),getString(R.string.tab_favorite));
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void clearSearchView() {
        searchView.clearFocus();
        searchView.onActionViewCollapsed();
    }
}
