package kt.types

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

class OptionSome<T : Any>(val value: T, val valueType: KType, clazz: KClass<T>) : Option<T>(clazz)

class OptionNone<T : Any>(val valueType: KType, clazz: KClass<T>) : Option<T>(clazz)

sealed class Option<T : Any>(val clazz: KClass<T>) {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Option<*>) return false
        if (this is OptionNone<*> && other is OptionNone<*>) return valueType == other.valueType
        if (this is OptionSome<*> && other is OptionSome<*>) return valueType == other.valueType && value == other.value
        return false
    }

    fun isSome(): Boolean = this is OptionSome<*>

    fun isNone(): Boolean = this is OptionNone<*>

    inline fun <reified U : Any> map(f: (T) -> U): Option<U> {
        if (this is OptionSome<T>) {
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

inline fun <reified T : Any> Some(value: T): Option<T> = OptionSome<T>(value, typeOf<T>(), T::class)

inline fun <reified T : Any> None(): Option<T> = OptionNone<T>(typeOf<T>(), T::class)
