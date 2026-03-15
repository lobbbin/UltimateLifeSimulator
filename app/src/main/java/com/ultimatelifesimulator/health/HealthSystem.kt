package com.ultimatelifesimulator.health

data class HealthStatus(
    val physicalHealth: Int = 100,
    val mentalHealth: Int = 100,
    val energy: Int = 100,
    val hunger: Int = 0,
    val thirst: Int = 0,
    val pain: Int = 0,
    val immunity: Int = 100,
    val hygiene: Int = 100,
    val sleepQuality: Int = 100
)

data class Injury(
    val id: String,
    val name: String,
    val severity: InjurySeverity,
    val bodyPart: BodyPart,
    val healingTime: Int,
    val isChronic: Boolean = false,
    val effects: Map<String, Int> = emptyMap()
)

enum class InjurySeverity {
    MINOR, MODERATE, SEVERE, CRITICAL
}

enum class BodyPart {
    HEAD, TORSO, LEFT_ARM, RIGHT_ARM, LEFT_LEG, RIGHT_LEG, HAND, FOOT
}

data class Illness(
    val id: String,
    val name: String,
    val category: IllnessCategory,
    val symptoms: List<String>,
    val duration: Int,
    val contagious: Boolean = false,
    val mortality: Float = 0f,
    val treatments: List<String> = emptyList()
)

enum class IllnessCategory {
    INFECTION, CHRONIC, MENTAL, INJURY, ENVIRONMENTAL
}

class HealthManager {
    private var healthStatus = HealthStatus()
    private val injuries = mutableListOf<Injury>()
    private val illnesses = mutableListOf<Illness>()
    
    fun getHealthStatus(): HealthStatus = healthStatus
    
    fun takeDamage(amount: Int) {
        healthStatus = healthStatus.copy(
            physicalHealth = (healthStatus.physicalHealth - amount).coerceAtLeast(0)
        )
    }
    
    fun heal(amount: Int) {
        healthStatus = healthStatus.copy(
            physicalHealth = (healthStatus.physicalHealth + amount).coerceAtMost(100)
        )
    }
    
    fun changeEnergy(amount: Int) {
        healthStatus = healthStatus.copy(
            energy = (healthStatus.energy + amount).coerceIn(0, 100)
        )
    }
    
    fun changeHunger(amount: Int) {
        healthStatus = healthStatus.copy(
            hunger = (healthStatus.hunger + amount).coerceIn(0, 100)
        )
    }
    
    fun changeThirst(amount: Int) {
        healthStatus = healthStatus.copy(
            thirst = (healthStatus.thirst + amount).coerceIn(0, 100)
        )
    }
    
    fun changeStress(amount: Int) {
        healthStatus = healthStatus.copy(
            mentalHealth = (healthStatus.mentalHealth - amount).coerceAtLeast(0)
        )
    }
    
    fun improveMentalHealth(amount: Int) {
        healthStatus = healthStatus.copy(
            mentalHealth = (healthStatus.mentalHealth + amount).coerceAtMost(100)
        )
    }
    
    fun addInjury(injury: Injury) {
        injuries.add(injury)
    }
    
    fun removeInjury(injuryId: String) {
        injuries.removeAll { it.id == injuryId }
    }
    
    fun getInjuries(): List<Injury> = injuries.toList()
    
    fun addIllness(illness: Illness) {
        illnesses.add(illness)
    }
    
    fun removeIllness(illnessId: String) {
        illnesses.removeAll { it.id == illnessId }
    }
    
    fun getIllnesses(): List<Illness> = illnesses.toList()
    
    fun processHungerThirst() {
        if (healthStatus.hunger >= 100) {
            takeDamage(5)
        }
        if (healthStatus.thirst >= 100) {
            takeDamage(10)
        }
    }
    
    fun isDead(): Boolean = healthStatus.physicalHealth <= 0
    
    fun getOverallHealth(): Int = (healthStatus.physicalHealth + healthStatus.mentalHealth) / 2
}

object IllnessRegistry {
    val commonIllnesses = listOf(
        Illness("cold", "Common Cold", IllnessCategory.INFECTION, listOf("cough", "sneeze", "fatigue"), 7, true, 0f),
        Illness("flu", "Influenza", IllnessCategory.INFECTION, listOf("fever", "body_aches", "fatigue"), 10, true, 0.01f),
        Illness("pneumonia", "Pneumonia", IllnessCategory.INFECTION, listOf("cough", "fever", "difficulty_breathing"), 21, true, 0.1f),
        Illness("depression", "Depression", IllnessCategory.MENTAL, listOf("sadness", "fatigue", "insomnia"), -1, false, 0.05f),
        Illness("anxiety", "Anxiety", IllnessCategory.MENTAL, listOf("worry", "restlessness", "insomnia"), -1, false, 0.01f),
        Illness("diabetes", "Diabetes", IllnessCategory.CHRONIC, listOf("thirst", "fatigue"), -1, false, 0.05f),
        Illness("hypertension", "Hypertension", IllnessCategory.CHRONIC, listOf("headache", "dizziness"), -1, false, 0.02f),
        Illness("asthma", "Asthma", IllnessCategory.CHRONIC, listOf("shortness_breath", "wheezing"), -1, false, 0.01f)
    )
}
