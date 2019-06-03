package com.abecerra.calculator.core.utils

import android.content.SharedPreferences
import com.abecerra.calculator.core.App

class AppSharedPreferences {

    private val mPrefs: SharedPreferences = App.getAppContext().getSharedPreferences(MY_PREFS, 0)

    companion object {

        const val MY_PREFS: String = "MY_PREFS"
        const val USER_TOKEN: String = "user_token"
        const val PROFILE_IMAGE: String = "profile_image"
        const val LOGIN_SUGGESTION: String = "login_suggestion"

        private var INSTANCE: AppSharedPreferences? = null

        fun getInstance(): AppSharedPreferences {
            if (INSTANCE == null) INSTANCE = AppSharedPreferences()
            return INSTANCE!!
        }
    }

    fun getUserToken(): String = mPrefs.getString(USER_TOKEN, "") ?: ""
    fun setUserToken(token: String) {
        mPrefs.edit()
            .putString(USER_TOKEN, token)
            .apply()
    }

    fun removeUserToken() {
        setUserToken("")
    }

    fun isLoginSuggestionRequired(): Boolean {
        if (!isLoginRequired()) {
            return false
        }

        val shownTimes = mPrefs.getInt(LOGIN_SUGGESTION, 0)
        mPrefs.edit()
            .putInt(LOGIN_SUGGESTION, shownTimes + 1)
            .apply()
        return isLoginRequired() && shownTimes < 10
    }

    fun isLoginRequired() = getUserToken().isBlank()

    fun getProfileImage(): String? = mPrefs.getString(PROFILE_IMAGE, "")
    fun setProfileImage(image: String) {
        mPrefs.edit()
            .putString(PROFILE_IMAGE, image)
            .apply()
    }
}