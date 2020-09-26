package hut.cwp.mvppresenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hut.cwp.annotations.mvp.DelegateBind
import hut.cwp.core.MvpFragment

@DelegateBind(presenter = TestPresenter::class)
class TestFragment : MvpFragment<TestPresenter, ITest >() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup,
            savedInstanceState: Bundle
    ): View {
        return inflater.inflate(R.layout.fragment_test, container, false)
    }
}