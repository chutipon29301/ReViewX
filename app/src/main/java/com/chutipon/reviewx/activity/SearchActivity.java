package com.chutipon.reviewx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.chutipon.reviewx.R;
import com.chutipon.reviewx.adapter.SearchAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class SearchActivity extends AppCompatActivity {
    private static String TAG = "SearchActivity";
    private static SearchActivity instance;
    private RecyclerView searchRecycler;
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        instance = this;
        initInstance(savedInstanceState);
    }

    private void initInstance(Bundle savedInstanceState) {

        searchRecycler = findViewById(R.id.searchRecycler);
        searchView = findViewById(R.id.search_view);

        SearchAdapter.getInstance().init(getBaseContext());

        searchRecycler.setAdapter(SearchAdapter.getInstance());
        searchRecycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }


    public static SearchActivity getInstance() {
        return instance;
    }
    public void redirect(Class cls) {
        Intent intent = new Intent(SearchActivity.this, cls);
        startActivity(intent);
    }
    public void redirectFragment(Fragment frag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer,frag)
                .commit();
    }



}
