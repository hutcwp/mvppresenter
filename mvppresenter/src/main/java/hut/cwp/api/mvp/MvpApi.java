package hut.cwp.api.mvp;

import android.util.Log;

import hut.cwp.annotations.mvp.DelegateBind;

public class MvpApi {

    /**
     * 根据MVP中的v生成对应PresenterBinder类
     */
    public static <P, V> PresenterBinder<P, V> getPresenterBinder(V v) {
        Class<?> tClass = v.getClass();
        boolean delegate = false;

        if (null != tClass.getAnnotation(DelegateBind.class)) {
            delegate = true;
        }

        if (delegate) {
            String clsName = tClass.getName();
            try {
                Class<?> presenterBinderClass = Class.forName(clsName + "$$PresenterBinder");
                return (PresenterBinder<P, V>) presenterBinderClass.getConstructor().newInstance();
            } catch (Exception e) {
                Log.e("MvpApi getPresenter", "create Presenter fail, reason is " + e.getMessage());
            }
        }
        return null;
    }

}
