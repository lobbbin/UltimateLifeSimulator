package com.ultimatelifesimulator.relationships

data class Relationship(
    val id: Long = 0,
    val npcId: String,
    val npcName: String,
    val type: RelationshipType,
    val score: Int = 0,
    val trust: Int = 50,
    val fear: Int = 0,
    val respect: Int = 50,
    val loyalty: Int = 50,
    val debt: Int = 0,
    val memories: List<String> = emptyList(),
    val isRomantic: Boolean = false,
    val isFamily: Boolean = false
)

enum class RelationshipType {
    PARENT,
    CHILD,
    SIBLING,
    SPOUSE,
    LOVER,
    FRIEND,
    ACQUAINTANCE,
    RIVAL,
    ENEMY,
    MENTOR,
    PROTÉGÉ,
    EMPLOYER,
    EMPLOYEE,
    COLLEAGUE,
    CLIENT,
    SUPPLIER,
    CRIMINAL_PARTNER,
    INFORMANT,
    CELLMATE,
    NEIGHBOR,
    STRANGER
}

class RelationshipManager {
    private val relationships = mutableMapOf<String, Relationship>()
    
    fun addRelationship(npcId: String, npcName: String, type: RelationshipType): Relationship {
        val relationship = Relationship(
            npcId = npcId,
            npcName = npcName,
            type = type,
            isFamily = isFamilyRelationship(type),
            isRomantic = isRomanticRelationship(type)
        )
        relationships[npcId] = relationship
        return relationship
    }
    
    fun getRelationship(npcId: String): Relationship? = relationships[npcId]
    
    fun getAllRelationships(): List<Relationship> = relationships.values.toList()
    
    fun getRelationshipsByType(type: RelationshipType): List<Relationship> =
        relationships.values.filter { it.type == type }
    
    fun modifyScore(npcId: String, amount: Int) {
        relationships[npcId]?.let { rel ->
            relationships[npcId] = rel.copy(score = (rel.score + amount).coerceIn(-100, 100))
        }
    }
    
    fun modifyTrust(npcId: String, amount: Int) {
        relationships[npcId]?.let { rel ->
            relationships[npcId] = rel.copy(trust = (rel.trust + amount).coerceIn(0, 100))
        }
    }
    
    fun addMemory(npcId: String, memory: String) {
        relationships[npcId]?.let { rel ->
            val newMemories = rel.memories + memory
            relationships[npcId] = rel.copy(memories = newMemories.takeLast(10))
        }
    }
    
    fun addDebt(npcId: String, amount: Int) {
        relationships[npcId]?.let { rel ->
            relationships[npcId] = rel.copy(debt = rel.debt + amount)
        }
    }
    
    fun repayDebt(npcId: String, amount: Int) {
        relationships[npcId]?.let { rel ->
            val newDebt = (rel.debt - amount).coerceAtLeast(0)
            relationships[npcId] = rel.copy(debt = newDebt)
        }
    }
    
    private fun isFamilyRelationship(type: RelationshipType): Boolean =
        type in listOf(RelationshipType.PARENT, RelationshipType.CHILD, RelationshipType.SIBLING)
    
    private fun isRomanticRelationship(type: RelationshipType): Boolean =
        type in listOf(RelationshipType.SPOUSE, RelationshipType.LOVER)
    
    fun getFamilyMembers(): List<Relationship> = relationships.values.filter { it.isFamily }
    fun getFriends(): List<Relationship> = relationships.values.filter { it.type == RelationshipType.FRIEND }
    fun getRivals(): List<Relationship> = relationships.values.filter { it.type == RelationshipType.RIVAL }
    fun getEnemies(): List<Relationship> = relationships.values.filter { it.type == RelationshipType.ENEMY }
}
