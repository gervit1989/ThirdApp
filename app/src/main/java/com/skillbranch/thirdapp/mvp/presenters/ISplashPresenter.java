package com.skillbranch.thirdapp.mvp.presenters;

import android.support.annotation.Nullable;

import com.skillbranch.thirdapp.mvp.views.ISplashView;

/**
 * Абстракция над бизнес логикой связанной с представлением и взаимодействием с данными.
 * Presenter подписывается на события UI и изменяет данные в модели по запросу. Содержит
 * ссылку на экземпляр модели и экземпляр представления
 */
public interface ISplashPresenter {
    /**
     * обработка щелчка на кнопку "показать каталог"
     */
    void clickOnLogin();

    /**
     * обработка щелчка на кнопку "Facebook"
     */
    void clickOnFb();

    /**
     * обработка щелчка на кнопку "Twitter"
     */
    void clickOnTwitter();

    /**
     * обработка щелчка на кнопку "VK"
     */
    void clickOnVk();

    /**
     * обработка щелчка на кнопку "показать каталог"
     */
    void clickOnShowCatalog();

    /**
     *  проверить есть ли сохраненный токен пользователя (авторизован ли пользователь)
     * @return Да или нет
     */
    boolean checkUserAuth();
}
