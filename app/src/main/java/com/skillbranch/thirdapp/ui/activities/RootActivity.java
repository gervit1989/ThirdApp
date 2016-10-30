package com.skillbranch.thirdapp.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.skillbranch.thirdapp.R;
import com.skillbranch.thirdapp.mvp.views.IMainView;

public class RootActivity extends AppCompatActivity implements IMainView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
