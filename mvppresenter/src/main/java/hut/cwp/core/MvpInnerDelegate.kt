package hut.cwp.core

import android.os.Bundle
import hut.cwp.api.mvp.MvpApi
import hut.cwp.api.mvp.PresenterBinder

/**
 * a delegate for [MvpFragment] or [MvpActivity] handle some logic
 */
class MvpInnerDelegate<P : MvpPresenter<V>, V : MvpView>(
        private val mMvpInnerDelegateCallback: MvpInnerDelegateCallback<P, V>
) {
    private var mPresenterBinder: PresenterBinder<P, V>? = null

    /**
     * createPresenter封装在delegate中，避免在activity/fragment中多次出现
     *
     * @return
     */
    fun createPresenter(): P {
        var presenter: P? = null
        if (mPresenterBinder == null) {
            mPresenterBinder = MvpApi.getPresenterBinder(mMvpInnerDelegateCallback.getMvpView())
            presenter = mPresenterBinder!!.bindPresenter(mMvpInnerDelegateCallback.getMvpView())
        }
        return presenter ?: MvpPresenter<V>() as P
    }

    /**
     * attach MvpView[MvpActivity]/[MvpFragment] to [MvpPresenter]
     *
     * @param savedInstanceState
     */
    fun attach(savedInstanceState: Bundle?) {
        for (ii in mMvpInnerDelegateCallback.javaClass.interfaces) {
            //if (ii.isAssignableFrom(MvpView.class)) {
            if (MvpView::class.java.isAssignableFrom(ii)) {
                mMvpInnerDelegateCallback.getPresenter()!!.attachView(
                        mMvpInnerDelegateCallback.getMvpView())
                mMvpInnerDelegateCallback.getPresenter()!!.onCreate(savedInstanceState)
                break
            }
        }
    }

    /**
     * detach PresenterBinder<P></P>, V>,
     * 解除MvpView[MvpActivity]/[MvpFragment] 对 [MvpPresenter]中的数据绑定
     */
    fun detach() {
        if (mPresenterBinder != null) {
            mPresenterBinder!!.unbindPresenter()
        }
        mPresenterBinder = null
    }
}