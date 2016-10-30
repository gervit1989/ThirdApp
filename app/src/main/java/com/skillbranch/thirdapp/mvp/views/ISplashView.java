package com.skillbranch.thirdapp.mvp.views;

import android.support.annotation.Nullable;

import com.skillbranch.thirdapp.mvp.presenters.ISplashPresenter;
import com.skillbranch.thirdapp.ui.custom_views.AuthPanel;

/**
 * Абстракция реализующая набор методов представления для авторизации
 */
public interface ISplashView extends IView{

    /**
     * Presenter
     * @return
     */
    ISplashPresenter getPresenter();

    /**
     * Показать Кнопку логин
     */
    void showLoginBtn();

    /**
     * Скрыть Кнопку логин
     */
    void hideLoginBtn();

    /**
     * Новая панель авторизации
     * @return
     */
    @Nullable
    AuthPanel getAuthPanel();

    /**
     * Загрузи экран каталогов
     */
    void showCatalogScreen();
}

