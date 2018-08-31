package hut.cwp.mvppresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import hut.cwp.annotations.InitAttrConfig;
import hut.cwp.annotations.InitAttrConfigs;
import hut.cwp.api.Injector;
import hut.cwp.mvp.BindPresenter;
import hut.cwp.mvp.MvpActivity;

@InitAttrConfigs({
        @InitAttrConfig(component = TestFragment.class, resourceId = R.id.fragment_content)
})
@BindPresenter(presenter = MainPresenter.class)
public class MainActivity extends MvpActivity<MainPresenter, IMain> implements IMain {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Injector.injectContainer(this);
    }

    public void setTextView(View v){
        getPresenter().click();
    }

    @Override
    public void changeText() {
        Toast.makeText(this,"hahah",Toast.LENGTH_LONG).show();
    }

}
