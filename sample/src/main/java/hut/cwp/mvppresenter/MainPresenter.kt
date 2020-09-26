package hut.cwp.mvppresenter

import android.os.Bundle
import android.util.Log
import hut.cwp.core.MvpPresenter

/**
 * Created by hutcwp on 2018/8/17 23:44
 */
class MainPresenter : MvpPresenter<IMain>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainPresenter", "onCreate invoke")
    }

    fun click() {
        view?.changeText()
    }
}