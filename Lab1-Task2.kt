fun greetStudent(name: String, course: String = "CSC 402"): String {
    return "Welcome to $course, $name!"
}


fun finalMark(quizzes: Double, project: Double, finalExam: Double): Double =
    (quizzes * 0.10) + (project * 0.70) + (finalExam * 0.20)

fun printBanner(title: String, width: Int = 40) {
    println("-".repeat(width))
    println(title)
    println("-".repeat(width))
}

fun task2() {

    printBanner("CSC 402 Lab 2")


    println(greetStudent("Sara"))
    println(greetStudent("Mona", "CSC 101"))


    val result1 = finalMark(85.0, 90.0, 95.0)
    val result2 = finalMark(finalExam = 95.0, quizzes = 85.0, project = 90.0)

    println("Final Mark (Positional): $result1")
    println("Final Mark (Named): $result2")
}

fun main() {
    task2()
}
