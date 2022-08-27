package com.kirkbushman.redgifs.managers

import com.kirkbushman.redgifs.auth.Token

interface StorageManager {

    fun isAuthed(): Boolean

    fun hasToken(): Boolean
    fun getToken(): Token?

    fun saveToken(token: Token)

    fun clearAll()
}
