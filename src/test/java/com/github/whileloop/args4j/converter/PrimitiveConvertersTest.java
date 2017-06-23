package com.github.whileloop.args4j.converter;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrimitiveConvertersTest {
    @Test
    public void testStringConverter() {
        assertArrayEquals(new Class[]{String.class}, PrimitiveConverters.STRING_CONVERTER.getConvertClass());
        assertEquals("testing", PrimitiveConverters.STRING_CONVERTER.convert(null,null, "testing"));
        assertEquals("", PrimitiveConverters.STRING_CONVERTER.convert(null,null, ""));
        assertEquals(null, PrimitiveConverters.STRING_CONVERTER.convert(null,null, null));
    }

    @Test
    public void testIntegerConverter() {
        assertArrayEquals(new Class[]{Integer.class, int.class}, PrimitiveConverters.INTEGER_CONVERTER.getConvertClass());

        assertEquals(3, (int) PrimitiveConverters.INTEGER_CONVERTER.convert(null,null, "3"));
        assertEquals(new Integer(-1), PrimitiveConverters.INTEGER_CONVERTER.convert(null,null, "-1"));

        try {
            PrimitiveConverters.INTEGER_CONVERTER.convert(null,null, null);
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("null", e.getMessage());
        }

        try {
            PrimitiveConverters.INTEGER_CONVERTER.convert(null,null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"\"", e.getMessage());
        }
    }

    @Test
    public void testDoubleConverter() {
        assertArrayEquals(new Class[]{double.class, Double.class}, PrimitiveConverters.DOUBLE_CONVERTER.getConvertClass());

        assertEquals(3.2, PrimitiveConverters.DOUBLE_CONVERTER.convert(null,null, "3.2"), 0.01);
        assertEquals(new Double(-1.0), PrimitiveConverters.DOUBLE_CONVERTER.convert(null,null, "-1"));

        try {
            PrimitiveConverters.DOUBLE_CONVERTER.convert(null,null, null);
            fail("NullPointerException not thrown");
        } catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }

        try {
            PrimitiveConverters.DOUBLE_CONVERTER.convert(null,null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("empty String", e.getMessage());
        }
    }

    @Test
    public void testFloatConverter() {
        assertArrayEquals(new Class[]{float.class, Float.class}, PrimitiveConverters.FLOAT_CONVERTER.getConvertClass());

        assertEquals(3.2, PrimitiveConverters.FLOAT_CONVERTER.convert(null,null, "3.2"), 0.01);
        assertEquals(new Float(-1.0), PrimitiveConverters.FLOAT_CONVERTER.convert(null,null, "-1"));

        try {
            PrimitiveConverters.FLOAT_CONVERTER.convert(null,null, null);
            fail("NullPointerException not thrown");
        } catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }

        try {
            PrimitiveConverters.FLOAT_CONVERTER.convert(null,null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("empty String", e.getMessage());
        }
    }

    @Test
    public void testShortConverter() {
        assertArrayEquals(new Class[]{Short.class, short.class}, PrimitiveConverters.SHORT_CONVERTER.getConvertClass());

        assertEquals(32767, PrimitiveConverters.SHORT_CONVERTER.convert(null,null, "32767"), 0.01);
        assertEquals(new Short((short) -654), PrimitiveConverters.SHORT_CONVERTER.convert(null,null, "-654"));

        try {
            PrimitiveConverters.SHORT_CONVERTER.convert(null,null, null);
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("null", e.getMessage());
        }

        try {
            PrimitiveConverters.SHORT_CONVERTER.convert(null,null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"\"", e.getMessage());
        }
    }

    @Test
    public void testBooleanConverter() {
        assertArrayEquals(new Class[]{boolean.class, Boolean.class}, PrimitiveConverters.BOOLEAN_CONVERTER.getConvertClass());

        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null,null, "false"));
        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null,null, ""));
        assertEquals(new Boolean(false), PrimitiveConverters.BOOLEAN_CONVERTER.convert(null,null, null));

        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null,null, null));

        assertTrue(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null,null, "true"));
        assertTrue(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null,null, "1"));
    }
}