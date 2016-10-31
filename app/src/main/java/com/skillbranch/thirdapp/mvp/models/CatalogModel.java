package com.skillbranch.thirdapp.mvp.models;

import com.skillbranch.thirdapp.data.managers.DataManager;
import com.skillbranch.thirdapp.data.storage.models.ProductDTO;

import java.util.List;

public class CatalogModel {
    public CatalogModel() {
    }

    public List<ProductDTO> getProductList(){
        return DataManager.getINSTANCE().getProductList();
    }

    public boolean isUserAuth(){
        return DataManager.getINSTANCE().isAuthUser();
    }
}
