package com.kirkbushman.gfycat.managers

import com.kirkbushman.gfycat.auth.Token

class NoOpStorageManager : StorageManager {

    override fun isAuthed(): Boolean {
        return false
    }

    override fun hasToken(): Boolean {
        return false
    }

    override fun getToken(): Token? {
        return null
    }

    override fun saveToken(token: Token) {}
    override fun clearAll() {}
}
