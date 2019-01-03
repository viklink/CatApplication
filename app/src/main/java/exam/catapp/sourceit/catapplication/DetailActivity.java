package exam.catapp.sourceit.catapplication;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import exam.catapp.sourceit.catapplication.adapter.CatFragmentAdapter;
import exam.catapp.sourceit.catapplication.database.CatDatabase;
import exam.catapp.sourceit.catapplication.databinding.ActivityDetailBinding;
import exam.catapp.sourceit.catapplication.model.Cat;

public class DetailActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            id = savedInstanceState.getInt("id");
        } else {
            id = getIntent().getIntExtra("id", 0);
        }

        final ActivityDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        CatDatabase catDatabase = Room.databaseBuilder(this, CatDatabase.class, "cats.db").build();
        catDatabase.catDao().getCat(id).observe(this, new Observer<Cat>() {
            @Override
            public void onChanged(@Nullable Cat cat) {
                binding.setCat(cat);
            }
        });

        final ViewPager viewPager = findViewById(R.id.viewPager);

        catDatabase.catDao().getLiveDataAll().observe(this, new Observer<List<Cat>>() {
            @Override
            public void onChanged(@Nullable List<Cat> cats) {
                CatFragmentAdapter catFragmentAdapter = new CatFragmentAdapter(getSupportFragmentManager(), cats);
                viewPager.setAdapter(catFragmentAdapter);
            }
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("id", id);
    }

    /*private class CatFragmentAdapter extends FragmentStatePagerAdapter {
        private List<Cat> catList;

        public CatFragmentAdapter(FragmentManager fm, List<Cat> cats) {
            super(fm);
            catList = cats;
        }

        @Override
        public Fragment getItem(int i) {

            Cat cat = catList.get(i);
            CatFragment catFragment = new CatFragment();
            Bundle args = new Bundle();
            args.putInt("id", cat.getId());
            catFragment.setArguments(args);

            return catFragment;
        }

        @Override
        public int getCount() {
            return catList != null ? catList.size() : 0;
        }
    }
    */
}
