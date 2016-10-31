package com.skillbranch.thirdapp.mvp.presenters;

import com.skillbranch.thirdapp.data.storage.models.ProductDTO;

import java.util.HashMap;
import java.util.Map;

public class ProductPresenterFactory {
    private static final Map<String, ProductPresenter> sPresenterMap = new HashMap<>();

    private static void registerPresenter(ProductDTO product, ProductPresenter presenter){
        sPresenterMap.put(String.valueOf(product.getId()), presenter);
    }

    public static ProductPresenter getInstance(ProductDTO product){
        ProductPresenter presenter = sPresenterMap.get(String.valueOf(product.getId()));
        if (presenter == null){
            presenter = ProductPresenter.newIstance(product);
            registerPresenter(product, presenter);
        }
        return presenter;
    }

}
