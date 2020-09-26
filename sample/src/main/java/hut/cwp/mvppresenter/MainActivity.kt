package hut.cwp.mvppresenter

import android.os.Bundle
import android.view.View
import android.widget.Toast
import hut.cwp.annotations.mvp.DelegateBind
import hut.cwp.core.MvpActivity

// import me.hutcwp.liba.LibAMainActivity;
// @InitAttrConfigs({
//         @InitAttrConfig(component = TestFragment.class, resourceId = R.id.fragment_content)
// })
@DelegateBind(presenter = MainPresenter::class)
class MainActivity : MvpActivity<MainPresenter, IMain>(), IMain {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Injector.injectContainer(this);
    }

    fun setTextView(v: View?) {
        presenter.click()
    }

    override fun changeText() {
        Toast.makeText(this, "hahah", Toast.LENGTH_LONG).show()
        // startActivity(new Intent(this, LibAMainActivity.class));
    }
}