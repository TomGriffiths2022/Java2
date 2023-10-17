package org.example.arrayutils;

public class ArrayUtilsTest {
    @Test
    public void testToList() {
        var list = ArrayUtils.toList(new String[] {"a", "b", "c"});
        assertTrue(list.contains("a"));
        assertTrue(list.contains("b"));
        assertTrue(list.contains("c"));
        assertEquals(3, list.size());
    }
}
