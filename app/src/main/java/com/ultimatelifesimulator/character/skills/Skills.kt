package com.ultimatelifesimulator.character.skills

data class Skill(
    val id: String,
    val name: String,
    val category: SkillCategory,
    val level: Int = 0,
    val xp: Int = 0
) {
    fun xpToNextLevel(): Int = (level + 1) * 100
    
    fun addXp(amount: Int): Skill {
        var newXp = xp + amount
        var newLevel = level
        while (newXp >= xpToNextLevel()) {
            newXp -= xpToNextLevel()
            newLevel++
        }
        return copy(level = newLevel, xp = newXp)
    }
}

enum class SkillCategory {
    SOCIAL,
    INTELLECTUAL,
    CRIMINAL,
    COMBAT,
    PHYSICAL,
    PROFESSIONAL,
    CREATIVE,
    TECHNICAL
}

object SkillRegistry {
    val coreSkills = listOf(
        Skill("public_speaking", "Public Speaking", SkillCategory.SOCIAL),
        Skill("persuasion", "Persuasion", SkillCategory.SOCIAL),
        Skill("deception", "Deception", SkillCategory.SOCIAL),
        Skill("intimidation", "Intimidation", SkillCategory.SOCIAL),
        Skill("leadership", "Leadership", SkillCategory.SOCIAL),
        Skill("negotiation", "Negotiation", SkillCategory.SOCIAL),
        Skill("learning", "Learning", SkillCategory.INTELLECTUAL),
        Skill("research", "Research", SkillCategory.INTELLECTUAL),
        Skill("writing", "Writing", SkillCategory.INTELLECTUAL),
        Skill("mathematics", "Mathematics", SkillCategory.INTELLECTUAL),
        Skill("lockpicking", "Lockpicking", SkillCategory.CRIMINAL),
        Skill("stealth", "Stealth", SkillCategory.CRIMINAL),
        Skill("pickpocketing", "Pickpocketing", SkillCategory.CRIMINAL),
        Skill("forgery", "Forgery", SkillCategory.CRIMINAL),
        Skill("hacking", "Hacking", SkillCategory.CRIMINAL),
        Skill("melee_combat", "Melee Combat", SkillCategory.COMBAT),
        Skill("ranged_combat", "Ranged Combat", SkillCategory.COMBAT),
        Skill("martial_arts", "Martial Arts", SkillCategory.COMBAT),
        Skill("athletics", "Athletics", SkillCategory.PHYSICAL),
        Skill("fitness", "Fitness", SkillCategory.PHYSICAL),
        Skill("medicine", "Medicine", SkillCategory.PROFESSIONAL),
        Skill("law", "Law", SkillCategory.PROFESSIONAL),
        Skill("accounting", "Accounting", SkillCategory.PROFESSIONAL),
        Skill("programming", "Programming", SkillCategory.TECHNICAL),
        Skill("repair", "Repair", SkillCategory.TECHNICAL),
        Skill("cooking", "Cooking", SkillCategory.CREATIVE),
        Skill("painting", "Painting", SkillCategory.CREATIVE),
        Skill("music", "Music", SkillCategory.CREATIVE)
    )
}
