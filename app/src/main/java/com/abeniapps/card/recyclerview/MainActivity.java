package com.abeniapps.card.recyclerview;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    List<Book> lstBook ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initCollapsingToolbar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =  findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();

        MenuItem item= menu.findItem(R.id.control);
        MenuItem item2= menu.findItem(R.id.app_info);
        SpannableString s = new SpannableString(item.getTitle());
        SpannableString r = new SpannableString(item2.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.titleC), 0, s.length(), 0);
        r.setSpan(new TextAppearanceSpan(this, R.style.titleC), 0, r.length(), 0);
        item.setTitle(s);
        item2.setTitle(r);

        navigationView.setNavigationItemSelectedListener(this);

        lstBook = new ArrayList<>();
        lstBook.add(new Book(R.drawable.thevigitarian));
        lstBook.add(new Book(R.drawable.thewildrobot));
        lstBook.add(new Book(R.drawable.mariasemples));
        lstBook.add(new Book(R.drawable.themartian));
        lstBook.add(new Book(R.drawable.hediedwith));
        lstBook.add(new Book(R.drawable.thevigitarian));
        lstBook.add(new Book(R.drawable.thewildrobot));
        lstBook.add(new Book(R.drawable.mariasemples));
        lstBook.add(new Book(R.drawable.themartian));
        lstBook.add(new Book(R.drawable.hediedwith));
        lstBook.add(new Book(R.drawable.thevigitarian));
        lstBook.add(new Book(R.drawable.thewildrobot));
        lstBook.add(new Book(R.drawable.mariasemples));
        lstBook.add(new Book(R.drawable.themartian));
        lstBook.add(new Book(R.drawable.hediedwith));

        lstBook.add(new Book(R.drawable.thevigitarian));
        lstBook.add(new Book(R.drawable.thewildrobot));
        lstBook.add(new Book(R.drawable.mariasemples));
        lstBook.add(new Book(R.drawable.themartian));
        lstBook.add(new Book(R.drawable.hediedwith));
        lstBook.add(new Book(R.drawable.thevigitarian));
        lstBook.add(new Book(R.drawable.thewildrobot));
        lstBook.add(new Book(R.drawable.mariasemples));
        lstBook.add(new Book(R.drawable.themartian));
        lstBook.add(new Book(R.drawable.hediedwith));
        lstBook.add(new Book(R.drawable.thevigitarian));
        lstBook.add(new Book(R.drawable.thewildrobot));
        lstBook.add(new Book(R.drawable.mariasemples));
        lstBook.add(new Book(R.drawable.themartian));
        lstBook.add(new Book(R.drawable.hediedwith));



        RecyclerView myrv =  findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

        //adding GLIDE code
        try {
            Glide.with(this).load(R.drawable.sassy).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);//how the collapsing toolbar should be set is defined in the resource file hence the reference
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout =  findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_fav) {
            Toast.makeText(this, "You Clicked Favorites", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_tell) {
            try{
                Intent recommend=new Intent(Intent.ACTION_SEND);
                recommend.setType("text/plain");
                recommend.putExtra(Intent.EXTRA_SUBJECT,"AnyWallpaper");
                String appLink="Try this Wallpaper App\n\n";
                appLink+="https://play.google.com/store?hl=en";

                recommend.putExtra(Intent.EXTRA_TEXT,appLink);
                startActivity(Intent.createChooser(recommend,"Recommend Via"));

            }catch(Exception e){
                Toast.makeText(this,"Error with the device's sharing setting",Toast.LENGTH_LONG).show();
            }
        } else if(id == R.id.nav_setting) {
            Toast.makeText(this, "You Clicked Settings", Toast.LENGTH_LONG).show();
        } else if(id==R.id.nav_rate){
            Intent rate=new Intent(Intent.ACTION_VIEW);
            rate.setData(Uri.parse("https://play.google.com/store?hl=en"));
            startActivity(rate);
        } else if(id==R.id.nav_others){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store?hl=en")));
        }else if(id==R.id.nav_about){
            startActivity(new Intent(MainActivity.this,About.class));
        }else if(id==R.id.nav_exit){
            Intent startMain=new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
            finish();
        }

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch(item.getItemId()) {
           case R.id.action_fav:
               Toast.makeText(this, "You Clicked Favorites", Toast.LENGTH_SHORT).show();
               return true;
           case R.id.action_settings:
               Toast.makeText(this, "You Clicked Setting", Toast.LENGTH_SHORT).show();
               return true;
           case R.id.action_share:
               try{
                   PackageManager pack = getPackageManager();
                   ApplicationInfo appInfo=pack.getApplicationInfo(getPackageName(),0);
                   File srcFile=new File(appInfo.publicSourceDir);//Full path to the publicly available parts of sourceDir

                   Intent share=new Intent();
                   share.setAction(Intent.ACTION_SEND);
                   share.setType("application/vnd.android.package-archive");
                   share.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(srcFile));
                   startActivity(Intent.createChooser(share,"Share App Via"));
               }
               catch(Exception e){
                   Log.e("Share App",e.getMessage());
               }
           default:
               return super.onOptionsItemSelected(item);
       }
    }

}
