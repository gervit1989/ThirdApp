package com.skillbranch.thirdapp.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.Log;

import com.skillbranch.thirdapp.R;


/**
 * Переопределение приложения
 */
public class SkillBranchApplication extends Application {
    private static final String TAG = "SkillBranchAuthApp";

    /**
     * Доступ к пользовательским значениям
     */
    public static SharedPreferences sSharedPreferences;

    /**
     * Контекст приложения
     */
    private static Context sContext;
    private static Typeface fontRegular;
    private static Typeface fontBook;

    public static Typeface getCustomRegularFont() {
        return fontRegular;
    }

    public static Typeface getCustomBookFont() {
        return fontBook;
    }



    /**
     * Публичный доступ к контексту приложения
     * @return контекст
     */
    public static Context getContext() {
        return sContext;
    }

    /**
     * При создании
     */
    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        //- Получаем контекст
        sContext = getApplicationContext();

        //- Пользовательские значения
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //- Загрузка шрифтов
        fontRegular = Typeface.createFromAsset(getAssets(), getString(R.string.font_regular));
        fontBook = Typeface.createFromAsset(getAssets(), getString(R.string.font_book));
    }

    /**
     * Геттер для пользовательских значений
     * @return пользовательские значения
     */
    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }
}
