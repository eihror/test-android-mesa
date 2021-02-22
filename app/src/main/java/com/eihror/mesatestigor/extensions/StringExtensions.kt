package com.eihror.mesatestigor.extensions

import android.text.TextUtils
import java.util.regex.Pattern

fun String.isValidPassword(): Boolean {
    val PASSWORD_REGEX = Pattern.compile(
        "(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%&*()_+=|<>?{}\\[\\]~-]).{8,24}"
    )
    return !TextUtils.isEmpty(this) && this.length >= 8
}

fun String.isValidEmail(): Boolean {
    val REGEX_EMAIL = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    return !TextUtils.isEmpty(this.trim()) && REGEX_EMAIL.matcher(this).matches()
}
