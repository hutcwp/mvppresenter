package hut.cwp.core

import android.os.Bundle
import androidx.fragment.app.DialogFragment

/**
 * MvpDialogFragment
 */
class MvpDialogFragment<P : MvpPresenter<V>, V : MvpView> :
        DialogFragment(), MvpInnerDelegateCallback<P, V>, MvpView {

    private var mPresenter: P? = null
    private var mMvpInnerDelegate: MvpInnerDelegate<P, V>? = null

    override fun createPresenter(): P {
        if (mPresenter == null) {
            mPresenter = getMvpDelegate().createPresenter()
        }
        return mPresenter!!
    }

    override fun setPresenter(presenter: P) {
        mPresenter = presenter
    }

    override fun getPresenter(): P {
        return mPresenter!!
    }

    override fun getMvpView(): V {
        return this as V
    }

    override fun getMvpDelegate(): MvpInnerDelegate<P, V> {
        if (mMvpInnerDelegate == null) {
            mMvpInnerDelegate = MvpInnerDelegate(this)
        }
        return mMvpInnerDelegate!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPresenter()
        getMvpDelegate().attach(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        getPresenter().onStart()
    }

    override fun onPause() {
        super.onPause()
        getPresenter().onPause()
    }

    override fun onResume() {
        super.onResume()
        getPresenter().onResume()
    }

    override fun onStop() {
        super.onStop()
        getPresenter().onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        getPresenter().onDestroy()
        getMvpDelegate().detach()
    }
}