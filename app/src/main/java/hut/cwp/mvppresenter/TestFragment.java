package hut.cwp.mvppresenter;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mvp.MvpFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends MvpFragment<TestPresenter,ITest> {


    public TestFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

}
