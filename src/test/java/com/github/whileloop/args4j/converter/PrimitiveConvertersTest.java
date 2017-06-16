package com.github.whileloop.args4j.converter;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrimitiveConvertersTest {
    @Test
    public void testStringConverter() {
        assertEquals(String.class, PrimitiveConverters.STRING_CONVERTER.getConvertClass());
        assertEquals("testing", PrimitiveConverters.STRING_CONVERTER.convert("testing"));
        assertEquals("", PrimitiveConverters.STRING_CONVERTER.convert(""));
        assertEquals(null, PrimitiveConverters.STRING_CONVERTER.convert(null));
    }

    @Test
    public void testIntegerConverter() {
        assertEquals(Integer.class, PrimitiveConverters.INTEGER_CONVERTER.getConvertClass());

        assertEquals(3, (int) PrimitiveConverters.INTEGER_CONVERTER.convert("3"));
        assertEquals(new Integer(-1), PrimitiveConverters.INTEGER_CONVERTER.convert("-1"));

        try {
            PrimitiveConverters.INTEGER_CONVERTER.convert(null);
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("null", e.getMessage());
        }

        try {
            PrimitiveConverters.INTEGER_CONVERTER.convert("");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"\"", e.getMessage());
        }
    }

    @Test
    public void testDoubleConverter() {
        assertEquals(Double.class, PrimitiveConverters.DOUBLE_CONVERTER.getConvertClass());

        assertEquals(3.2, PrimitiveConverters.DOUBLE_CONVERTER.convert("3.2"), 0.01);
        assertEquals(new Double(-1.0), PrimitiveConverters.DOUBLE_CONVERTER.convert("-1"));

        try {
            PrimitiveConverters.DOUBLE_CONVERTER.convert(null);
            fail("NullPointerException not thrown");
        } catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }

        try {
            PrimitiveConverters.DOUBLE_CONVERTER.convert("");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("empty String", e.getMessage());
        }
    }

    @Test
    public void testFloatConverter() {
        assertEquals(Float.class, PrimitiveConverters.FLOAT_CONVERTER.getConvertClass());

        assertEquals(3.2, PrimitiveConverters.FLOAT_CONVERTER.convert("3.2"), 0.01);
        assertEquals(new Float(-1.0), PrimitiveConverters.FLOAT_CONVERTER.convert("-1"));

        try {
            PrimitiveConverters.FLOAT_CONVERTER.convert(null);
            fail("NullPointerException not thrown");
        } catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }

        try {
            PrimitiveConverters.FLOAT_CONVERTER.convert("");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("empty String", e.getMessage());
        }
    }

    @Test
    public void testShortConverter() {
        assertEquals(Short.class, PrimitiveConverters.SHORT_CONVERTER.getConvertClass());

        assertEquals(32767, PrimitiveConverters.SHORT_CONVERTER.convert("32767"), 0.01);
        assertEquals(new Short((short) -654), PrimitiveConverters.SHORT_CONVERTER.convert("-654"));

        try {
            PrimitiveConverters.SHORT_CONVERTER.convert(null);
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("null", e.getMessage());
        }

        try {
            PrimitiveConverters.SHORT_CONVERTER.convert("");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"\"", e.getMessage());
        }
    }

    @Test
    public void testBooleanConverter() {
        assertEquals(Boolean.class, PrimitiveConverters.BOOLEAN_CONVERTER.getConvertClass());

        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert("false"));
        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert(""));
        assertEquals(new Boolean(false), PrimitiveConverters.BOOLEAN_CONVERTER.convert(null));

        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null));

        assertTrue(PrimitiveConverters.BOOLEAN_CONVERTER.convert("true"));
        assertTrue(PrimitiveConverters.BOOLEAN_CONVERTER.convert("1"));
    }
}