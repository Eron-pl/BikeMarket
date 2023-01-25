package com.psablik.bikemarket.infrastructure.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.psablik.bikemarket.infrastructure.local.PreferencesKey.LOGGED_STATUS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = "local_authentication")

private object PreferencesKey {
    val LOGGED_STATUS: Preferences.Key<Boolean> =
        booleanPreferencesKey(name = "logged_status")
}

class LocalAuthenticationDataSource @Inject constructor(
    private val context: Context
) {

    fun getLoggedStatus(): Flow<Boolean?> =
        context.dataStore.data.map { auth ->
            auth[LOGGED_STATUS]
        }

    suspend fun setLoggedStatus(status: Boolean) =
        context.dataStore.edit { auth ->
            auth[LOGGED_STATUS] = status
        }

    suspend fun clearLoggedStatus() =
        context.dataStore.edit { auth ->
            auth.remove(LOGGED_STATUS)
        }

}
