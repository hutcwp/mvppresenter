package hut.cwp.mylibrary.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.components.support.RxFragment;

import hut.cwp.mylibrary.util.MLog;


public class MvpFragment<P extends MvpPresenter<V>, V extends MvpView> extends RxFragment
        implements MvpView {

    private static final String TAG = "MvpFragment";

    protected P mPresenter;


    public P createPresenter() {
        if (mPresenter == null) {
            mPresenter = getPresenterBinder(getMvpView());
        }
        return mPresenter;
    }


    @NonNull
    public P getPresenter() {
        return mPresenter;
    }


    @NonNull
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(getMvpView());
            mPresenter.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        getPresenter().onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        getPresenter().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
    }


    private P getPresenterBinder(V v) {
        Class<?> tClass = v.getClass();
        boolean isBindPresenter = tClass.isAnnotationPresent(BindPresenter.class);
        if (isBindPresenter) {
            BindPresenter annotation = tClass.getAnnotation(BindPresenter.class);
            try {
                MLog.debug(TAG, "create presenter instance success");
                P p =  (P) annotation.presenter().newInstance();
                return p;
            } catch (InstantiationException e1) {
                MLog.error(TAG, "create presenter fail : " + e1.getMessage());
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
