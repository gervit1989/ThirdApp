package com.skillbranch.thirdapp.mvp.presenters;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.skillbranch.thirdapp.data.storage.models.ProductDTO;
import com.skillbranch.thirdapp.mvp.models.ProductModel;
import com.skillbranch.thirdapp.mvp.views.IProductView;

public class ProductPresenter extends AbstractPresenter<IProductView> implements IProductPresenter {

    /**
     * Паттерн синглтон
     */
    private static ProductPresenter ourInstance = null;

    /**
     * Модель
     */
    private ProductModel mProductModel;
    private ProductDTO mProduct;


    /**
     * Конструктор
     */
    public ProductPresenter(ProductDTO product) {
        mProductModel = new ProductModel();
        mProduct = product;
    }

    /**
     * Возвращаем сами себя
     * @return
     */
    public static ProductPresenter newIstance(ProductDTO product){
        if (ourInstance ==null){
            ourInstance = new ProductPresenter(product);
        }
        return new ProductPresenter(product);
    }

    @Override
    public void initView() {
        if (getView()!=null){
            getView().showProductView(mProduct);
        }
    }

    @Override
    public void clickOnPlus() {
        mProduct.addProduct();
        mProductModel.updateProduct(mProduct);

        if (getView()!=null){
            getView().updateProductCountView(mProduct);
        }
    }

    @Override
    public void clickOnMinus() {
        if (mProduct.getCount()>0){
            mProduct.deleteProduct();
            mProductModel.updateProduct(mProduct);

            if (getView()!=null){
                getView().updateProductCountView(mProduct);
            }
        }
    }
}
