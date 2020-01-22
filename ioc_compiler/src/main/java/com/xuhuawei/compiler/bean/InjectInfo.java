package com.xuhuawei.compiler.bean;


import com.xuhuawei.annotation.config.AnnotationConfig;

public class InjectInfo {
    //宿主类的包名
    public String packageName;
    //宿主类的类名
    public String className;
    //要创建的辅助类的类名
    public String newClassName;

    public InjectInfo(String packageName, String className) {
        super();
        this.packageName = packageName;
        this.className = className;
        this.newClassName = className + AnnotationConfig.SUFFIX;
    }

    /**
     * 获取辅助类的完整类名
     * @return
     */
    public String getClassFullName(){
        return this.packageName + "." + this.newClassName;
    }

}
