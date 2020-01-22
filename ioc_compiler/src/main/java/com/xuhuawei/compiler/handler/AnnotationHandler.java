package com.xuhuawei.compiler.handler;


import com.xuhuawei.annotation.ViewInjector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.VariableElement;

public class AnnotationHandler extends BaseHandler {

    public AnnotationHandler(ProcessingEnvironment processingEnv) {
        super(processingEnv);
    }
    @Override
    public Map<String, List<VariableElement>> handleAnnotation(RoundEnvironment env) {
        Map<String, List<VariableElement>> annotationMap = new HashMap<String, List<VariableElement>>();
        Set<? extends Element> elementSet = env.getElementsAnnotatedWith(ViewInjector.class);
        for (Element element : elementSet) {
            //我们的注解是在变量上面的
            VariableElement varElement = (VariableElement)element;
            //获取宿主的全路径名称
            String className = getParentClassName(varElement);
            //key->value
            List<VariableElement> cacheElements = annotationMap.get(className);
            if(cacheElements == null){
                cacheElements = new ArrayList<VariableElement>();
                annotationMap.put(className, cacheElements);
            }
            cacheElements.add(varElement);
        }
        return annotationMap;
    }

}
