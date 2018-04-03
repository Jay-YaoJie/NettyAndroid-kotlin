package com.ftrd.flashlight.util

import android.R.id.edit
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 * @author: Jeff <15899859876@qq.com>
 * @date:  2018-03-01 16:50
 * @description:
 */
object SharedPreferencesUtil {
    fun getString(context: Context, key: String,
                  defaultValue: String): String? {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getString(key, defaultValue)
    }

    fun setString(context: Context, key: String,
                  value: String) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putString(key, value).apply()
    }

    fun getBoolean(context: Context, key: String,
                   defaultValue: Boolean): Boolean {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getBoolean(key, defaultValue)
    }

    fun hasKey(context: Context, key: String): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(context).contains(
                key)
    }

    fun setBoolean(context: Context, key: String,
                   value: Boolean) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putBoolean(key, value).apply()
    }

    fun setInt(context: Context, key: String,
               value: Int) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putInt(key, value).apply()
    }

    fun getInt(context: Context, key: String,
               defaultValue: Int): Int {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getInt(key, defaultValue)
    }

    fun setFloat(context: Context, key: String,
                 value: Float) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putFloat(key, value).apply()
    }

    fun getFloat(context: Context, key: String,
                 defaultValue: Float): Float {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getFloat(key, defaultValue)
    }

    fun setLong(context: Context, key: String,
                value: Long) {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        settings.edit().putLong(key, value).apply()
    }

    fun getLong(context: Context, key: String,
                defaultValue: Long): Long {
        val settings = PreferenceManager
                .getDefaultSharedPreferences(context)
        return settings.getLong(key, defaultValue)
    }

    fun clear(context: Context,
              p: SharedPreferences) {
        val editor = p.edit()
        editor.clear()
        editor.apply()
    }
}
