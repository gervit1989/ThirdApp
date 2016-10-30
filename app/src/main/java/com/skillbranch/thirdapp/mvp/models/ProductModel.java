package com.skillbranch.thirdapp.mvp.models;

import com.skillbranch.thirdapp.data.managers.DataManager;
import com.skillbranch.thirdapp.data.storage.models.ProductDTO;

public class ProductModel {
    DataManager mDataManager = DataManager.getINSTANCE();


    public ProductDTO getProductById(int product_id){
        return mDataManager.getProductById(product_id);
    }

    public void updateProduct(ProductDTO product){
        mDataManager.updateProduct(product);
    }
}
