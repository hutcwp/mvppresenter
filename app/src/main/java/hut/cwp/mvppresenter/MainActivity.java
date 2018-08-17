package hut.cwp.mvppresenter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apt_lib.annotation.BindView;
import com.example.apt_lib.annotation.InitAttrConfig;
import com.example.apt_lib.annotation.InitAttrConfigs;

import mvp.BindPresenter;
import mvp.MvpActivity;


@InitAttrConfigs({
        @InitAttrConfig(component = TestFragment.class, resourceId = R.id.fragment_content)
})
@BindPresenter(presenter = MainPresenter.class)
public class MainActivity extends MvpActivity<MainPresenter, IMain> implements IMain {

    @BindView(R.id.iv)
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setTextView(View v){

        getPresenter().click();
    }

    @Override
    public void changeText() {
        Toast.makeText(this,"hahah",Toast.LENGTH_LONG).show();
    }
}
