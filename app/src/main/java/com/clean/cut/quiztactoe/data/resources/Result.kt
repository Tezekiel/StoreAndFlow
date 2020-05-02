package com.clean.cut.quiztactoe.data.resources

sealed class Result<out T> {

  data class Success<out T>(val data: T) : Result<T>()

  data class Error(val error: Throwable) : Result<Nothing>()

  /**
   * If this [Result] is of type [Result.Success], returns the available data
   * from it. Otherwise, returns `null`.
   */
  fun get(): T? {
    return when (this) {
      is Success -> data
      is Error -> null
    }
  }

  /**
   * If this [Result] is of type [Result.Success], returns the available data
   * from it. Otherwise throw `error`.
   */
  fun getOrThrow(): T {
    return when (this) {
      is Success -> data
      is Error -> throw error
    }
  }

  /**
   * If this [Result] is of type [Result.Error], returns the available error
   * from it. Otherwise, returns `null`.
   */
  fun errorOrNull(): Throwable? {
    return (this as? Error)?.error
  }

  /**
   * If this [Result] is of type [Result.Error], throws the exception
   * Otherwise, does nothing.
   */
  fun throwIfError() {
    if (this is Error) {
      throw error
    }
  }

  /**
   * Maps [Result] of type T to result of type R by applying
   * [mapper] function.
   */
  fun <R> map(mapper: (T) -> R): Result<R> {
    return when (this) {
      is Success -> Success(mapper(data))
      is Error -> Error(error)
    }
  }

  /** Return `true` if this result is [Result.Success], `false` otherwise */
  fun isSuccess() = this is Success

  /** Return `true` if this result is [Result.Error], `false` otherwise */
  fun isError() = this is Error

  override fun toString(): String {
    return when (this) {
      is Success<*> -> "Success[data=$data]"
      is Error -> "Error[error=$error]"
    }
  }

  companion object {
    /** Creates empty success result - [Result.Success] with type [Unit] */
    fun success(): Result<Unit> = Success(Unit)
  }
}

/**
 * Calls the specified function block and returns its encapsulated result if invocation was
 * successful, catching and encapsulating any thrown exception as a failure.
 * This is similar to Kotlin `runCatching(block)` but it uses domain [Result].
 */
inline fun <T> resultFrom(block: () -> T): Result<T> {
  return try {
    val data = block()
    Result.Success(data)
  } catch (t: Throwable) {
    Result.Error(t)
  }
}