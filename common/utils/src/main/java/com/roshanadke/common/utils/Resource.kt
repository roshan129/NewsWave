package com.roshanadke.common.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null): Resource<T>(data)
    class Success<T>(data: T? = null): Resource<T>(data)
    class Error<T>(data: T? = null, message: String? = null): Resource<T>(data, message)
}