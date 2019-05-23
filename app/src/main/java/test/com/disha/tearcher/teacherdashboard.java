package test.com.disha.tearcher;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import test.com.disha.R;
import test.com.disha.tearcher.ui.main.SectionsPagerAdapter;

public class teacherdashboard extends AppCompatActivity {

    SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teacherdashboard);

        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());



        ViewPager viewPager = findViewById(R.id.view_pager);

        setupViewPager(viewPager);

        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager)
    {

        sectionsPagerAdapter.addFragment(new tmeating(),"meating");

        sectionsPagerAdapter.addFragment(new tquestion(),"question");

        sectionsPagerAdapter.addFragment(new tprofile(),"profile");

        viewPager.setAdapter(sectionsPagerAdapter);
    }

}