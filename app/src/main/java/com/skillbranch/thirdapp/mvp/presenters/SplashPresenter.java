package com.skillbranch.thirdapp.mvp.presenters;

import android.support.annotation.Nullable;

import com.skillbranch.thirdapp.data.managers.DataManager;
import com.skillbranch.thirdapp.mvp.models.SplashModel;
import com.skillbranch.thirdapp.mvp.views.ISplashView;
import com.skillbranch.thirdapp.ui.custom_views.AuthPanel;

public class SplashPresenter extends AbstractPresenter<ISplashView> implements ISplashPresenter{

    /**
     * Паттерн синглтон
     */
    private static SplashPresenter ourInstance = new SplashPresenter();

    /**
     * Модель авторизации
     */
    private SplashModel mAuthModel;

    /**
     * Конструктор
     */
    public SplashPresenter() {
        mAuthModel = new SplashModel();
    }

    /**
     * Возвращаем сами себя
     * @return
     */
    public static SplashPresenter getInstance(){
        return ourInstance;
    }

    @Override
    public void initView() {
        if (getView()!=null) {
            if (checkUserAuth()) {
                getView().hideLoginBtn();
            } else {
                getView().showLoginBtn();
            }
        }
    }

    @Override
    public void clickOnLogin() {
        if (getView()!=null  && getView().getAuthPanel()!=null){
            getView().hideLoad();
            if (getView().getAuthPanel().isIdle()){
                getView().getAuthPanel().setCustomState(AuthPanel.LOGIN_STATE);
            }
            else {
                if (!getView().getAuthPanel().checkUserMailInput() || !getView().getAuthPanel().checkUserPassInput()){
                    if (!getView().getAuthPanel().checkUserMailInput()){
                        getView().showMessage("No email input");
                    }
                    else if(!getView().getAuthPanel().checkUserPassInput()){
                        getView().showMessage("No pass input");
                    }
                }
                else {
                    String userToken = getView().getAuthPanel().getUserEmail();
                    String userPassToken = getView().getAuthPanel().getUserPass();
                    DataManager manager = DataManager.getINSTANCE();
                    if (manager.getPreferencesManager().getAuthToken()!=null){
                        if (!manager.getPreferencesManager().checkAuthToken(userToken)){
                            getView().showMessage("Введенный email не совпадает!");
                        }
                        else {
                            if (manager.getPreferencesManager().getAuthPassToken()!=null){
                                if (!manager.getPreferencesManager().checkAuthPassToken(userPassToken)){
                                    getView().showMessage("Введенный pass не совпадает!");
                                }
                            }
                        }
                    }
                    mAuthModel.loginUser(userToken, userPassToken);
                    getView().showMessage("request for user auth");
                    manager.getPreferencesManager().saveAuthToken(userToken);
                    manager.getPreferencesManager().saveAuthPassToken(userPassToken);
                }
            }
        }
    }

    @Override
    public void clickOnFb() {
        if (getView()!=null){
            getView().showMessage("Facebook");
        }
    }

    @Override
    public void clickOnTwitter() {
        if (getView()!=null){
            getView().showMessage("Twitter");
        }
    }

    @Override
    public void clickOnVk() {
        if (getView()!=null){
            getView().showMessage("VK");
        }
    }

    @Override
    public void clickOnShowCatalog() {
        if (getView()!=null){
            getView().showLoad();
            getView().showMessage("Показать каталог");
            getView().showCatalogScreen();
        }
    }

    @Override
    public boolean checkUserAuth() {
        return mAuthModel.isAuthUser();
    }
}


