package com.xuhuawei.compiler.processer;

import com.google.auto.service.AutoService;
import com.xuhuawei.compiler.handler.AnnotationHandler;
import com.xuhuawei.compiler.writer.AptAbstractWriter;
import com.xuhuawei.compiler.writer.DefaultWriterApt;
import com.xuhuawei.annotation.ViewInjector;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

/**
 * 我们注解的处理器
 */
@AutoService({Processor.class})
public class ViewInjectProcessor extends AbstractProcessor {

    private Filer mFileUtils;
    private Elements mElementUtils;
    private Messager mMessager;

    private AnnotationHandler handler;
    private AptAbstractWriter mWriter;
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types=new HashSet<>();
        types.add(ViewInjector.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_6;
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        handler=new AnnotationHandler(processingEnvironment);
        mWriter=new DefaultWriterApt(processingEnvironment);

        mFileUtils = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Map<String, List<VariableElement>> result= handler.handleAnnotation(roundEnvironment);

        mWriter.generate(result);
        return true;
    }
}
