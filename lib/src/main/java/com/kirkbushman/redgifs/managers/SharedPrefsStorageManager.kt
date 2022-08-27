package com.kirkbushman.redgifs.managers

import android.content.Context
import androidx.core.content.edit
import com.kirkbushman.redgifs.auth.Token

class SharedPrefsStorageManager(context: Context) : StorageManager {

    companion object {

        internal const val INTERNAL_SHARED_PREFS = "internal_shared_prefs_default"

        private const val IS_AUTHED = "android_redgifs_oauth2_is_authed_first_time"

        private const val LAST_TOKEN_TYPE = "android_redgifs_oauth2_current_token_type"
        private const val LAST_SCOPE = "android_redgifs_oauth2_current_scope"
        private const val LAST_RESOURCE_OWNER = "android_redgifs_oauth2_current_owner"
        private const val LAST_REFRESH_TOKEN = "android_redgifs_oauth2_current_refresh_token"
        private const val LAST_REFRESH_EXPIRES_IN = "android_redgifs_oauth2_current_refresh_expires_in"
        private const val LAST_ACCESS_TOKEN = "android_redgifs_oauth2_current_access_token"
        private const val LAST_EXPIRES_IN = "android_redgifs_oauth2_current_expires_in"
        private const val LAST_CREATED_TIME = "android_redgifs_oauth2_current_created_time"
    }

    private val prefs by lazy { context.getSharedPreferences(INTERNAL_SHARED_PREFS, Context.MODE_PRIVATE) }

    override fun isAuthed(): Boolean {
        return prefs.getBoolean(IS_AUTHED, false)
    }

    override fun hasToken(): Boolean {
        return prefs.contains(LAST_ACCESS_TOKEN) &&
            prefs.contains(LAST_RESOURCE_OWNER) &&
            prefs.contains(LAST_REFRESH_TOKEN) &&
            prefs.contains(LAST_REFRESH_EXPIRES_IN) &&
            prefs.contains(LAST_TOKEN_TYPE) &&
            prefs.contains(LAST_EXPIRES_IN) &&
            prefs.contains(LAST_SCOPE) &&
            prefs.contains(LAST_CREATED_TIME)
    }

    override fun getToken(): Token {
        if (
            !prefs.contains(LAST_ACCESS_TOKEN) ||
            !prefs.contains(LAST_RESOURCE_OWNER) ||
            !prefs.contains(LAST_REFRESH_TOKEN) ||
            !prefs.contains(LAST_REFRESH_EXPIRES_IN) ||
            !prefs.contains(LAST_TOKEN_TYPE) ||
            !prefs.contains(LAST_EXPIRES_IN) ||
            !prefs.contains(LAST_SCOPE) ||
            !prefs.contains(LAST_CREATED_TIME)
        ) {

            throw IllegalStateException("Token not found in store! did you ever saved one?")
        }

        val accessToken = prefs.getString(LAST_ACCESS_TOKEN, "") as String
        val tokenType = prefs.getString(LAST_TOKEN_TYPE, "") as String
        val expiresIn = prefs.getInt(LAST_EXPIRES_IN, 0)
        val resourceOwner = prefs.getString(LAST_RESOURCE_OWNER, null)
        val refreshToken = prefs.getString(LAST_REFRESH_TOKEN, null)
        val refreshExpiresIn = prefs.getInt(LAST_REFRESH_EXPIRES_IN, -1)
        val scope = prefs.getString(LAST_SCOPE, "") as String
        val createdTime = prefs.getLong(LAST_CREATED_TIME, 0L)

        return Token(
            tokenType = tokenType,
            resourceOwner = resourceOwner,
            refreshToken = refreshToken,
            refreshTokenExpiresIn = if (refreshExpiresIn != -1) refreshExpiresIn else null,
            accessToken = accessToken,
            expiresIn = expiresIn,
            scope = scope,
            createdTime = createdTime
        )
    }

    override fun saveToken(token: Token) {

        prefs.edit {

            if (!prefs.contains(IS_AUTHED) || !prefs.getBoolean(IS_AUTHED, false)) {
                putBoolean(IS_AUTHED, true)
            }

            putString(LAST_TOKEN_TYPE, token.tokenType)
            putString(LAST_RESOURCE_OWNER, token.resourceOwner)
            putString(LAST_REFRESH_TOKEN, token.refreshToken)
            putInt(LAST_REFRESH_EXPIRES_IN, token.refreshTokenExpiresIn ?: -1)
            putString(LAST_ACCESS_TOKEN, token.accessToken)
            putInt(LAST_EXPIRES_IN, token.expiresIn)
            putString(LAST_SCOPE, token.scope)
            putLong(LAST_CREATED_TIME, token.createdTime)
        }
    }

    override fun clearAll() {

        prefs.edit {

            if (prefs.contains(IS_AUTHED))
                this.remove(IS_AUTHED)

            if (prefs.contains(LAST_TOKEN_TYPE))
                this.remove(LAST_TOKEN_TYPE)
            if (prefs.contains(LAST_RESOURCE_OWNER))
                this.remove(LAST_RESOURCE_OWNER)
            if (prefs.contains(LAST_REFRESH_TOKEN))
                this.remove(LAST_REFRESH_TOKEN)
            if (prefs.contains(LAST_REFRESH_EXPIRES_IN))
                this.remove(LAST_REFRESH_EXPIRES_IN)
            if (prefs.contains(LAST_ACCESS_TOKEN))
                this.remove(LAST_ACCESS_TOKEN)
            if (prefs.contains(LAST_EXPIRES_IN))
                this.remove(LAST_EXPIRES_IN)
            if (prefs.contains(LAST_SCOPE))
                this.remove(LAST_SCOPE)
            if (prefs.contains(LAST_CREATED_TIME))
                this.remove(LAST_CREATED_TIME)
        }
    }
}
