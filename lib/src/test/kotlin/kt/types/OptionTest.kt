package kt.types

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class OptionTest {
    @Test
    fun testNew() {
        val s = Some(2)
        val n = None<Int>()

        assertEquals(s, Some(2))
        assertEquals(n, None<Int>())
    }

    @Test
    fun testIsSome() {
        val s = Some(2)
        val n = None<Int>()

        assertTrue(s.isSome())
        assertFalse(n.isSome())
    }

    @Test
    fun testIsNone() {
        val s = Some(2)
        val n = None<Int>()

        assertFalse(s.isNone())
        assertTrue(n.isNone())
    }
}
