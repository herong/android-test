package com.github.herong.android.eshopping.mainNavTabs;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.github.herong.android.eshopping.ActBarActivity;
import com.github.herong.android.eshopping.R;

public class NavHomeActivity extends ActionBarActivity implements View.OnClickListener {

    private final String TAG = "NavHomeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"----------------------------------------------------启动NavHomeActivity");
        setContentView(R.layout.activity_nav_home);
        bindListener();
    }


    private void bindListener(  ) {
        Button btnTest = (Button) findViewById(R.id.btn_test);
        btnTest.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nav_home, menu);
        return true;
/*
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        return super.onCreateOptionsMenu(menu);*/
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

    @Override
    public void onClick(View v) {
       Log.d(TAG,"单击了:" + v.toString());
       switch (v.getId()) {
           case R.id.btn_test:
               Intent intent = new Intent(this, ActBarActivity.class);
               startActivity(intent);
               break;
           default:

       }
    }
}
