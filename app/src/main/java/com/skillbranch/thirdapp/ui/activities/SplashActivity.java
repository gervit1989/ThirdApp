package com.skillbranch.thirdapp.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.BuildConfig;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skillbranch.thirdapp.R;
import com.skillbranch.thirdapp.data.managers.DataManager;
import com.skillbranch.thirdapp.mvp.presenters.ISplashPresenter;
import com.skillbranch.thirdapp.mvp.presenters.SplashPresenter;
import com.skillbranch.thirdapp.mvp.views.ISplashView;
import com.skillbranch.thirdapp.ui.custom_views.AuthPanel;
import com.skillbranch.thirdapp.utils.ConstantManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements ISplashView, View.OnClickListener{
    SplashPresenter mPresenter = SplashPresenter.getInstance();

    /**
     * Координатор
     */
    @BindView(R.id.activity_root)
    CoordinatorLayout mCoordinatorLayout;

    /**
     * Блок авторизации
     */
    @BindView(R.id.auth_wrapper)
    AuthPanel mAuthPanel;
    /**
     * Кнопка Войти
     */
    @BindView(R.id.login_start_btn)
    Button mStartBtn;

    /**
     * Кнопка Показать каталог
     */
    @BindView(R.id.login_show_btn)
    Button mShowBtn;

    /**
     * Текстовое поле
     */
    @BindView(R.id.logo_txt)
    TextView mTextView;

    /**
     * Пользовательские настройки
     */
    private DataManager mDataManager;

    //region ========================= Life cycle =========================

    /**
     * Конструктор
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        mPresenter.takeView(this);
        mPresenter.initView();

        /**
         * Хранилище данных пользователя
         */
        mDataManager = DataManager.getINSTANCE();

        // Установка внешнего шрифта
        if (ConstantManager.IS_CUSTOM_ALL){
            if (ConstantManager.IS_CUSTOM_BOOK_FONT) {
                setFont((ViewGroup) findViewById(R.id.activity_root), mDataManager.getCustomRegularFont());
            }
            else {
                setFont((ViewGroup) findViewById(R.id.activity_root), mDataManager.getCustomRegularFont());
            }
        }
        else {
            if (ConstantManager.IS_CUSTOM_BOOK_FONT){
                mTextView.setTypeface(mDataManager.getCustomBookFont());
            }
            else {
                mTextView.setTypeface(mDataManager.getCustomRegularFont());
            }
        }

        mStartBtn.setOnClickListener(this);
        mShowBtn.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (getAuthPanel()!=null) {
            outState.putString("auth_mail", getAuthPanel().getUserEmail());
            outState.putString("auth_pass", getAuthPanel().getUserPass());
            outState.putBoolean("auth_visible", !getAuthPanel().isIdle());
            LinearLayout progressLay = (LinearLayout) findViewById(R.id.progress_lay);
            if(progressLay!=null) {
                int val = progressLay.getVisibility();
                outState.putInt("progress_visible", val);
            }
        }
        else {
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        if (getAuthPanel()!=null) {
            boolean bAuthVisible = savedInstanceState.getBoolean("auth_visible");
            getAuthPanel().setCustomState(bAuthVisible ? AuthPanel.LOGIN_STATE : AuthPanel.IDLE_STATE);
            getAuthPanel().setUserEmail(savedInstanceState.getString("auth_mail"));
            getAuthPanel().setUserPass(savedInstanceState.getString("auth_pass"));
            LinearLayout progressLay = (LinearLayout) findViewById(R.id.progress_lay);
            if(progressLay!=null) {
                int val = savedInstanceState.getInt("progress_visible");
                if (val==0)
                    progressLay.setVisibility(View.VISIBLE);
                else if(val==4)
                    progressLay.setVisibility(View.GONE);
                else
                    progressLay.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * Установка шрифта для TextView
     * @param group
     * @param font
     */
    public void setFont(ViewGroup group, Typeface font) {
        int count = group.getChildCount();
        View v;
        for(int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if(v instanceof TextView) {
                if(font==null){
                    showMessage("Шрифта не существует");
                }
                else {
                    ((TextView) v).setTypeface(font);
                }
            } else if(v instanceof ViewGroup) {
                setFont((ViewGroup) v, font);
            }
        }
    }
    @Override
    protected void onDestroy() {
        mPresenter.dropView();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    //endregion

    //region ========================= IAuthView =========================

    @Override
    public void showMessage(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(Throwable e) {
        if (BuildConfig.DEBUG){
            showMessage(e.getMessage());
            e.printStackTrace();
        }
        else {
            showMessage("Ошибка! Что то не так!");
            // TODO: 22-10-2016 send error  stacktrace
        }
    }

    @Override
    public void showLoad() {
        // TODO: 22-10-2016 show load progress
        LinearLayout progressLay = (LinearLayout) findViewById(R.id.progress_lay);
        if(progressLay!=null) {
            progressLay.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideLoad() {
        // TODO: 22-10-2016 hide load progress
        LinearLayout progressLay = (LinearLayout) findViewById(R.id.progress_lay);
        if(progressLay!=null){
            progressLay.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public ISplashPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showLoginBtn() {
        mStartBtn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoginBtn() {
        mStartBtn.setVisibility(View.GONE);
    }

    @Override
    public AuthPanel getAuthPanel() {
        return mAuthPanel;
    }

    @Override
    public void showCatalogScreen() {
        Intent intent = new Intent(this, RootActivity.class);
        startActivity(intent);
    }
    //endregion


    @Override
    public void onBackPressed() {
        if (!mAuthPanel.isIdle()){
            mAuthPanel.setCustomState(AuthPanel.IDLE_STATE);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login_show_btn:
                mPresenter.clickOnShowCatalog();
                break;
            case R.id.login_start_btn:
                mPresenter.clickOnLogin();
                break;
        }
    }
}
