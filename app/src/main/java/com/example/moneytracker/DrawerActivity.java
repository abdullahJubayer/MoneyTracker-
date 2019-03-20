package com.example.moneytracker;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DrawerActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;
    TextView tab1,tab2,tab3,tab4,tab5;
    private ViewPagerAdapter pagerAdapter;
    SqLDatabasehelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databasehelper=new SqLDatabasehelper(this);
        SQLiteDatabase database=databasehelper.getWritableDatabase();


        navigationView=findViewById(R.id.navigation_drawer);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        viewPager=findViewById(R.id.view_pager);
        tab1=findViewById(R.id.tab_1);
        tab2=findViewById(R.id.tab_2);
        tab3=findViewById(R.id.tab_3);
        tab4=findViewById(R.id.tab_4);
        tab5=findViewById(R.id.tab_5);

        mDrawerLayout=findViewById(R.id.drawer_id);
        toggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        pagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        tab5.setOnClickListener(this);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {


                if (i==0){
                    tab1.setTextSize(20);
                    tab1.setTextColor(getResources().getColor(R.color.white));
                    tab2.setTextSize(16);
                    tab2.setTextColor(getResources().getColor(R.color.gray));
                    tab3.setTextSize(16);
                    tab3.setTextColor(getResources().getColor(R.color.gray));
                    tab4.setTextSize(16);
                    tab4.setTextColor(getResources().getColor(R.color.gray));
                    tab5.setTextSize(16);
                    tab5.setTextColor(getResources().getColor(R.color.gray));
                }
                if (i==1){
                    tab1.setTextSize(16);
                    tab1.setTextColor(getResources().getColor(R.color.gray));
                    tab2.setTextSize(20);
                    tab2.setTextColor(getResources().getColor(R.color.white));
                    tab3.setTextSize(16);
                    tab3.setTextColor(getResources().getColor(R.color.gray));
                    tab4.setTextSize(16);
                    tab4.setTextColor(getResources().getColor(R.color.gray));
                    tab5.setTextSize(16);
                    tab5.setTextColor(getResources().getColor(R.color.gray));
                }
                if (i==2){
                    tab1.setTextSize(16);
                    tab1.setTextColor(getResources().getColor(R.color.gray));
                    tab2.setTextSize(16);
                    tab2.setTextColor(getResources().getColor(R.color.gray));
                    tab3.setTextSize(20);
                    tab3.setTextColor(getResources().getColor(R.color.white));
                    tab4.setTextSize(16);
                    tab4.setTextColor(getResources().getColor(R.color.gray));
                    tab5.setTextSize(16);
                    tab5.setTextColor(getResources().getColor(R.color.gray));
                }

                if (i==3){
                    tab1.setTextSize(16);
                    tab1.setTextColor(getResources().getColor(R.color.gray));
                    tab2.setTextSize(16);
                    tab2.setTextColor(getResources().getColor(R.color.gray));
                    tab3.setTextSize(16);
                    tab3.setTextColor(getResources().getColor(R.color.gray));
                    tab4.setTextSize(20);
                    tab4.setTextColor(getResources().getColor(R.color.white));
                    tab5.setTextSize(16);
                    tab5.setTextColor(getResources().getColor(R.color.gray));
                }
                if (i==4){
                    tab1.setTextSize(16);
                    tab1.setTextColor(getResources().getColor(R.color.gray));
                    tab2.setTextSize(16);
                    tab2.setTextColor(getResources().getColor(R.color.gray));
                    tab3.setTextSize(16);
                    tab3.setTextColor(getResources().getColor(R.color.gray));
                    tab4.setTextSize(16);
                    tab4.setTextColor(getResources().getColor(R.color.gray));
                    tab5.setTextSize(20);
                    tab5.setTextColor(getResources().getColor(R.color.white));
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tab_1){
            viewPager.setCurrentItem(0);
        }
        if (v.getId()==R.id.tab_2){
            viewPager.setCurrentItem(1);
        }
        if (v.getId()==R.id.tab_3){
            viewPager.setCurrentItem(2);
        }
        if (v.getId()==R.id.tab_4){
            viewPager.setCurrentItem(3);
        }
        if (v.getId()==R.id.tab_5){
            viewPager.setCurrentItem(4);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.nav_home:
                mDrawerLayout.closeDrawers();
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_deposit:
                mDrawerLayout.closeDrawers();
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_ExpensesId:
                mDrawerLayout.closeDrawers();
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_DebtId:
                mDrawerLayout.closeDrawers();
                viewPager.setCurrentItem(3);
                break;
            case R.id.nav_CreditId:
                mDrawerLayout.closeDrawers();
                viewPager.setCurrentItem(4);
                break;
            case R.id.bottom_navigation_menu_daily:
                Toast.makeText(DrawerActivity.this,"Daily",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottom_navigation_menu_monthly:
                Toast.makeText(DrawerActivity.this,"Monthly",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bottom_navigation_menu_yearly:
                Toast.makeText(DrawerActivity.this,"Yearly",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
