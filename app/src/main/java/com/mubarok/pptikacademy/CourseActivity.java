package com.mubarok.pptikacademy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class CourseActivity extends AppCompatActivity {

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.loginCheck();

        Button mBtn_account = findViewById(R.id.settingbutton);
        mBtn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iAccount = new Intent(getApplicationContext(),AccountActivity.class);
                startActivity(iAccount);
            }
        });

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabItem tabMyCourses = findViewById(R.id.tabMyCourses);
        TabItem tabDiscovery = findViewById(R.id.tabDiscovery);
        final ViewPager viewPager = findViewById(R.id.pager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}