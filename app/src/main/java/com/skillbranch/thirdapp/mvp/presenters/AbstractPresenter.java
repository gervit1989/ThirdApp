package com.skillbranch.thirdapp.mvp.presenters;

import android.support.annotation.Nullable;

import com.skillbranch.thirdapp.mvp.views.IView;

public abstract class AbstractPresenter<T extends IView> {
    private T mView;

    public void takeView(T view){
        mView = view;
    }

    public abstract void initView();

    public void dropView(){
        mView = null;
    }

    @Nullable
    public T getView(){
        return mView;
    }
}
