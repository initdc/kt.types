package kt.types

import kotlin.reflect.KType
import kotlin.reflect.typeOf

data class OptionSome<T : Any>(val value: T, val valueType: KType) : Option<T>()

data class OptionNone<T : Any>(val valueType: KType) : Option<T>()

sealed class Option<T> {
    companion object {
        inline fun <reified T : Any> from(value: T): Option<T> = Some<T>(value)

        inline fun <reified T : Any> fromNullable(value: T?): Option<T> {
            if (value == null) {
                return None<T>()
            }
            return Some<T>(value)
        }

        inline fun <reified T : Any> fromThrowable(f: () -> T): Option<T> {
            try {
                return Some<T>(f())
            } catch (e: Exception) {
                return None<T>()
            }
        }
    }

    fun isSome(): Boolean = this is OptionSome<*>

    fun isNone(): Boolean = this is OptionNone<*>

    inline fun <reified U : Any> map(f: (T) -> U): Option<U> {
        if (this is OptionSome<T>) {
            return Some<U>(f(this.value))
        }
        return None<U>()
    }
}

inline fun <reified T : Any> Some(value: T): Option<T> = OptionSome<T>(value, typeOf<T>())

inline fun <reified T : Any> None(): Option<T> = OptionNone<T>(typeOf<T>())
