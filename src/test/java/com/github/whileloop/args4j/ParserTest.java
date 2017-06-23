package com.github.whileloop.args4j;

import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Type;


public class ParserTest {
    @Test
    public void parse() throws Exception {
        int[] n = new int[]{1,2,3,};
        Class c = n.getClass();
        System.out.println(c.getComponentType());
        System.out.println(c.getDeclaringClass());
        System.out.println(c.getEnclosingClass());
        System.out.println(TypeToken.of(int.class).getRawType());
        System.out.println(TypeToken.of(Integer.class).getRawType());
    }

    void s(Object[] v) {
        System.out.println(v.getClass().getComponentType());
    }

    @Test
    public void parseArgs() throws Exception {
    }

}