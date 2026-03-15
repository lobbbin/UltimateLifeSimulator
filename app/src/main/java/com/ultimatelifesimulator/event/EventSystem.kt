package com.ultimatelifesimulator.event

import com.ultimatelifesimulator.core.model.Condition
import com.ultimatelifesimulator.core.model.GameFlag
import com.ultimatelifesimulator.core.model.StatModifier

data class GameEvent(
    val id: String,
    val title: String,
    val description: String,
    val category: EventCategory,
    val choices: List<EventChoice>,
    val conditions: List<Condition> = emptyList(),
    val cooldown: Int = 0,
    val lifetime: Int = -1
)

data class EventChoice(
    val id: String,
    val text: String,
    val successChance: Float = 1.0f,
    val effects: List<EventEffect> = emptyList(),
    val requirements: List<Condition> = emptyList()
)

data class EventEffect(
    val type: EffectType,
    val target: String,
    val value: Int,
    val description: String = ""
)

enum class EventCategory {
    RANDOM,
    STORY,
    LIFE,
    ROYALTY,
    POLITICS,
    CRIME,
    BUSINESS,
    CAREER,
    HEALTH,
    RELATIONSHIP
}

enum class EffectType {
    STAT_CHANGE,
    SKILL_XP,
    MONEY_CHANGE,
    RELATIONSHIP_CHANGE,
    TRAIT_GAIN,
    TRAIT_LOSE,
    FLAG_SET,
    FLAG_CLEAR,
    LOCATION_CHANGE,
    DAMAGE,
    HEAL,
    STRESS_CHANGE,
    ENERGY_CHANGE,
    FACTION_REP_CHANGE,
    EVENT_TRIGGER,
    GAME_OVER
}

class RandomEventGenerator {
    private val randomEvents = mutableListOf<GameEvent>()
    
    init {
        initializeEvents()
    }
    
