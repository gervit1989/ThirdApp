package com.skillbranch.thirdapp.ui.activities;

import android.graphics.Bitmap;
import android.support.design.BuildConfig;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.skillbranch.thirdapp.R;
import com.skillbranch.thirdapp.data.managers.DataManager;
import com.skillbranch.thirdapp.mvp.views.IView;
import com.skillbranch.thirdapp.ui.fragments.CatalogFragment;
import com.skillbranch.thirdapp.utils.ConstantManager;
import com.skillbranch.thirdapp.utils.RoundedAvatarDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RootActivity extends AppCompatActivity implements IView {

    private static final String TAG = "RootActivity";

    /**
     * Пользовательские настройки
     */
    private DataManager mDataManager;

    @BindView(R.id.catalog_drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.catalog_coordinator_container)
    CoordinatorLayout mCoordinatorLayout;

    ImageView mAccountAvatar;

    @BindView(R.id.catalog_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.catalog_drawer)
    NavigationView mNavigationView;

    @BindView(R.id.catalog_fragment_container)
    FrameLayout mFrameLayout;

    FragmentManager mFragmentManager;

    //region ========================= Life cycle =========================

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        initAvatarImage();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initDrawer();

        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null){
            //mFragmentManager.beginTransaction().replace(R.id.catalog_fragment_container, new CatalogFragment()).commit();
        }

    }

    /**
     * Тулбар сверху
     */
    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Выдвижное меню
     */
    private void initDrawer() {
        initAvatarImage();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,mToolbar, R.string.open_drawer_str, R.string.close_drawer_str);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //showMessage(item.getTitle().toString());
                item.setChecked(true);
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.user_account_menu:
                        break;
                    case R.id.goods_catalog_menu:
                        fragment = new CatalogFragment();
                        break;
                    case R.id.favorites_menu:
                        break;
                    case R.id.orders_menu:
                        break;
                    case R.id.notices_menu:
                        break;
                }
                if (fragment!=null){
                    mFragmentManager.beginTransaction().replace(R.id.catalog_fragment_container, fragment).addToBackStack(null).commit();
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    /**
     * Создание скругленного аватара
     */
    private void initAvatarImage() {
        String photoURL = getIntent().getStringExtra(ConstantManager.USER_AVATAR_URL_KEY);
        mAccountAvatar = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.account_avatar);
        /*final Uri photoLocalUri = mDataManager.getPreferencesManager().loadUserAvatar();

        Call<ResponseBody> call = mDataManager.getImage(photoURL);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                    if (bitmap != null) {
                        Log.d(TAG, "onResume2");
                        makeRoundAvatarFromBitmap(bitmap);
                        try {
                            File file = createImageFileFromBitmap("user_avatar", bitmap);
                            if (file != null) {
                                mDataManager.getPreferencesManager()
                                        .saveUserAvatar(Uri.fromFile(file));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    else {
                        Log.d(TAG, "onResume3");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //showShackbar("Не удалось загрузить фотографию пользователя");
                File file = new File(Environment.
                        getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"user_avatar.jpg");
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                if (bitmap != null) {
                    Log.d(TAG, "onResume2");
                    makeRoundAvatarFromBitmap(bitmap);
                }
            }
        });*/
    }


    /**
     * Создание скругленного изображения
     * @param bitmap
     */
    private void makeRoundAvatarFromBitmap(Bitmap bitmap) {
        mAccountAvatar = (ImageView) mNavigationView.getHeaderView(0).findViewById(R.id.account_avatar);
        RoundedAvatarDrawable bt = new RoundedAvatarDrawable(bitmap);
        mAccountAvatar.setImageDrawable(bt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //endregion

    //region ========================= IView =========================
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

    }

    @Override
    public void hideLoad() {

    }
    //endregion
}
