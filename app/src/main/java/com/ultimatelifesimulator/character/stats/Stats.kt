package com.ultimatelifesimulator.character.stats

data class PrimaryStats(
    val health: Int = 100,
    val energy: Int = 100,
    val stress: Int = 0,
    val charisma: Int = 50,
    val intellect: Int = 50,
    val cunning: Int = 50,
    val violence: Int = 50,
    val stealth: Int = 50,
    val perception: Int = 50,
    val willpower: Int = 50
) {
    fun clamp() = copy(
        health = health.coerceIn(0, 100),
        energy = energy.coerceIn(0, 100),
        stress = stress.coerceIn(0, 100),
        charisma = charisma.coerceIn(0, 100),
        intellect = intellect.coerceIn(0, 100),
        cunning = cunning.coerceIn(0, 100),
        violence = violence.coerceIn(0, 100),
        stealth = stealth.coerceIn(0, 100),
        perception = perception.coerceIn(0, 100),
        willpower = willpower.coerceIn(0, 100)
    )
}

data class SecondaryStats(
    val reputation: Int = 0,
    val wealth: Double = 0.0,
    val piety: Int = 0,
    val heat: Int = 0,
    val addiction: Int = 0,
    val streetCred: Int = 0,
    val nobleStanding: Int = 0,
    val politicalCapital: Int = 0
)
