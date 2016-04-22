package com.dono.psakkos.dono;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private static Activity Me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        this.yourLabelsLayout();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        MainActivity.Me = this;
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            this.hideKeyBoard();
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        this.hideKeyBoard();
        this.removeAllFragments();

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_labels) {
            this.yourLabelsLayout();
        } else if (id == R.id.nav_add_label) {
            this.addLabelLayout();
        } else if (id == R.id.nav_key) {
            this.keyLayout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void removeAllFragments() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LonelyFragment lonelyFragment = new LonelyFragment();
        fragmentTransaction.remove(lonelyFragment);

        LabelsFragment labelsFragment = new LabelsFragment();
        fragmentTransaction.remove(labelsFragment);

        AddLabelFragment addlabelFragment = new AddLabelFragment();
        fragmentTransaction.remove(addlabelFragment);

        KeyFragment keyFragment = new KeyFragment();
        fragmentTransaction.remove(keyFragment);

        fragmentTransaction.commit();

    }

    private void setToolbarTitle(String toolbarTitle) {
        this.getSupportActionBar().setTitle(toolbarTitle);
    }

    private void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void showLonelyFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LonelyFragment lonelyFragment = new LonelyFragment();
        fragmentTransaction.replace(R.id.mainFragment, lonelyFragment);
        fragmentTransaction.commit();
    }

    private void yourLabelsLayout() {
        this.setToolbarTitle("Your Labels");

        if (new PersistableLabels(this).getAll().length == 0)
        {
            this.showLonelyFragment();
        }
        else
        {
            this.showLabelsFragment();
        }
    }

    private void showLabelsFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        LabelsFragment labelsFragment = new LabelsFragment();
        fragmentTransaction.replace(R.id.mainFragment, labelsFragment);
        fragmentTransaction.commit();
    }

    private void addLabelLayout() {
        this.setToolbarTitle("Add Label");

        this.showAddLabelFragment();
    }

    private void showAddLabelFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        AddLabelFragment addlabelFragment = new AddLabelFragment();
        fragmentTransaction.replace(R.id.mainFragment, addlabelFragment);
        fragmentTransaction.commit();
    }

    private void keyLayout() {
        this.setToolbarTitle("Your Key");

        this.showKeyFragment();
    }

    private void showKeyFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        KeyFragment keyFragment = new KeyFragment();
        fragmentTransaction.replace(R.id.mainFragment, keyFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.dono.psakkos.dono/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.dono.psakkos.dono/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    public static void showError(String error)
    {
        SuperActivityToast superActivityToast = new SuperActivityToast(MainActivity.Me);
        superActivityToast.setText(error);
        superActivityToast.setDuration(SuperToast.Duration.LONG);
        superActivityToast.setBackground(SuperToast.Background.RED);
        superActivityToast.setTextColor(Color.WHITE);
        superActivityToast.setTouchToDismiss(true);
        superActivityToast.show();
    }

    public static void showInfo(String info)
    {
        SuperActivityToast superActivityToast = new SuperActivityToast(MainActivity.Me);
        superActivityToast.setText(info);
        superActivityToast.setDuration(SuperToast.Duration.SHORT);
        superActivityToast.setBackground(SuperToast.Background.BLUE);
        superActivityToast.setTextColor(Color.WHITE);
        superActivityToast.setTouchToDismiss(true);
        superActivityToast.show();
    }
}
