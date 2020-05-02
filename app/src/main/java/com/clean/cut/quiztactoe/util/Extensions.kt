package com.clean.cut.quiztactoe.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.lifecycle.MutableLiveData
import com.clean.cut.quiztactoe.data.resources.Result
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun EditText.toText(): String {
    return this.text.toString().trim()
}

fun <T> MutableLiveData<T>.mutate(mutator: T.() -> T) {
    value?.let { value = mutator(it) }
}

fun View.snack(message: String, @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT, anchor: View? = null) {
    Snackbar
        .make(this, message, duration)
        //to place above bottomNavBar add bottomNavBar as anchorview
        .setAnchorView(anchor)
        .show()
}

/**
 * Wrap a suspending API [call] that returns [Result] in try/catch.
 * In case an exception [Result.Error] is returned.
 */
suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        // An exception was thrown when calling the API so we're converting this to an IOException
        Result.Error(IOException(e))
    }
}

fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw HttpException(this)
    return body()!!
}

fun <T> Response<T>.toException() = HttpException(this)

fun <T> Response<T>.toResult(): Result<T> {
    return try {
        if (isSuccessful) {
            Result.Success(bodyOrThrow())
        } else {
            Result.Error(toException())
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}
