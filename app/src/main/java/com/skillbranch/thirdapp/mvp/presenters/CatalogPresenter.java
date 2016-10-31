package com.skillbranch.thirdapp.mvp.presenters;

import com.skillbranch.thirdapp.data.storage.models.ProductDTO;
import com.skillbranch.thirdapp.mvp.models.CatalogModel;
import com.skillbranch.thirdapp.mvp.views.ICatalogView;

import java.util.ArrayList;
import java.util.List;

public class CatalogPresenter extends AbstractPresenter<ICatalogView> implements ICatalogPresenter{

    private CatalogModel mCatalogModel;

    private static CatalogPresenter ourInstance = new CatalogPresenter();

    private List<ProductDTO> mProductDTOList;

    public static CatalogPresenter getInstance() {
        return ourInstance;
    }

    private CatalogPresenter(){
        mCatalogModel = new CatalogModel();
    }

    @Override
    public void initView() {
        if (mProductDTOList == null){
            mProductDTOList = mCatalogModel.getProductList();

        }
        if (getView()!=null){
            getView().showCatalogView(mProductDTOList);
        }
    }

    @Override
    public void clickOnBuyButton(int position) {
        if (getView()!=null){
            if (checkUserAuth()){
                getView().showAddToCardMessage(mProductDTOList.get(position));
            }
            else {
                getView().showAuthScreen();
            }
        }
    }

    @Override
    public boolean checkUserAuth() {
        return mCatalogModel.isUserAuth();
    }
}
