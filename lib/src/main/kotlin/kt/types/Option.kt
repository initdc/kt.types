package kt.types

sealed class Option<out T> {
    data class Some<T>(private val value: T) : Option<T>()

    object None : Option<Nothing>()

    companion object {
        fun <T> from(value: T): Option<T> = Option.Some(value)
        fun <T> fromNullable(value: T?): Option<T> {
            if (value == null) {
                return Option.None
            }
            return Option.Some(value)
        }

        fun <T> fromThrowable(f: () -> T): Option<T> {
            try {
                return Option.Some(f())
            } catch (e: Exception) {
                return Option.None
            }
        }
    }

    fun isSome(): Boolean = this is Option.Some

    fun isNone(): Boolean = this is Option.None
}

fun <T> Some(value: T): Option<T> = Option.Some(value)

fun <T> None(): Option<T> = Option.None
