package kt.types

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class OptionTest {
    val s = Some(2)
    val n = None<Int>()

    @Test
    fun testNew() {
        assertEquals(s, Some(2))
        assertEquals(n, None<Int>())
    }

    @Test
    fun testIsSome() {
        assertTrue(s.isSome())
        assertFalse(n.isSome())
    }

    @Test
    fun testIsNone() {
        assertFalse(s.isNone())
        assertTrue(n.isNone())
    }

    @Test
    fun testFrom() {
        assertEquals(Option.from(2), s)

        var x: Int? = 2
        assertEquals(Option.fromNullable(x), s)
        x = null
        assertEquals(Option.fromNullable(x), n)

        var f: () -> Int = { 2 }
        assertEquals(Option.fromThrowable(f), s)
        f = { throw Exception("bad") }
        assertEquals(Option.fromThrowable(f), n)
    }
}
