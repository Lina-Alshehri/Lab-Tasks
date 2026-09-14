data class Course(
    val code: String,
    val title: String,
    val credits: Int,
    val days: String
)

fun findCourse(list: List<Course>, code: String): String {
    return list.firstOrNull { it.code == code }?.title ?: "Course not found"
}

fun main() {
    
    val courses = listOf(
        Course("CSC 402", "Mobile Application Programming", 3, "Mon/ Wed"),
        Course("CSC 311", "Database Systems", 4, "Sun /Tue"),
        Course("CSC 340", "Operating Systems", 3, "Mon/ Wed"),
        Course("MATH 202", "Discrete Mathematics", 3, "Sun/ Thu"),
        Course("ENG 214", "Technical Writing", 2, "Tue")
    )

    
    println("---All Courses---")
    courses.forEach { println(it) }
    println("\n--- Courses with 3+ Credits---")
    courses.filter { it.credits >= 3 }.forEach { println(it) }

    println("\n---Course Codes---")
    val courseCodes = courses.map { it.code }
    println(courseCodes)
    val totalCredits = courses.sumOf { it.credits }
    println("\nTotal Credit Hours: $totalCredits")

    println("\n--- Search Results---")
    println(findCourse(courses, "CSC 402"))
    println(findCourse(courses, "CSC 999"))

    
    println("\n---Copy Demonstration---")
    val originalCourse = courses.first { it.code == " CSC 402" }
    val modifiedCourse = originalCourse.copy(credits = 4)
    println("Modified Course: $modifiedCourse")
    println("Original Course in list: ${courses.first { it.code == "CSC 402 " }}")
