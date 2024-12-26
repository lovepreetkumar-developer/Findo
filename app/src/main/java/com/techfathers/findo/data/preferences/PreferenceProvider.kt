package com.techeytech.rich_dad_poor_dad.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.techfathers.findo.util.Constants

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun clear(): Boolean {
        return preferences.edit().clear().commit()
    }

    fun setRateCount(count: Int) {
        preferences.edit().putInt(
            Constants.RATE_COUNT,
            count
        ).apply()
    }

    fun getRateCount(): Int {
        return preferences.getInt(Constants.RATE_COUNT, 0)
    }

    fun setFirstNumberCounter(count: Int) {
        preferences.edit().putInt(
            Constants.FIRST_COUNTER,
            count
        ).apply()
    }

    fun getFirstNumberCounter(): Int {
        return preferences.getInt(Constants.FIRST_COUNTER, 0)
    }


    fun setSecondNumberCounter(count: Int) {
        preferences.edit().putInt(
            Constants.SECOND_COUNTER,
            count
        ).apply()
    }

    fun getSecondNumberCounter(): Int {
        return preferences.getInt(Constants.SECOND_COUNTER, 0)
    }

    fun setUserLoginType(type: String?) {
        preferences.edit().putString(
            Constants.USER_LOGIN_TYPE,
            type
        ).apply()
    }

    fun getUserLoginType(): String? {
        return preferences.getString(Constants.USER_LOGIN_TYPE, "1")
    }

    fun setShowLockScreen(value: Boolean): Boolean {
        return preferences.edit().putBoolean(
            Constants.SHOW_LOCK_SCREEN,
            value
        ).commit()
    }

    fun getShowLockScreen(): Boolean {
        return preferences.getBoolean(Constants.SHOW_LOCK_SCREEN, true)
    }

    fun setTimezone(timezone: String?) {
        preferences.edit().putString(
            Constants.TIMEZONE,
            timezone
        ).apply()
    }

    fun getTimezone(): String? {
        return preferences.getString(Constants.TIMEZONE, "")
    }

    fun setUserPicture(timezone: String?) {
        preferences.edit().putString(
            Constants.TIMEZONE,
            timezone
        ).apply()
    }

    fun getUserPicture(): String? {
        return preferences.getString(Constants.TIMEZONE, "")
    }

    fun setLatitude(latitude: String?) {
        preferences.edit().putString(
            Constants.LATITUDE,
            latitude
        ).apply()
    }

    fun getLatitude(): String? {
        return preferences.getString(Constants.LATITUDE, "")
    }

    fun setLongitude(longitude: String?) {
        preferences.edit().putString(
            Constants.LONGITUDE,
            longitude
        ).apply()
    }

    fun getLongitude(): String? {
        return preferences.getString(Constants.LONGITUDE, "")
    }

    fun setNotification(value: Boolean): Boolean {
        return preferences.edit().putBoolean(
            Constants.NOTIFICATION,
            value
        ).commit()
    }

    fun getNotification(): Boolean {
        return preferences.getBoolean(Constants.NOTIFICATION, false)
    }

    fun setRemember(): Boolean {
        return preferences.edit().putBoolean(
            Constants.REMEMBER,
            true
        ).commit()
    }

    fun getRemember(): Boolean {
        return preferences.getBoolean(Constants.REMEMBER, false)
    }

    fun setFirebaseToken(token: String?): Boolean {
        return preferences.edit().putString(
            Constants.FIREBASE_TOKEN,
            token
        ).commit()
    }

    fun setLanguage(language: String?): Boolean {
        return preferences.edit().putString(
            Constants.LANGUAGE,
            language
        ).commit()
    }

    fun getLanguage(): String? {
        return preferences.getString(Constants.LANGUAGE, null)
    }

    fun setCurrentDate(currentDate: String?): Boolean {
        return preferences.edit().putString(
            Constants.CURRENT_DATE,
            currentDate
        ).commit()
    }

    fun getCurrentDate(): String? {
        return preferences.getString(Constants.CURRENT_DATE, null)
    }

    fun setFirstNumber(firstNumber: String) {
        preferences.edit().putString(
            Constants.FIRST_NUMBER,
            firstNumber
        ).apply()
    }

    fun getFirstNumber(): String {
        return preferences.getString(Constants.FIRST_NUMBER, "null") ?: return "null"
    }

    fun setSecondNumber(secondNumber: String) {
        preferences.edit().putString(
            Constants.SECOND_NUMBER,
            secondNumber
        ).apply()
    }

    fun getSecondNumber(): String {
        return preferences.getString(Constants.SECOND_NUMBER, "null") ?: return "null"
    }
}