package com.xuhuawei.compiler.writer;

import java.util.List;
import java.util.Map;

import javax.lang.model.element.VariableElement;

public interface AdapterWriter {
    void generate(Map<String, List<VariableElement>> map);
}
