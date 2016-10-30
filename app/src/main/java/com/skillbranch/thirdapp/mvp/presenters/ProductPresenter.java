package com.skillbranch.thirdapp.mvp.presenters;

import android.support.annotation.Nullable;

import com.skillbranch.thirdapp.mvp.models.ProductModel;
import com.skillbranch.thirdapp.mvp.views.IProductView;

public class ProductPresenter extends AbstractPresenter<IProductView> implements IProductPresenter {

    /**
     * Паттерн синглтон
     */
    private static ProductPresenter ourInstance = new ProductPresenter();

    /**
     * Модель
     */
    private ProductModel mProductModel;


    /**
     * Конструктор
     */
    public ProductPresenter() {
        mProductModel = new ProductModel();
    }

    /**
     * Возвращаем сами себя
     * @return
     */
    public static ProductPresenter getInstance(){
        return ourInstance;
    }

    @Override
    public void initView() {
        if (getView()!=null){

        }
    }

    @Override
    public void clickOnPlus() {

    }

    @Override
    public void clickOnMinus() {

    }
}
