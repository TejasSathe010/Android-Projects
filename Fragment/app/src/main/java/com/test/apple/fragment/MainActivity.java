package com.test.apple.fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements FragmentB.FragmentBListener , FragmentA.FragmentAListener{

    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_a,fragmentA);
        ft.replace(R.id.container_b,fragmentB);
        ft.commit();
    }

    @Override
    public void onInputBSent(CharSequence input) {
        fragmentB.updateEditText(input);
    }

    @Override
    public void onInputASent(CharSequence input) {
        fragmentA.updateEditText(input);
    }
}
