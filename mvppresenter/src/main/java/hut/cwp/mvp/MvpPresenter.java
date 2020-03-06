package hut.cwp.mvp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * base presenter for referring to the attach view
 *
 */

public class MvpPresenter<V extends MvpView> {

    private V viewRef;

    @Nullable
    protected V getView(){
        return viewRef;
    }

    /**
     * 保护空指针
     * @return 非空类
     */
    protected CompatOptional<V> getMvpView(){
        return CompatOptional.ofNullable(getView());
    }

    /**
     * 更新activity/fragment中Bundle
     * @param arguments 参数
     * return boolean 结果
     */
    public boolean setArguments(Bundle arguments){
        if(viewRef != null){
            if(viewRef instanceof Fragment){
                ((Fragment) viewRef).setArguments(arguments);
                return true;
            }else if(viewRef instanceof Activity && ((Activity) viewRef).getIntent() != null){
                ((Activity) viewRef).getIntent().putExtras(arguments);
                return true;
            }
        }
        return false;
    }

    /**
     * 获取activity/fragment中Bundle
     * @return Bundle
     */
    public Bundle getArguments(){
        if(viewRef != null){
            if(viewRef instanceof Activity && ((Activity) viewRef).getIntent() != null){
                return ((Activity) viewRef).getIntent().getExtras();
            }else if(viewRef instanceof Fragment){
                return ((Fragment) viewRef).getArguments();
            }
        }
        return null;
    }

    /**
     * attach fragment/activity  指针
     * @param view view
     */
    protected void attachView(V view){
        viewRef = view;
    }

    /**
     * 对应 activity/fragment onCreate
     * @param  savedInstanceState 参数
     */
    protected void onCreate(Bundle savedInstanceState){}

    /**
     * 对应 activity/fragment onDestroy
     */
    protected void onDestroy() {
        if (viewRef != null) {
            viewRef = null;
        }
    }

    /**
     * 对应 activity/fragment onStart
     */
    protected void onStart(){}

    /**
     * 对应 activity/fragment onResume
     */
    protected void onResume(){}

    /**
     * 对应 activity/fragment onPause
     */
    protected void onPause(){}

    /**
     * 对应 activity/fragment onStop
     */
    protected void onStop(){}

    protected void detachView() {}
}
