package com.ultimatelifesimulator.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
abstract class GameEntity(
    open val id: Long = 0,
    open val name: String = ""
) : Parcelable

@Parcelize
data class StatModifier(
    val statName: String,
    val value: Int,
    val duration: Int = -1,
    val description: String = ""
) : Parcelable

@Parcelize
data class Condition(
    val type: String,
    val target: String,
    val operator: String,
    val value: Int
) : Parcelable

@Parcelize
data class GameFlag(
    val key: String,
    val value: String,
    val timestamp: Long = System.currentTimeMillis()
) : Parcelable
