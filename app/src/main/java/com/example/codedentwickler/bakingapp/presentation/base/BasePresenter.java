package com.example.codedentwickler.bakingapp.presentation.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by codedentwickler on 5/22/17.
 */

public class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    private T view;

    @Override
    public void attachView(T mvpView) {
        this.view = checkNotNull(mvpView, "View cannot be null");
    }

    @Override
    public void detachView() {
        view = null;
        compositeSubscription.clear();
    }


    public T getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached())
            throw new MvpViewNotAttachedException();
    }

    private boolean isViewAttached() {

        return view != null;
    }

    protected void addSubscription(Subscription subscription) {

        this.compositeSubscription.add(subscription);
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call presenter.attachView(MvpView) before requesting data from the " +
                    "Presenter");
        }
    }
}
