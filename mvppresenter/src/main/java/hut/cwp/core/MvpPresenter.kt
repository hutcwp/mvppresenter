package hut.cwp.core

import android.os.Bundle
import hut.cwp.util.CompatOptional

/**
 * base presenter for referring to the attach view
 */
open class MvpPresenter<V : MvpView?> {
    protected var view: V? = null
        private set

    /**
     * 保护空指针
     * @return
     */
    protected val mvpView: CompatOptional<V>
        protected get() = CompatOptional.ofNullable(view)

    /**
     * attach fragment/activity  指针
     * @param view
     */
    fun attachView(view: V) {
        this.view = view
    }

    /**
     * 对应 activity/fragment onCreate
     */
    open fun onCreate(savedInstanceState: Bundle?) {}

    /**
     * 对应 activity/fragment onDestroy
     */
    fun onDestroy() {
        if (view != null) {
            view = null
        }
    }

    /**
     * 对应 activity/fragment onStart
     */
    fun onStart() {}

    /**
     * 对应 activity/fragment onResume
     */
    fun onResume() {}

    /**
     * 对应 activity/fragment onPause
     */
    fun onPause() {}

    /**
     * 对应 activity/fragment onStop
     */
    fun onStop() {}
}