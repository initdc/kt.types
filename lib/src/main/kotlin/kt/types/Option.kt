package kt.types

import kotlin.reflect.KType
import kotlin.reflect.typeOf

sealed class Option {
    data class Some<T : Any>(val value: T, val valueType: KType) : Option()

    data class None<T : Any>(val valueType: KType) : Option()

    companion object {
        inline fun <reified T : Any> from(value: T): Option = Option.Some<T>(value, typeOf<T>())

        inline fun <reified T : Any> fromNullable(value: T?): Option {
            if (value == null) {
                return Option.None<T>(typeOf<T>())
            }
            return Option.Some<T>(value, typeOf<T>())
        }

        inline fun <reified T : Any> fromThrowable(f: () -> T): Option {
            try {
                return Option.Some<T>(f(), typeOf<T>())
            } catch (e: Exception) {
                return Option.None<T>(typeOf<T>())
            }
        }
    }

    fun isSome(): Boolean = this is Option.Some<*>

    fun isNone(): Boolean = this is Option.None<*>

    inline fun <reified U : Any> map(f: (Any) -> U): Option {
        if (this is Option.Some<*>) {
            return Some<U>(f(this.value))
        }
        return None<U>()
    }

    // inline fun <reified E : Any> okOr(error: E): Result<T, E> {
    //     if (this is Option.Some<*>) {
    //         return Ok<T, E>(this.value)
    //     }
    //     return Err<T, E>(error)
    // }
}

inline fun <reified T : Any> Some(value: T): Option = Option.Some<T>(value, typeOf<T>())

inline fun <reified T : Any> None(): Option = Option.None<T>(typeOf<T>())
