package com.skillbranch.thirdapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.skillbranch.thirdapp.R;
import com.skillbranch.thirdapp.data.managers.DataManager;
import com.skillbranch.thirdapp.data.storage.models.ProductDTO;
import com.skillbranch.thirdapp.mvp.presenters.CatalogPresenter;
import com.skillbranch.thirdapp.mvp.presenters.ICatalogPresenter;
import com.skillbranch.thirdapp.mvp.views.ICatalogView;
import com.skillbranch.thirdapp.ui.activities.RootActivity;
import com.skillbranch.thirdapp.ui.fragments.adapters.CatalogAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogFragment extends Fragment implements ICatalogView, View.OnClickListener{

    private CatalogPresenter mPresenter;

    @BindView(R.id.add_to_card_btn)
    Button mButton;

    @BindView(R.id.product_pager)
    ViewPager mViewPager;

    public CatalogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        ButterKnife.bind(this, view);

        mPresenter.takeView(this);
        mPresenter.initView();

        mButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        mPresenter.dropView();
        super.onDestroyView();
    }

    //region ========================= Life cycle  =========================
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_to_card_btn:
                mPresenter.clickOnBuyButton(mViewPager.getCurrentItem());
                break;
        }
    }
    //endregion

    //region ========================= ICatalogView =========================

    @Override
    public void showAddToCardMessage(ProductDTO product) {
        showMessage("Товар "+ product.getProductName() + " успешно добавлен в корзину");
    }

    @Override
    public void showCatalogView(List<ProductDTO> productList) {
        CatalogAdapter adapter = new CatalogAdapter(getChildFragmentManager());
        List<ProductDTO> list = DataManager.getINSTANCE().getProductList();
        for (int i = 0; i < list.size(); i++) {
            adapter.addItem(list.get(i));
        }
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void showAuthScreen() {

    }

    @Override
    public void updateProductCounter() {

    }
    //endregion

    //region ========================= IView =========================
    @Override
    public void showMessage(String message) {
        getRootActivity().showMessage(message);
    }

    @Override
    public void showError(Throwable e) {
        getRootActivity().showError(e);
    }

    @Override
    public void showLoad() {
        getRootActivity().showLoad();
    }

    @Override
    public void hideLoad() {
        getRootActivity().hideLoad();
    }
    //endregion

    private RootActivity getRootActivity(){
        return (RootActivity)getActivity();
    }
}
