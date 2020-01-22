package com.xuhuawei.aptapi;

import android.app.Activity;

import com.xuhuawei.annotation.config.AnnotationConfig;


public class HuaweiKnife {

    public static void inject(Activity activity){
        Class<?> clazz = activity.getClass();
        String proxyClassFullName = clazz.getName()+ AnnotationConfig.SUFFIX;
        //找到我们的新生成的内部辅助类，我们的辅助类也必须实现这个接口ViewInjectorInterface
        Class<?> proxyClazz = null;
        try {
            proxyClazz = Class.forName(proxyClassFullName);
            ViewInjectorInterface viewInjector = (ViewInjectorInterface) proxyClazz.newInstance();
            viewInjector.inject(activity);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
