package com.example.rndroid.splashcreens_usingsharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //take a preference file - with int valiable - counter
//        if counter = 0, display splash, and make counter 1;
        // if counter is 1 then show fragment 2
        final FragmentManager fm = getSupportFragmentManager();
        SharedPreferences sp = getSharedPreferences("splash", 0);//creating a file splash.xml(Installing Application)
        SharedPreferences.Editor editor = sp.edit();

        int counter = sp.getInt("counter", 0);//will search for counter variable in splash.xml file , if not present then return default
        //value
        if (counter == 1){//it will not enter because initially counter is not available
            //it will enter here if aplication is already installed and will not show splash screen
            Fragment_Two fragment_two = new Fragment_Two();
            FragmentTransaction ft1 = fm.beginTransaction();
            ft1.add(R.id.container1, fragment_two);
            ft1.commit();
            return;
        }
            // for first time isntalling
            Splash_Fragment f1 = new Splash_Fragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.container1, f1);
            ft.commit();
        // edit value of counter by 1
            editor.putInt("counter", 1);
            editor.commit();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Fragment_Two fragment_two = new Fragment_Two();
                    FragmentTransaction ft1 = fm.beginTransaction();
                    ft1.replace(R.id.container1, fragment_two);
                    ft1.commit();
                }
            }, 5000);//after five second DVM will jump to above run() method
         }
}
