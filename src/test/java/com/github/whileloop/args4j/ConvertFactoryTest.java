package com.github.whileloop.args4j;

import com.github.whileloop.args4j.converter.PrimitiveConverters;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ConvertFactoryTest {

    ConvertFactory _cf;

    @Before
    public void before(){
        _cf = new ConvertFactory();
    }

    @Test
    public void addAll() throws Exception {
        class Person {
            String name;

            public Person(String name) {
                this.name = name;
            }
        }
        _cf.add(new Converter<Person>(){

            @Override
            public Person convert(ConvertFactory factory, Type type, String value) {
                return new Person(value);
            }

            @Override
            public Type[] getType() {
                return new Type[]{Person.class};
            }
        });
        _cf.addAll(PrimitiveConverters.PRIMITIVE_CONVERTERS);

        Person p = _cf.convert(Person.class, "john");
        String n = _cf.convert(String.class, "john");
        int a = _cf.convert(int.class, "12");
        assertEquals("john", p.name);
        assertEquals("john",n );
        assertEquals(12,a );
    }

    @Test
    public void convert() throws Exception {
    }

}