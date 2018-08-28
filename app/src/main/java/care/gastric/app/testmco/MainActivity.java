package care.gastric.app.testmco;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ListView list;

    String[] maintitle ={
            "Weight Analysis","Order","Organized posts","FAQ","Join Facebook group",
    };

    String[] subtitle ={
            "World's #1 health calculator",
            "Buy recommended plan",
            "Create amazing posts to inspire",
            "Get answers to common questions",
            "Join and talk to 80,000 members ",
    };

    Integer[] imgid={
            R.drawable.ic_chart_pie,
            R.drawable.ic_shopping_cart,
            R.drawable.ic_wpforms,
            R.drawable.ic_question_circle,
            R.drawable.ic_facebook_square,
   };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navLinkAdapter adapter=new navLinkAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position == 0) {
                    //Toast.makeText(getApplicationContext(), "Weight Analysis", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, WA.class);
                    myIntent.putExtra("key", "value"); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                } else if (position == 1) {
                    //Toast.makeText(getApplicationContext(), "Order", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, GBAOrder.class);
                    myIntent.putExtra("key", "value"); //Optional parameters
                    MainActivity.this.startActivity(myIntent);

                } else if (position == 2) {
                    //Toast.makeText(getApplicationContext(), "Organized posts", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MainActivity.this, GBAOP.class);
                    myIntent.putExtra("key", "value"); //Optional parameters
                    MainActivity.this.startActivity(myIntent);
                } else if (position == 3) {
                    //Toast.makeText(getApplicationContext(), "FAQ", Toast.LENGTH_SHORT).show();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gastric.care/FAQ"));
                    startActivity(browserIntent);
                } else if (position == 4) {
                    //Toast.makeText(getApplicationContext(), "Join facebook group", Toast.LENGTH_SHORT).show();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fb.com/groups/lost100"));
                    startActivity(browserIntent);
                }
            }
        });













        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent myIntent = new Intent(MainActivity.this, WA.class);
            myIntent.putExtra("key", "value"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_gallery) {
            Intent myIntent = new Intent(MainActivity.this, GBAOrder.class);
            myIntent.putExtra("key", "value"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_slideshow) {
            Intent myIntent = new Intent(MainActivity.this, GBAOP.class);
            myIntent.putExtra("key", "value"); //Optional parameters
            MainActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://gastric.care/FAQ"));
            startActivity(browserIntent);
        } else if (id == R.id.nav_send) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://fb.com/groups/lost100"));
            startActivity(browserIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