    private fun initializeEvents() {
        // Royalty Events
        randomEvents.add(
            GameEvent(
                id = "noble_request",
                title = "Noble's Request",
                description = "A vassal approaches you requesting a tax break for their village.",
                category = EventCategory.ROYALTY,
                choices = listOf(
                    EventChoice("grant", "Grant the tax break", effects = listOf(
                        EventEffect(EffectType.FACTION_REP_CHANGE, "noble_houses", 10, "Nobles appreciate your generosity"),
                        EventEffect(EffectType.MONEY_CHANGE, "treasury", -500, "Lost tax revenue")
                    )),
                    EventChoice("deny", "Deny the request", effects = listOf(
                        EventEffect(EffectType.FACTION_REP_CHANGE, "noble_houses", -5, "Noble is disappointed")
                    )),
                    EventChoice("delay", "Delay the decision", effects = listOf(
                        EventEffect(EffectType.STRESS_CHANGE, "player", 5, "More paperwork")
                    ))
                )
            )
        )
        
        randomEvents.add(
            GameEvent(
                id = "diplomat_arrives",
                title = "Foreign Diplomat",
                description = "A diplomat from a neighboring kingdom arrives with an alliance proposal.",
                category = EventCategory.ROYALTY,
                choices = listOf(
                    EventChoice("accept", "Accept the alliance", effects = listOf(
                        EventEffect(EffectType.FLAG_SET, "foreign_alliance", "true"),
                        EventEffect(EffectType.STAT_CHANGE, "reputation", 10)
                    )),
                    EventChoice("reject", "Reject the alliance", effects = listOf(
                        EventEffect(EffectType.STAT_CHANGE, "reputation", -5)
                    )),
                    EventChoice("negotiate", "Request better terms", successChance = 0.7f, effects = listOf(
                        EventEffect(EffectType.FLAG_SET, "foreign_alliance", "negotiating")
                    ))
                )
            )
        )
        
        // Political Events
        randomEvents.add(
            GameEvent(
                id = "town_hall_heckler",
                title = "Town Hall Heckler",
                description = "During your speech, an audience member starts heckling you.",
                category = EventCategory.POLITICS,
                choices = listOf(
                    EventChoice("ignore", "Ignore and continue", effects = listOf(
                        EventEffect(EffectType.STAT_CHANGE, "charisma", 5)
                    )),
                    EventChoice("confront", "Confront the heckler", successChance = 0.6f, effects = listOf(
                        EventEffect(EffectType.STAT_CHANGE, "charisma", 10)
                    )),
                    EventChoice("respond", "Witty comeback", successChance = 0.5f, effects = listOf(
                        EventEffect(EffectType.STAT_CHANGE, "charisma", 15)
                    ))
                )
            )
        )
        
        randomEvents.add(
            GameEvent(
                id = "fundraiser_scandal",
                title = "Fundraiser Scandal",
                description = "A major donor has been caught in a scandal. Their opponents want you to return their contributions.",
                category = EventCategory.POLITICS,
                choices = listOf(
                    EventChoice("return", "Return the money", effects = listOf(
                        EventEffect(EffectType.MONEY_CHANGE, "campaign_funds", -10000),
                        EventEffect(EffectType.STAT_CHANGE, "reputation", 5)
                    )),
                    EventChoice("keep", "Keep the money", effects = listOf(
                        EventEffect(EffectType.MONEY_CHANGE, "campaign_funds", 10000),
                        EventEffect(EffectType.STAT_CHANGE, "reputation", -10)
                    )),
                    EventChoice("ignore", "Ignore the controversy", effects = listOf(
                        EventEffect(EffectType.STAT_CHANGE, "reputation", -5)
                    ))
                )
            )
        )
        
        // Crime Events
        randomEvents.add(
            GameEvent(
                id = "cop_pull_over",
                title = "Police Pull Over",
                description = "A police car signals you to pull over. Your car was reported stolen... wait, it wasn't... was it?",
                category = EventCategory.CRIME,
                choices = listOf(
                    EventChoice("comply", "Comply calmly", successChance = 0.7f, effects = listOf(
                        EventEffect(EffectType.HEAT_CHANGE, "player", -5, "Police satisfied")
                    )),
                    EventChoice("bribe", "Offer a bribe", successChance = 0.5f, effects = listOf(
                        EventEffect(EffectType.MONEY_CHANGE, "player", -500, "Bribe paid"),
                        EventEffect(EffectType.HEAT_CHANGE, "player", -10)
                    )),
                    EventChoice("run", "Attempt to flee", successChance = 0.3f, effects = listOf(
                        EventEffect(EffectType.HEAT_CHANGE, "player", 30, "Police now suspicious"),
                        EventEffect(EffectType.FLAG_SET, "police_chase", "true")
                    ))
                )
            )
        )
        
        randomEvents.add(
            GameEvent(
                id = "rival_gang_turf",
                title = "Rival Gang Challenge",
                description = "A rival gang is moving in on your territory. Your crew awaits your response.",
                category = EventCategory.CRIME,
                choices = listOf(
                    EventChoice("fight", "Fight back", successChance = 0.5f, effects = listOf(
                        EventEffect(EffectType.STREET_CRED_CHANGE, "player", 10),
                        EventEffect(EffectType.DAMAGE, "player", 20)
                    )),
                    EventChoice("negotiate", "Negotiate", successChance = 0.6f, effects = listOf(
                        EventEffect(EffectType.MONEY_CHANGE, "player", -1000, "Paid for peace")
                    )),
                    EventChoice("retreat", "Retreat", effects = listOf(
                        EventEffect(EffectType.STREET_CRED_CHANGE, "player", -10)
                    ))
                )
            )
        )
        
        // Business Events
        randomEvents.add(
            GameEvent(
                id = "business_rival",
                title = "Business Rival",
                description = "A competitor has launched a product directly competing with yours at a lower price.",
                category = EventCategory.BUSINESS,
                choices = listOf(
                    EventChoice("lower_price", "Lower your prices", effects = listOf(
                        EventEffect(EffectType.MONEY_CHANGE, "business", -2000),
                        EventEffect(EffectType.FLAG_SET, "price_war", "true")
                    )),
                    EventChoice("advertise", "Increase advertising", effects = listOf(
                        EffectType.MONEY_CHANGE, "business", -1000
                    )),
                    EventChoice("innovate", "Innovate your product", successChance = 0.5f, effects = listOf(
                        EventEffect(EffectType.FLAG_SET, "innovation", "true")
                    ))
                )
            )
        )
        
        // Health Events
        randomEvents.add(
            GameEvent(
                id = "sick_day",
                title = "Feeling Unwell",
                description = "You wake up feeling under the weather. Your body is telling you to rest.",
                category = EventCategory.HEALTH,
                choices = listOf(
                    EventChoice("rest", "Rest for the day", effects = listOf(
                        EventEffect(EffectType.ENERGY_CHANGE, "player", 30),
                        EventEffect(EffectType.HEAL, "player", 10)
                    )),
                    EventChoice("push", "Push through", effects = listOf(
                        EventEffect(EffectType.ENERGY_CHANGE, "player", -20),
                        EventEffect(EffectType.HEALTH_CHANGE, "player", -10)
                    )),
                    EventChoice("doctor", "Visit a doctor", effects = listOf(
                        EventEffect(EffectType.MONEY_CHANGE, "player", -100),
                        EventEffect(EffectType.HEAL, "player", 15)
                    ))
                )
            )
        )
        
        // Relationship Events
        randomEvents.add(
            GameEvent(
                id = "friend_asking_favor",
                title = "Friend's Favor",
                description = "A close friend asks for your help with a personal matter.",
                category = EventCategory.RELATIONSHIP,
                choices = listOf(
                    EventChoice("help", "Help them out", effects = listOf(
                        EventEffect(EffectType.RELATIONSHIP_CHANGE, "friend", 15),
                        EventEffect(EffectType.ENERGY_CHANGE, "player", -10)
                    )),
                    EventChoice("refuse", "Refuse", effects = listOf(
                        EventEffect(EffectType.RELATIONSHIP_CHANGE, "friend", -10)
                    )),
                    EventChoice("later", "Promise to help later", effects = listOf(
                        EventEffect(EffectType.RELATIONSHIP_CHANGE, "friend", 5)
                    ))
                )
            )
        )
        
        // Random Life Events
        randomEvents.add(
            GameEvent(
                id = "windfall",
                title = "Unexpected Windfall",
                description = "You find some money on the street! Lucky day!",
                category = EventCategory.RANDOM,
                choices = listOf(
                    EventChoice("keep", "Keep it", effects = listOf(
                        EventEffect(EffectType.MONEY_CHANGE, "player", 500)
                    )),
                    EventChoice("donate", "Donate to charity", effects = listOf(
                        EventEffect(EffectType.MONEY_CHANGE, "player", -500),
                        EventEffect(EffectType.STAT_CHANGE, "reputation", 10),
                        EventEffect(EffectType.STAT_CHANGE, "piety", 5)
                    ))
                )
            )
        )
    }
    
    fun getRandomEvent(): GameEvent? {
        return randomEvents.randomOrNull()
    }
    
    fun getEventById(id: String): GameEvent? {
        return randomEvents.find { it.id == id }
    }
    
    fun getEventsByCategory(category: EventCategory): List<GameEvent> {
        return randomEvents.filter { it.category == category }
    }
}
