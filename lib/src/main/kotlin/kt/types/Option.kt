package kt.types

abstract class Option<T> {
    fun isSome(): Boolean = this is Some

    fun isNone(): Boolean = this is None
}

data class Some<T>(
    val value: T,
) : Option<T>()

data class None<T>(
    val value: T? = null,
) : Option<T>()
