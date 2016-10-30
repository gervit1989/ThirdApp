package com.skillbranch.thirdapp.mvp.views;


import com.skillbranch.thirdapp.data.storage.models.ProductDTO;
import com.skillbranch.thirdapp.mvp.presenters.IProductPresenter;

/**
 * Абстракция реализующая набор методов представления для авторизации
 */
public interface IProductView extends IView{

    /**
     * Presenter
     *
     * @return
     */
    IProductPresenter getPresenter();


    void showProductView(ProductDTO product);

    void updateProductCountView();
}