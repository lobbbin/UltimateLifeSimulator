package com.ultimatelifesimulator.character.traits

data class Trait(
    val id: String,
    val name: String,
    val description: String,
    val type: TraitType,
    val statModifiers: Map<String, Int> = emptyMap()
)

enum class TraitType {
    POSITIVE,
    NEGATIVE,
    NEUTRAL
}

object TraitRegistry {
    val positiveTraits = listOf(
        Trait("ambitious", "Ambitious", "Driven to succeed", TraitType.POSITIVE, mapOf("willpower" to 5)),
        Trait("charismatic", "Charismatic", "Natural charm", TraitType.POSITIVE, mapOf("charisma" to 10)),
        Trait("lucky", "Lucky", "Fortune favors you", TraitType.POSITIVE),
        Trait("connected", "Connected", "Knows important people", TraitType.POSITIVE),
        Trait("resilient", "Resilient", "Bounces back from setbacks", TraitType.POSITIVE, mapOf("willpower" to 10)),
        Trait("just", "Just", "Fairs and impartial", TraitType.POSITIVE),
        Trait("brave", "Brave", "Not afraid of danger", TraitType.POSITIVE, mapOf("violence" to 5)),
        Trait("diligent", "Diligent", "Hardworking", TraitType.POSITIVE),
        Trait("patient", "Patient", "Calm under pressure", TraitType.POSITIVE, mapOf("willpower" to 5)),
        Trait("honest", "Honest", "Trustworthy", TraitType.POSITIVE),
        Trait("athletic", "Athletic", "Physically fit", TraitType.POSITIVE, mapOf("fitness" to 10, "athletics" to 10)),
        Trait("bookworm", "Bookworm", "Loves learning", TraitType.POSITIVE, mapOf("intellect" to 10)),
        Trait("green_thumb", "Green Thumb", "Good with plants", TraitType.POSITIVE),
        Trait("polyglot", "Polyglot", "Languages come easily", TraitType.POSITIVE, mapOf("intellect" to 5)),
        Trait("leader", "Leader", "Natural leader", TraitType.POSITIVE, mapOf("leadership" to 15))
    )

    val negativeTraits = listOf(
        Trait("greedy", "Greedy", "Obsessed with wealth", TraitType.NEGATIVE, mapOf("charisma" to -5)),
        Trait("paranoid", "Paranoid", "Trusts no one", TraitType.NEGATIVE, mapOf("perception" to 5)),
        Trait("addict", "Addict", "Prone to addiction", TraitType.NEGATIVE),
        Trait("hot_headed", "Hot-Headed", "Quick to anger", TraitType.NEGATIVE, mapOf("violence" to 5)),
        Trait("cowardly", "Cowardly", "Avoids danger", TraitType.NEGATIVE, mapOf("violence" to -10)),
        Trait("dishonest", "Dishonest", "Lies easily", TraitType.NEGATIVE),
        Trait("sickly", "Sickly", "Prone to illness", TraitType.NEGATIVE, mapOf("health" to -10)),
        Trait("lazy", "Lazy", "Avoids work", TraitType.NEGATIVE, mapOf("energy" to 5)),
        Trait("gluttonous", "Gluttonous", "Overeats", TraitType.NEGATIVE),
        Trait("envious", "Envious", "Jealous of others", TraitType.NEGATIVE),
        Trait("prideful", "Prideful", "Thinks highly of self", TraitType.NEGATIVE),
        Trait("wrathful", "Wrathful", "Quick to violence", TraitType.NEGATIVE, mapOf("violence" to 10)),
        Trait("deceitful", "Deceitful", "Trickster", TraitType.NEGATIVE, mapOf("cunning" to 5)),
        Trait("shy", "Shy", "Quiet and reserved", TraitType.NEGATIVE, mapOf("charisma" to -10)),
        Trait("clumsy", "Clumsy", "Accident-prone", TraitType.NEGATIVE),
        Trait("insomniac", "Insomniac", "Can't sleep well", TraitType.NEGATIVE, mapOf("energy" to -10))
    )

    val neutralTraits = listOf(
        Trait("religious", "Religious", "Devout faith", TraitType.NEUTRAL, mapOf("piety" to 10)),
        Trait("cynical", "Cynical", "Skeptical of others", TraitType.NEUTRAL),
        Trait("romantic", "Romantic", "Dreamy idealist", TraitType.NEUTRAL),
        Trait("solitary", "Solitary", "Prefers alone time", TraitType.NEUTRAL),
        Trait("night_owl", "Night Owl", "Active at night", TraitType.NEUTRAL),
        Trait("early_bird", "Early Bird", "Morning person", TraitType.NEUTRAL),
        Trait("superstitious", "Superstitious", "Believes in omens", TraitType.NEUTRAL),
        Trait("pragmatic", "Pragmatic", "Practical thinker", TraitType.NEUTRAL),
        Trait("idealistic", "Idealistic", "Believes in perfection", TraitType.NEUTRAL),
        Trait("traditionalist", "Traditionalist", "Values old ways", TraitType.NEUTRAL)
    )

    val allTraits = positiveTraits + negativeTraits + neutralTraits

    fun getTrait(id: String): Trait? = allTraits.find { it.id == id }
}
