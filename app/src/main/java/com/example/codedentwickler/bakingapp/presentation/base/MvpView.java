package com.example.codedentwickler.bakingapp.presentation.base;

/**
 * Created by codedentwickler on 5/22/17.
 */
import android.support.annotation.StringRes;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View Presenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {

    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void showSnackBarMessage(String message);

    void onError(String message);

    boolean isNetworkConnected();

    void hideKeyboard();

}
