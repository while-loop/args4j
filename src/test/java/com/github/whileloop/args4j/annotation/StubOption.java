package com.github.whileloop.args4j.annotation;

import java.lang.annotation.Annotation;

public class StubOption implements Option {
    @Override
    public boolean required() {
        return false;
    }

    @Override
    public String shortOpt() {
        return null;
    }

    @Override
    public String longOpt() {
        return null;
    }

    @Override
    public String desc() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
