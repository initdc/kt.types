package kt.types

import kotlin.reflect.typeOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class OptionTest {
    val s = some(2)
    val n = none<Int>()

    @Test
    fun testNew() {
        assertEquals(s, some(2))
        assertEquals(n, none<Int>())
        assertEquals(n, OptionNone<Int>(typeOf<Int>()))
        assertEquals(n, OptionNone<Byte>(typeOf<Int>()) as Option<*>)
        assertEquals(n, OptionNone(typeOf<Int>()))

        assertNotEquals(n, OptionNone<Int>(typeOf<Byte>()))
        assertNotEquals(n, OptionNone<Byte>(typeOf<Byte>()) as Option<*>)
        assertNotEquals(n, OptionNone<Float>(typeOf<Float>()) as Option<*>)
        assertNotEquals(n, none<Byte>() as Option<*>)
        assertNotEquals(n, none<Float>() as Option<*>)
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
        assertEquals(Option.fromNullable<Int>(x), n)

        val x2: Int? = null
        assertEquals(Option.fromNullable(x2), n)

        var f: () -> Int = { 2 }
        assertEquals(Option.fromThrowable(f), s)
        f = { throw Exception("bad") }
        assertEquals(Option.fromThrowable<Int>(f), n)

        val f2: () -> Int = { throw Exception("bad") }
        assertEquals(Option.fromThrowable(f2), n)
    }

    @Test
    fun testMap() {
        assertEquals(n.map<String> { x -> x.toString() }, none<String>())
        assertEquals(s.map<String> { x -> x.toString() }, some("2"))

        val f: (Int) -> String = { x -> x.toString() }
        assertEquals(n.map(f), none<String>())
        assertEquals(s.map(f), some("2"))

        assertEquals(n.map { x -> x.toString() }, none<String>())
        assertEquals(s.map { x -> x.toString() }, some("2"))
    }
}
