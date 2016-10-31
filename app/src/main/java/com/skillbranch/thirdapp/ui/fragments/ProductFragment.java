package com.skillbranch.thirdapp.ui.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.skillbranch.thirdapp.R;

import com.skillbranch.thirdapp.data.storage.models.ProductDTO;
import com.skillbranch.thirdapp.mvp.presenters.IProductPresenter;
import com.skillbranch.thirdapp.mvp.presenters.ProductPresenter;
import com.skillbranch.thirdapp.mvp.presenters.ProductPresenterFactory;
import com.skillbranch.thirdapp.mvp.views.IProductView;
import com.skillbranch.thirdapp.ui.activities.RootActivity;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductFragment extends Fragment implements IProductView, View.OnClickListener{

    @BindView(R.id.product_name)
    TextView mProductNameTxt;

    @BindView(R.id.product_description)
    TextView mProductDescription;

    @BindView(R.id.product_counter)
    TextView mProductCounterField;

    @BindView(R.id.product_price)
    TextView mProductPriceField;

    @BindView(R.id.product_image)
    ImageView mProductImage;

    @BindView(R.id.minus_btn)
    ImageButton mMinusBtn;

    @BindView(R.id.plus_btn)
    ImageButton mPlusBtn;

    @BindDrawable(R.drawable.product_example)
    Drawable mResProductImage;

    private ProductPresenter mPresenter;

    public ProductFragment() {
    }

    public static ProductFragment newInstance(ProductDTO product){
        Bundle bundle = new Bundle();
        bundle.putParcelable("PRODUCT", product);
        ProductFragment fragment = new ProductFragment();

        fragment.setArguments(bundle);
        return fragment;
    }

    private void readBundle(Bundle bundle){
        if (bundle!=null){
            ProductDTO product = bundle.getParcelable("PRODUCT");
            mPresenter = ProductPresenterFactory.getInstance(product);
        }
    }

    @Override
    public void onDestroyView() {
        mPresenter.dropView();
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, view);

        readBundle(getArguments());
        mPresenter.takeView(this);
        mPresenter.initView();

        mPlusBtn.setOnClickListener(this);
        mMinusBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.minus_btn:
                mPresenter.clickOnMinus();
                break;
            case R.id.plus_btn:
                mPresenter.clickOnPlus();
                break;
        }
    }


    //region ========================= IProductView =========================
    @Override
    public IProductPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showProductView(ProductDTO product) {
        mProductNameTxt.setText(product.getProductName());
        mProductDescription.setText(product.getDescription());
        mProductPriceField.setText(product.getPrice()+".-");
        mProductCounterField.setText(String.valueOf(product.getCount()));
        if (product.getCount()>0){
            mProductPriceField.setText(String.valueOf(product.getCount()*product.getPrice()+".-"));
        }
        else {
            mProductPriceField.setText(String.valueOf(product.getPrice()+".-"));
        }
        mProductImage.setImageDrawable(mResProductImage);
    }

    @Override
    public void updateProductCountView(ProductDTO product) {
        mProductCounterField.setText(String.valueOf(product.getCount()));
        if (product.getCount()>0){
            mProductPriceField.setText(String.valueOf(product.getCount()*product.getPrice()+".-"));
        }
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
