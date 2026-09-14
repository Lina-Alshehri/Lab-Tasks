fun describeTeam(teamName: String?): String {
    
    return teamName?.let { "Team: $it (${it.length} characters)" } ?: "Team not registered yet"
}

fun main() {
    val nickname: String? = null
    val fullName: String = "Abdullah Al-Qahtani"

  
    println(fullName.length)
    println(nickname?.length)
    println(nickname ?: "no nickname set")
    println(describeTeam("Team Falcon"))
    println(describeTeam(null))

    
}
