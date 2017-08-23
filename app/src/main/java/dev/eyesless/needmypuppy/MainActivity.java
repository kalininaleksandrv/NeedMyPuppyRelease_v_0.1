package dev.eyesless.needmypuppy;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements onButtonListner, ItemClickListner, ActivityCompat.OnRequestPermissionsResultCallback {


    private String [] titles;
    private DrawerLayout drawer;
    private NavigationView naview;
    private Toolbar mytoolbar;
    private ActionBarDrawerToggle drawerToggle;
    private InitiationActivity inact;
    private static int THISLAYOUT = R.layout.activity_main;
    protected Guideline myGuideline;
    protected ConstraintLayout.LayoutParams lp;
    public static String GUIDLINE_VALUE;

    protected Drawable mPic;
    protected String start;
    protected String finish;


    public Drawable getmPic() {
        return mPic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(THISLAYOUT);

        inact = ((InitiationActivity) getApplicationContext());

        initguidline ();

        //call main fragmett if never call before
        if (savedInstanceState == null) {
            frameRemoover(new Buttons_main(), "ButtonsMain");
            toastmaker(getString(R.string.starttoast));
        }

        //restore guidline value if re-create fragment
        if (savedInstanceState != null){
            onRestoreGuidlineValue(savedInstanceState);
        }

        //код diwider-а
        initNavigationView ();
        inittoolbar();
        initDrawerTogle ();

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        initFrameVision ();

    }


    // create navigation view on drawer layout and set listner
    private void initNavigationView() {

        drawer = (DrawerLayout) findViewById(R.id.drawer_main);
        naview = (NavigationView) findViewById(R.id.navigation_view);
        naview.getMenu().clear();
        naview.inflateMenu(R.menu.menu_navigation);
        naview.inflateHeaderView(R.layout.navigation_header);

        DrawlerItemClickListner myDrawlerListner = new DrawlerItemClickListner
                (inact, this, drawer, naview);
        naview.setNavigationItemSelectedListener(myDrawlerListner);
    }

    //create custom toolbar
    private void inittoolbar() {

        mytoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mytoolbar);
    }

    //код Drawer Togle кнопка выдвижения и задвижения drawer-а
    private ActionBarDrawerToggle initDrawerTogle() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_closed){
            public void onDrawerClosed (View v) {
                super.onDrawerClosed(v);
            }
            public void onDrawerOpened (View v) {
                super.onDrawerOpened(v);
            }
        };

        return drawerToggle;
    }

    //init guidline

    private void initguidline() {
        myGuideline = (Guideline) findViewById(R.id.guideline2);
        lp = (ConstraintLayout.LayoutParams) myGuideline.getLayoutParams();
    }

    //save guidline value
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        //saved current value of guidline
        float f = lp.guidePercent;
        savedInstanceState.putFloat(GUIDLINE_VALUE, f);
        Log.w("MY_TAG", "save guideline value");
        super.onSaveInstanceState(savedInstanceState);
    }

    //restore saved value of guideline to correct work with recycle view
    public void onRestoreGuidlineValue (Bundle savedInstanceState){

        lp.guidePercent = savedInstanceState.getFloat(GUIDLINE_VALUE);
        myGuideline.setLayoutParams(lp);
        Log.w("MY_TAG", "restore guideline value");
    }

    //set a guidline value to change screen proportions depend on content
    public void gudlinesetter (Float f) {

        Guideline dragview = (Guideline) findViewById(R.id.guideline2);
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) dragview.getLayoutParams();
        lp.guidePercent = f;
        dragview.setLayoutParams(lp);

    }

    //create  action-menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //слушатель для меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
                switch (item.getItemId()) {

                    case R.id.action_email:
                        android.support.v4.app.Fragment tempfragment = getSupportFragmentManager().findFragmentByTag("ViewPager");
                        if (tempfragment != null && tempfragment.isVisible()) {
                            onShared ();
                        } else
                            toastmaker(getString(R.string.sharetoast));
                        return true;

                    case R.id.action_settings:
                        toastmaker(getString(R.string.nofunc));
                        return true;

                    case R.id.action_delet:
                        //deliting all choosing parameters ant set buttons status NOT pressed
                        inact.bucketseraser();
                        return true;

                    default:
                        return super.onOptionsItemSelected(item);
                }
    }

    //реакция на нажате кнопок в фрагменте buttons main
    @Override
    public void buttonClicked(View v) {

        switch (v.getId()){

            case R.id.button_complete_aboutdog:
                databaseinitiator();

                if (inact.getMyListOfBreed_m().size() != 0){

                    frameRemoover(new Forwhat_main(), "ForWhat");}

                else toastmaker(getString(R.string.no_breed_found));

                break;

            case R.id.button_complete_forwhat:

                if (inact.getMyListOfBreed_m().size() != 0){

                    frameRemoover(new Fragment_morpho(), "Morpho");}

                else toastmaker(getString(R.string.no_breed_found));

                break;

            //in morpho fragment
            case R.id.button_complete:

                if (inact.getMyListOfBreed_m().size() != 0){

                    frameRemoover(new Recycle_view_fragment(), "RecycleView");}

                else toastmaker(getString(R.string.no_breed_found));

                break;


            case R.id.imageButton_aboutowner:
                //check if button already been pressed, cant pressed next time
                if (inact.isButtonaboutdogispressed()){toastmaker(getString(R.string.disabled_button));}
                else
                    frameRemoover(new About_dog_main(), "AboutDog");
                break;

            case R.id.imageButton_forwhat:
                //check if button already been pressed, cant pressed next time
                if (inact.isButtonforwhatispressed()){toastmaker(getString(R.string.disabled_button));}
                else
                frameRemoover(new Forwhat_main(), "ForWhat");
                break;

            case R.id.imageButton_aboutdog:
                if (inact.isButtonaboutdogispressed()){toastmaker(getString(R.string.disabled_button));}
                else
                frameRemoover(new About_dog_main(), "AboutDog");
                break;

            case R.id.imageButton_morpho:
                if (inact.isButtonmorphoispressed()){toastmaker(getString(R.string.disabled_button));}
                else
                frameRemoover(new Fragment_morpho(), "Morpho");
                break;

            case R.id.button_gonext:

  //              databaseinitiator();

                if (inact.getMyListOfBreed_m().size() != 0){

                frameRemoover(new Recycle_view_fragment(), "RecycleView");}

                else toastmaker(getString(R.string.no_breed_found));

                break;

            case R.id.button_gonext_search:

                if (inact.getMyListOfBreed_m().size() != 0){

                    frameRemoover(new Recycle_view_fragment(), "RecycleView");}

                else toastmaker(getString(R.string.no_breed_found));

                break;
        }
    }

    //onClick to get data (position) from RecycleView's CardView and show appropriate breed in Fragment Description
    @Override
    public void onClick(View view, int position) {

        Fragment_viewpager myViewPagerFragm = new Fragment_viewpager();
        myViewPagerFragm.setBreedId(position);

        frameRemoover(myViewPagerFragm, "ViewPager");

    }

    // tis method start new activitys, including second optional parameter - arraylist, if null just ignored, if not null - put on intent
    public void activitystarter(Object o, ArrayList<?> al) {

        Intent intent = new Intent(this, (Class<?>) o);

        if (al != null) {
            intent.putExtra(List_profile.LIST, al);
        }
        startActivity(intent);
    }

    //main method for remoove frames when clicked
    public void frameRemoover (Fragment fragment, String mytag){

        initFrameVision ();

        android.support.v4.app.FragmentTransaction fratramain = getSupportFragmentManager().beginTransaction();
        fratramain.replace(R.id.replaced_main, fragment, mytag);
        fratramain.addToBackStack(null);
        fratramain.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fratramain.commit();

    }

    //method for create Frame Vision (up frame in main screen)

    private void initFrameVision() {

        android.support.v4.app.FragmentTransaction fratramain = getSupportFragmentManager().beginTransaction();
        fratramain.replace(R.id.frame_vision, new ProgressBarFragment());
        fratramain.addToBackStack(null);
        fratramain.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fratramain.commit();
    }

   // method for share created content
    private void onShared() {

        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = (ViewPagerAdapter) pager.getAdapter();

        Fragment newfragment = adapter.getCurrFragment(pager.getCurrentItem());

        TextView mytext = (TextView)newfragment.getView().findViewById(R.id.breed_title);

        TextView mydesctext = (TextView)newfragment.getView().findViewById(R.id.breed_descript);

        ImageView myimage = (ImageView) newfragment.getView().findViewById(R.id.breed_image);

        mPic = myimage.getDrawable();

        StringBuilder sb = new StringBuilder();

        sb.append(mytext.getText()).append("\n").append(mydesctext.getText()).append("\n").append("____________").append("\n").append(getString(R.string.myapp));

        start = getString(R.string.maybithisbreed);
        finish = sb.toString();

        if (isStoragePermissionsGranted()) {
            shareintentwithpicmaker ();
        }

    }

    //ask permission to external storage to share pic of breed with method Urimaker
    private boolean isStoragePermissionsGranted() {

        //version 23 or higher request permissions manually (if check self permissions not success), versions lower read permissions from Manifest
        if (Build.VERSION.SDK_INT >= 23){

            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                return true;
            }
            else  {
               ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else {return true;}
    }

    //implemented callback requested result of permission if isStoragePermissionsGranted() return false
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){shareintentwithpicmaker();}

        if(grantResults[0] == PackageManager.PERMISSION_DENIED){shareintentNOpicmaker();}
    }

    private void shareintentwithpicmaker() {

        Uri myUri = urimaker(); //operation possible only if app have permission WRIRE EXTERNAL STORAGE

        try {
            Intent myintent = ShareCompat.IntentBuilder.from(MainActivity.this)
                    .setText(finish)
                    .setSubject(start)
                    .setStream(myUri)
                    .setType("application/image")
                    .getIntent();
            startActivity(myintent);
        } catch (ActivityNotFoundException e) {
            toastmaker(getString(R.string.nosuchactivity));
        }
    }

    private void shareintentNOpicmaker() {

        toastmaker(getString(R.string.nopicaded));

        try {
            Intent myintent = ShareCompat.IntentBuilder.from(MainActivity.this)
                    .setText(finish)
                    .setSubject(start)
                    .setType("text/plain")
                    .getIntent();
            startActivity(myintent);
        } catch (ActivityNotFoundException e) {
            toastmaker(getString(R.string.nosuchactivity));
        }
    }

    protected void shareintentEmail() {

        try {
            Intent myintent = ShareCompat.IntentBuilder.from(MainActivity.this)
                    .setEmailTo(new String[]{getString(R.string.email_adress)})
                    .setSubject(getString(R.string.email_subj))
                    .setType("text/plain")
                    .getIntent();
            startActivity(myintent);
        } catch (ActivityNotFoundException e) {
            toastmaker(getString(R.string.nosuchactivity));
        }
    }



    //make Uri from drawable resource
    public Uri urimaker (){

        Drawable mDrawable = getmPic();

        Bitmap mybitmap = ((BitmapDrawable)mDrawable).getBitmap();

        ContentValues myvalues = new ContentValues();

        myvalues.put(MediaStore.Images.Media.TITLE, "title");
        myvalues.put (MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        Uri myUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, myvalues);

        OutputStream outputStream;

        try{
            outputStream = getContentResolver().openOutputStream(myUri);
            mybitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();

        }catch (Exception e){
            System.err.print(e.toString());
        }
        return myUri;
    }

    // if button was pressed and trying next time, set toast about
    public void toastmaker(String s) {

       final Toast myToast = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG);
        myToast.setGravity(Gravity.CENTER, 0, 30);
        myToast.show();

    }

    //init database if it does not
    public void databaseinitiator(){

            BreedDataBaseCreator myDataCreator = new BreedDataBaseCreator(this, inact);
            myDataCreator.databasecreator();
            myDataCreator.onCreateDb(null);
            inact.setListOfTitles();
    }

}

// TODO: 17.08.2017 использовать двухуровневый подбор как при машинном обучении, групируя параметры в т.ч. в пересекающиеся группы