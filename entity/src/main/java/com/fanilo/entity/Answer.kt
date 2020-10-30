package com.fanilo.entity

sealed class Answer<out T : Any> {

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure

    data class Success<T : Any>(val value: T) : Answer<T>()
    data class Failure(val error: Throwable, val message: String, val type: FailureReason?) : Answer<Nothing>()
}

/**
 * Calls the specified function [onSuccess] with `this` value as its receiver if it's a Success and returns the Answer
 *
 * Usage:
 *
 * val answer: Answer<String> = success("Hello World")
 * answer.onSuccess {
 *    println(it)
 * }
 */
inline fun <T : Any, R> Answer<T>.onSuccess(onSuccess: (value: T) -> R): Answer<T> {
    if (this is Answer.Success) {
        onSuccess(this.value)
    }
    return this
}

/**
 * Calls the specified function [onFailure] with `this` value as its receiver if it's a Failure and returns the Answer
 *
 * Usage:
 *
 * val answer: Answer<String> = success("Hello World")
 * answer.onFailure { error, message, type ->
 *    error.printStackTrace()
 * }
 */
inline fun <T : Any, R> Answer<T>.onFailure(onFailure: (error: Throwable, message: String, type: FailureReason?) -> R): Answer<T> {
    if (this is Answer.Failure) {
        onFailure(this.error, this.message, this.type)
    }
    return this
}

enum class FailureReason {
    NETWORK,
    CACHE
}