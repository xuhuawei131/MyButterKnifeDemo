package com.xuhuawei.compiler.handler;

import com.xuhuawei.compiler.utils.AnnotationUtils;

import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public abstract class BaseHandler {

    private ProcessingEnvironment processingEnv;
    public BaseHandler(ProcessingEnvironment processingEnv){
        this.processingEnv=processingEnv;
    }

    protected String getParentClassName(VariableElement varElement) {
        TypeElement typeElement = (TypeElement)varElement.getEnclosingElement();
        String packageName =  AnnotationUtils.getPackageName(processingEnv, typeElement);
        return packageName + "." + typeElement.getSimpleName().toString();
    }
    public abstract Map<String, List<VariableElement>> handleAnnotation(RoundEnvironment roundEnvironment);
}
