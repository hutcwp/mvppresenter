package hut.cwp.mvppresenter;

import android.os.Bundle;
import android.widget.Toast;

import mvp.MvpPresenter;

/**
 * Created by hutcwp on 2018/8/17 23:44
 * email: caiwenpeng@yy.com
 * YY: 909076244
 **/
public class MainPresenter extends MvpPresenter<IMain> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void click(){
        getView().changeText();
    }
}
