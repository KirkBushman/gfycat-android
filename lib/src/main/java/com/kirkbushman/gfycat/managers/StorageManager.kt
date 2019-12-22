package com.kirkbushman.gfycat.managers

import com.kirkbushman.gfycat.auth.Token

interface StorageManager {

    fun isAuthed(): Boolean

    fun hasToken(): Boolean
    fun getToken(): Token?

    fun saveToken(token: Token)

    fun clearAll()
}
