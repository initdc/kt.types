package kt.types

import kotlin.reflect.KType
import kotlin.reflect.typeOf

data class OptionSome<T : Any>(
    val value: T,
    val valueType: KType,
) : Option<T>()

data class OptionNone<T : Any>(
    val valueType: KType,
) : Option<T>()

sealed class Option<T> {
    companion object {
        inline fun <reified T : Any> from(value: T): Option<T> = some<T>(value)

        inline fun <reified T : Any> fromNullable(value: T?): Option<T> {
            if (value == null) {
                return none<T>()
            }
            return some<T>(value)
        }

        inline fun <reified T : Any> fromThrowable(f: () -> T): Option<T> {
            try {
                return some<T>(f())
            } catch (e: Exception) {
                return none<T>()
            }
        }
    }

    fun isSome(): Boolean = this is OptionSome<*>

    fun isNone(): Boolean = this is OptionNone<*>

    inline fun <reified U : Any> map(f: (T) -> U): Option<U> {
        if (this is OptionSome<T>) {
            return some<U>(f(this.value))
        }
        return none<U>()
    }

    // inline fun <reified E : Any> okOr(error: E): Result<T, E> {
    //     if (this is Option.some<*>) {
    //         return Ok<T, E>(this.value)
    //     }
    //     return Err<T, E>(error)
    // }
}

inline fun <reified T : Any> some(value: T): Option<T> = OptionSome<T>(value, typeOf<T>())

inline fun <reified T : Any> none(): Option<T> = OptionNone<T>(typeOf<T>())
