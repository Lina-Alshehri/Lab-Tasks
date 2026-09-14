fun main() {
val  university = "Imam Abdulrahman Bin Faisal University"
val courseCode = "CSC 402"
val creditHours = 3
var studentsEnrolled = 28 
val isElective = false

    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).")
    
    studentsEnrolled += 2
    println("$courseCode has $studentsEnrolled students enrolled ($creditHours credit hours).") 

    println("University name length: ${university.length}")
}
