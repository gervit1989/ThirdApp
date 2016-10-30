package com.skillbranch.thirdapp.data.managers;

import android.content.SharedPreferences;
import android.net.Uri;

import com.skillbranch.thirdapp.utils.ConstantManager;
import com.skillbranch.thirdapp.utils.SkillBranchApplication;

/**
 * Класс, отвечающий за сохранение/загрузку пользовательских данных
 */
public class PreferencesManager {

    /**
     * доступ к значениям
     */
    private SharedPreferences mSharedPreferences;


    /**
     * Конструктор
     */
    public PreferencesManager() {

        this.mSharedPreferences = SkillBranchApplication.getSharedPreferences();
    }

    /**
     * Проверка токена email
     * @param authToken
     * @return
     */
    public boolean checkAuthToken(String authToken){
        String s = getAuthToken();
        if (s.equals(authToken)){
            return true;
        }
        return false;
    }

    /**
     * Сохранить токена email
     * @param authToken
     */
    public void saveAuthToken(String authToken){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ConstantManager.REG_USER_LOGIN_KEY,authToken);
        editor.apply();
        String s = getAuthToken();
        if (s.equals(authToken)){
            s="12";
        }
        else {
            s="13";
        }
    }

    /**
     * Получить токен
     * @return
     */
    public String getAuthToken(){
        String token = mSharedPreferences.getString(ConstantManager.REG_USER_LOGIN_KEY, "null");
        return token;
    }
    /**
     * Проверка токена pass
     * @param authPassToken
     * @return
     */
    public boolean checkAuthPassToken(String authPassToken){
        String s = getAuthPassToken();
        if (s.equals(authPassToken)){
            return true;
        }
        return false;
    }

    /**
     * Сохранить токена pass
     * @param authPassToken
     */
    public void saveAuthPassToken(String authPassToken){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(ConstantManager.REG_USER_PASS_KEY, authPassToken);
        editor.apply();
    }

    /**
     * Получить токен pass
     * @return
     */
    public String getAuthPassToken(){
        return mSharedPreferences.getString(ConstantManager.REG_USER_PASS_KEY, "null");
    }

    /**
     * Получить путь к фото
     * @return
     */
    public Uri loadUserAvatar() {
        return Uri.parse(mSharedPreferences.getString(ConstantManager.USER_AVATAR_URL_KEY,
                "android.resource://com.softdesign.devintensive/drawable/no_avatar"));
    }

    /**
     * Сохраняем путь к сохраненному фото
     * @param uri - путь к фото
     */
    public void saveUserAvatar(Uri uri){
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(ConstantManager.USER_AVATAR_URL_KEY, uri.toString());
        editor.apply();
    }
}
