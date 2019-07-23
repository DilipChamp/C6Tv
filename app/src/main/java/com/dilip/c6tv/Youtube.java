package com.dilip.c6tv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Youtube extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

 String API_KEY="AIzaSyD6NOCLtQECVb6bNyveEntd5rJ_u_uwxOA";
 String url="https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCnk5kAaFR3RqphttB2-TJkw&maxResults=50&pageToken=CAUQAA%2CCAUQAQ&key=AIzaSyD6NOCLtQECVb6bNyveEntd5rJ_u_uwxOA";
 ArrayList<Data> DataArrayList;
 MyCustomAdapter myCustomAdapter;
 ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        listView=(ListView)findViewById(R.id.listview);
        DataArrayList=new ArrayList<>();
        myCustomAdapter =new MyCustomAdapter(Youtube.this,DataArrayList);
        displayVideos();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.youtube, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_live) {
            Intent livet=new Intent(Youtube.this,LiveTV.class);
            startActivity(livet);        } else if (id == R.id.nav_web) {
            Intent webvi=new Intent(Youtube.this,Web.class);
            startActivity(webvi);

        } else if (id == R.id.nav_youtube) {
            Intent youv=new Intent(Youtube.this,Youtube.class);
            startActivity(youv);
        } else if(id==R.id.nav_home)
        {
            Intent hom=new Intent(Youtube.this,Home.class);
            startActivity(hom);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void displayVideos()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray= jsonObject.getJSONArray("items");
                     for (int i=0;i<jsonArray.length();i++)
                     {
                         JSONObject jsonObject1=jsonArray.getJSONObject(i);
                         JSONObject jsonVideoId=jsonObject1.getJSONObject("id");
                         JSONObject jsonObjectSnippet=jsonObject1.getJSONObject("snippet");
                         JSONObject jsonObjectDefault=jsonObjectSnippet.getJSONObject("thumbnails").getJSONObject("medium");
                         Data vd=new Data();
                         String video_id=jsonVideoId.getString("videoId");
                         vd.setVideoId(video_id);
                         vd.setTitle(jsonObjectSnippet.getString("title"));
                         vd.setDescription(jsonObjectSnippet.getString("description"));
                         vd.setUrl(jsonObjectDefault.getString("url"));
                         DataArrayList.add(vd);

                     }
                     listView.setAdapter(myCustomAdapter);
                     myCustomAdapter.notifyDataSetChanged();

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
