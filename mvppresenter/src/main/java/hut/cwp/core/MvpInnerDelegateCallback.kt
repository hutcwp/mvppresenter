package hut.cwp.core

interface MvpInnerDelegateCallback<P : MvpPresenter<V>, V : MvpView> {
    fun createPresenter(): P
    fun setPresenter(presenter: P)
    fun getPresenter(): P?
    fun getMvpView(): V
    fun getMvpDelegate(): MvpInnerDelegate<P, V>
}