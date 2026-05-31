package com.example.pixo.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.tokenDataStore by preferencesDataStore(name = "token_store")
private val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token")

class TokenStore(private val context: Context) {

    suspend fun saveRefreshToken(token: String) {
        context.tokenDataStore.edit { prefs ->
            prefs[REFRESH_TOKEN_KEY] = token
        }
    }

    val refreshTokenFlow: Flow<String?> = context.tokenDataStore.data.map { prefs ->
        prefs[REFRESH_TOKEN_KEY]
    }

    suspend fun clearRefreshToken() {
        context.tokenDataStore.edit { prefs ->
            prefs.remove(REFRESH_TOKEN_KEY)
        }
    }
}