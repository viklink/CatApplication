package exam.catapp.sourceit.catapplication;

import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import exam.catapp.sourceit.catapplication.adapter.CatAdapter;
import exam.catapp.sourceit.catapplication.adapter.CatRecycleAdapter;
import exam.catapp.sourceit.catapplication.adapter.OnCatClickListener;
import exam.catapp.sourceit.catapplication.database.CatDatabase;
import exam.catapp.sourceit.catapplication.databinding.ItemCatBinding;
import exam.catapp.sourceit.catapplication.model.Cat;
import exam.catapp.sourceit.catapplication.network.CatLoadingListener;
import exam.catapp.sourceit.catapplication.network.LoadAsyncTask;

public class CatDrawerActivity extends AppCompatActivity implements CatLoadingListener {

    RecyclerView recyclerView;

    CatRecycleAdapter catAdapter;

    CatDatabase catDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_drawer);

        catDatabase = DBInitializer.initialize(this);

        catAdapter = new CatRecycleAdapter(null, null);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(catAdapter);

        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(getBaseContext(), String.format("Click on item %s", menuItem.getTitle()), Toast.LENGTH_LONG).show();
                String gender;
                if (menuItem.getItemId() == R.id.idFemale) {
                    gender = "female";
                } else {
                    gender = "male";
                }

                catDatabase.catDao().getCatsByGender(gender).observe(CatDrawerActivity.this, new Observer<List<Cat>>() {
                    @Override
                    public void onChanged(@Nullable List<Cat> cats) {
                        catAdapter.setData(cats);
                    }
                });
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public void startLoading() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void finishLoading(List<Cat> cats) {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setVisibility(View.VISIBLE);
        catAdapter = new CatRecycleAdapter(cats, null);
        recyclerView.setAdapter(catAdapter);
        catAdapter.setData(cats);
    }
}
