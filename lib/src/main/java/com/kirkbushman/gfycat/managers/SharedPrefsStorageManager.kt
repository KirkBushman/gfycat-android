package com.kirkbushman.gfycat.managers

import android.content.Context
import androidx.core.content.edit
import com.kirkbushman.gfycat.auth.Token

class SharedPrefsStorageManager(context: Context) : StorageManager {

    companion object {

        internal const val INTERNAL_SHARED_PREFS = "internal_shared_prefs_default"

        private const val IS_AUTHED = "android_gfycat_oauth2_is_authed_first_time"

        private const val LAST_TOKEN_TYPE = "android_gfycat_oauth2_current_token_type"
        private const val LAST_EXPIRES_IN_MINS = "android_gfycat_oauth2_current_expires_in_mins"
        private const val LAST_SCOPE = "android_gfycat_oauth2_current_scope"
        private const val LAST_ACCESS_TOKEN = "android_gfycat_oauth2_current_access_token"
        private const val LAST_CREATED_TIME = "android_gfycat_oauth2_current_created_time"
    }

    private val prefs by lazy { context.getSharedPreferences(INTERNAL_SHARED_PREFS, Context.MODE_PRIVATE) }

    override fun isAuthed(): Boolean {
        return prefs.getBoolean(IS_AUTHED, false)
    }

    override fun hasToken(): Boolean {
        return prefs.contains(LAST_ACCESS_TOKEN) &&
                prefs.contains(LAST_TOKEN_TYPE) &&
                prefs.contains(LAST_EXPIRES_IN_MINS) &&
                prefs.contains(LAST_SCOPE) &&
                prefs.contains(LAST_CREATED_TIME)
    }

    override fun getToken(): Token {
        if (!prefs.contains(LAST_ACCESS_TOKEN) ||
            !prefs.contains(LAST_TOKEN_TYPE) ||
            !prefs.contains(LAST_EXPIRES_IN_MINS) ||
            !prefs.contains(LAST_SCOPE) ||
            !prefs.contains(LAST_CREATED_TIME)) {

            throw IllegalStateException("Token not found in store! did you ever saved one?")
        }

        val accessToken = prefs.getString(LAST_ACCESS_TOKEN, "") as String
        val tokenType = prefs.getString(LAST_TOKEN_TYPE, "") as String
        val expiresInMins = prefs.getInt(LAST_EXPIRES_IN_MINS, 0)
        val scope = prefs.getString(LAST_SCOPE, "") as String
        val createdTime = prefs.getLong(LAST_CREATED_TIME, 0L) as Long

        return Token(
            accessToken = accessToken,
            tokenType = tokenType,
            expiresInMins = expiresInMins,
            scope = scope,
            createdTime = createdTime
        )
    }

    override fun saveToken(token: Token) {

        prefs.edit {

            if (!prefs.contains(IS_AUTHED) || !prefs.getBoolean(IS_AUTHED, false)) {
                putBoolean(IS_AUTHED, true)
            }

            putString(LAST_ACCESS_TOKEN, token.accessToken)
            putString(LAST_TOKEN_TYPE, token.tokenType)
            putInt(LAST_EXPIRES_IN_MINS, token.expiresInMins)
            putString(LAST_SCOPE, token.scope)
            putLong(LAST_CREATED_TIME, token.createdTime)
        }
    }

    override fun clearAll() {

        prefs.edit {

            if (prefs.contains(IS_AUTHED))
                this.remove(IS_AUTHED)

            if (prefs.contains(LAST_ACCESS_TOKEN))
                this.remove(LAST_ACCESS_TOKEN)
            if (prefs.contains(LAST_TOKEN_TYPE))
                this.remove(LAST_TOKEN_TYPE)
            if (prefs.contains(LAST_EXPIRES_IN_MINS))
                this.remove(LAST_EXPIRES_IN_MINS)
            if (prefs.contains(LAST_SCOPE))
                this.remove(LAST_SCOPE)
            if (prefs.contains(LAST_CREATED_TIME))
                this.remove(LAST_CREATED_TIME)
        }
    }
}
