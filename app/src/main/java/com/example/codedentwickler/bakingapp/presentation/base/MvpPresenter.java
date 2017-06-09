package com.example.codedentwickler.bakingapp.presentation.base;

/**
 * Created by codedentwickler on 5/22/17.
 */


/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
