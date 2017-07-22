package com.github.whileloop.args4j.converter;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrimitiveConvertersTest {

    @BeforeClass
    public static void init() {
        new PrimitiveConverters();
    }

    @Test
    public void testStringConverter() {
        assertArrayEquals(new Class[]{String.class}, PrimitiveConverters.STRING_CONVERTER.getType());
        assertEquals("testing", PrimitiveConverters.STRING_CONVERTER.convert(null, null, "testing"));
        assertEquals("", PrimitiveConverters.STRING_CONVERTER.convert(null, null, ""));
        assertEquals(null, PrimitiveConverters.STRING_CONVERTER.convert(null, null, null));
    }

    @Test
    public void testIntegerConverter() {
        assertArrayEquals(new Class[]{Integer.class, int.class}, PrimitiveConverters.INTEGER_CONVERTER.getType());

        assertEquals(3, (int) PrimitiveConverters.INTEGER_CONVERTER.convert(null, null, "3"));
        assertEquals(new Integer(-1), PrimitiveConverters.INTEGER_CONVERTER.convert(null, null, "-1"));

        try {
            PrimitiveConverters.INTEGER_CONVERTER.convert(null, null, null);
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("null", e.getMessage());
        }

        try {
            PrimitiveConverters.INTEGER_CONVERTER.convert(null, null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"\"", e.getMessage());
        }
    }

    @Test
    public void testLongConverter() {
        assertArrayEquals(new Class[]{Long.class, long.class}, PrimitiveConverters.LONG_CONVERTER.getType());

        assertEquals(3, (long) PrimitiveConverters.LONG_CONVERTER.convert(null, null, "3"));
        assertEquals(1000000000, (long) PrimitiveConverters.LONG_CONVERTER.convert(null, null, "1000000000"));
        assertEquals(new Long(-1), PrimitiveConverters.LONG_CONVERTER.convert(null, null, "-1"));
        assertEquals(new Long(-1000000000), PrimitiveConverters.LONG_CONVERTER.convert(null, null, "-1000000000"));
        try {
            PrimitiveConverters.LONG_CONVERTER.convert(null, null, null);
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("null", e.getMessage());
        }

        try {
            PrimitiveConverters.LONG_CONVERTER.convert(null, null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"\"", e.getMessage());
        }
    }

    @Test
    public void testDoubleConverter() {
        assertArrayEquals(new Class[]{double.class, Double.class}, PrimitiveConverters.DOUBLE_CONVERTER.getType());

        assertEquals(3.2, PrimitiveConverters.DOUBLE_CONVERTER.convert(null, null, "3.2"), 0.01);
        assertEquals(new Double(-1.0), PrimitiveConverters.DOUBLE_CONVERTER.convert(null, null, "-1"));

        try {
            PrimitiveConverters.DOUBLE_CONVERTER.convert(null, null, null);
            fail("NullPointerException not thrown");
        } catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }

        try {
            PrimitiveConverters.DOUBLE_CONVERTER.convert(null, null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("empty String", e.getMessage());
        }
    }

    @Test
    public void testFloatConverter() {
        assertArrayEquals(new Class[]{float.class, Float.class}, PrimitiveConverters.FLOAT_CONVERTER.getType());

        assertEquals(3.2, PrimitiveConverters.FLOAT_CONVERTER.convert(null, null, "3.2"), 0.01);
        assertEquals(new Float(-1.0), PrimitiveConverters.FLOAT_CONVERTER.convert(null, null, "-1"));

        try {
            PrimitiveConverters.FLOAT_CONVERTER.convert(null, null, null);
            fail("NullPointerException not thrown");
        } catch (NullPointerException e) {
            assertEquals(null, e.getMessage());
        }

        try {
            PrimitiveConverters.FLOAT_CONVERTER.convert(null, null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("empty String", e.getMessage());
        }
    }

    @Test
    public void testShortConverter() {
        assertArrayEquals(new Class[]{Short.class, short.class}, PrimitiveConverters.SHORT_CONVERTER.getType());

        assertEquals(32767, PrimitiveConverters.SHORT_CONVERTER.convert(null, null, "32767"), 0.01);
        assertEquals(new Short((short) -654), PrimitiveConverters.SHORT_CONVERTER.convert(null, null, "-654"));

        try {
            PrimitiveConverters.SHORT_CONVERTER.convert(null, null, null);
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("null", e.getMessage());
        }

        try {
            PrimitiveConverters.SHORT_CONVERTER.convert(null, null, "");
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("For input string: \"\"", e.getMessage());
        }
    }

    @Test
    public void testBooleanConverter() {
        assertArrayEquals(new Class[]{boolean.class, Boolean.class}, PrimitiveConverters.BOOLEAN_CONVERTER.getType());

        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null, null, "false"));
        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null, null, ""));
        assertEquals(new Boolean(false), PrimitiveConverters.BOOLEAN_CONVERTER.convert(null, null, null));

        assertFalse(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null, null, null));

        assertTrue(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null, null, "true"));
        assertTrue(PrimitiveConverters.BOOLEAN_CONVERTER.convert(null, null, "1"));
    }

    @Test
    public void testCharacterConverter() {
        assertArrayEquals(new Class[]{Character.class, char.class}, PrimitiveConverters.CHARACTER_CONVERTER.getType());

        assertEquals('a', (char) PrimitiveConverters.CHARACTER_CONVERTER.convert(null, null, "a"));
        assertEquals(new Character('b'), PrimitiveConverters.CHARACTER_CONVERTER.convert(null, null, "b"));
        try {
            PrimitiveConverters.CHARACTER_CONVERTER.convert(null, null, null);
            fail("IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("value given is not length 1", e.getMessage());
        }
        try {
            PrimitiveConverters.CHARACTER_CONVERTER.convert(null, null, "sdfsdf");
            fail("IllegalArgumentException not thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("value given is not length 1", e.getMessage());
        }
    }

    @Test
    public void testByteConverter() {
        assertArrayEquals(new Class[]{byte.class, Byte.class}, PrimitiveConverters.BYTE_CONVERTER.getType());
        assertEquals(9, (byte) PrimitiveConverters.BYTE_CONVERTER.convert(null, null, "9"));
        assertEquals(new Byte("58"), PrimitiveConverters.BYTE_CONVERTER.convert(null, null, "58"));
        assertEquals(new Byte((byte) 0), PrimitiveConverters.BYTE_CONVERTER.convert(null, null, "0"));
        try {
            PrimitiveConverters.BYTE_CONVERTER.convert(null, null, null);
            fail("NumberFormatException not thrown");
        } catch (NumberFormatException e) {
            assertEquals("null", e.getMessage());
        }
    }
}