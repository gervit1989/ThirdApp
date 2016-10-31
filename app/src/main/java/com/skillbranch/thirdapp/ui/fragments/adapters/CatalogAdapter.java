package com.skillbranch.thirdapp.ui.fragments.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.skillbranch.thirdapp.data.managers.DataManager;
import com.skillbranch.thirdapp.data.storage.models.ProductDTO;
import com.skillbranch.thirdapp.ui.fragments.ProductFragment;

import java.util.ArrayList;
import java.util.List;

public class CatalogAdapter extends FragmentPagerAdapter {

    private List<ProductDTO> mProductDTOList=new ArrayList<>();

    public CatalogAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ProductFragment.newInstance(mProductDTOList.get(position));
    }

    public void addItem(ProductDTO product){
        mProductDTOList.add(product);

    }

    @Override
    public int getCount() {
        return mProductDTOList.size();
    }
}
