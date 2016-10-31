package com.skillbranch.thirdapp.mvp.views;

import com.skillbranch.thirdapp.data.storage.models.ProductDTO;

import java.util.List;

public interface ICatalogView extends IView{
    void showAddToCardMessage(ProductDTO product);

    void showCatalogView(List<ProductDTO> productList);

    void showAuthScreen();

    void updateProductCounter();

}
