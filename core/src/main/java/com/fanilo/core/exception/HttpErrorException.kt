package com.fanilo.core.exception

class HttpErrorException(val httpCode: Int, message: String?) : Exception(message)