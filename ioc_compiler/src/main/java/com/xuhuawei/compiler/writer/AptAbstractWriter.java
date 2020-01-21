package com.xuhuawei.compiler.writer;

import com.xuhuawei.compiler.bean.InjectInfo;
import com.xuhuawei.compiler.utils.AnnotationUtils;
import com.xuhuawei.compiler.utils.IOUtil;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.JavaFileObject;

public abstract class AptAbstractWriter implements AdapterWriter {

    protected ProcessingEnvironment mProcessingEnv;
    protected Filer mFiler;

    public AptAbstractWriter(ProcessingEnvironment mProcessingEnv) {
        this.mProcessingEnv = mProcessingEnv;
    }

    public void generate(Map<String, List<VariableElement>> map) {
        Iterator<Map.Entry<String, List<VariableElement>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<VariableElement>> entry = iterator.next();
            List<VariableElement> cacheElements = entry.getValue();
            if (cacheElements == null || cacheElements.size() == 0) {
                continue;
            }

            InjectInfo info = createInjectInfo(cacheElements.get(0));
            Writer writer = null;
            try {
                JavaFileObject javaFileObject = mFiler.createSourceFile(info.getClassFullName());
                writer = javaFileObject.openWriter();
                generateImport(writer, info);
                for (VariableElement variableElement : cacheElements) {
                    writeField(writer, variableElement, info);
                }
//                FileUtils.output("writeField");
                writeEnd(writer);
//                FileUtils.output("writeEnd");
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                IOUtil.closeQuietly(writer);
        }
    }
    }

    private InjectInfo createInjectInfo(VariableElement element) {
        TypeElement typeElement = (TypeElement) element.getEnclosingElement();
        String packageName = AnnotationUtils.getPackageName(mProcessingEnv, element);
        String className = typeElement.getSimpleName().toString();
        return new InjectInfo(packageName, className);
    }


    protected abstract void generateImport(Writer writer, InjectInfo info) throws IOException;

    protected abstract void writeField(Writer writer, VariableElement element, InjectInfo info) throws IOException;

    protected abstract void writeEnd(Writer writer) throws IOException;
}
