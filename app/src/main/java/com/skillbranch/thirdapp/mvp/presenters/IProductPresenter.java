package com.skillbranch.thirdapp.mvp.presenters;


import android.support.annotation.Nullable;

import com.skillbranch.thirdapp.mvp.views.IProductView;

/**
 * Абстракция над бизнес логикой связанной с представлением и взаимодействием с данными.
 * Presenter подписывается на события UI и изменяет данные в модели по запросу. Содержит
 * ссылку на экземпляр модели и экземпляр представления
 */
public interface IProductPresenter {
    void clickOnPlus();

    void clickOnMinus();
}

