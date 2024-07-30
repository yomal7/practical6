def getStudentInfo(): (String, Int, Int, Float, Char) ={
    val name = scala.io.StdIn.readLine("Enter Student's name: ").trim()
    if(name.isEmpty){
        println("You have to enter a name.")
        getStudentInfo()
    }
    else{
        val totalPossibleMarks = scala.io.StdIn.readLine("Enter total possible marks: ").trim().toInt
        val marks = getMarks(totalPossibleMarks)
        val precentage = (marks/totalPossibleMarks.toFloat)*100
        val grade = precentage match{
            case x if(x < 50) => 'D'
            case x if(x < 75) => 'C'
            case x if(x < 90) => 'B'
            case _ => 'A'
        }

        (name, marks, totalPossibleMarks, precentage, grade)
    }
}

def getMarks(totalPossibleMarks: Int): Int = {
    val marks = scala.io.StdIn.readLine("Enter marks: ").trim().toInt
    if(marks >= 0 && totalPossibleMarks >= marks) marks
    else{
        println("Enter a valid marks")
        getMarks(totalPossibleMarks)
    }
}

def printStudentRecord(tuple: (String, Int, Int, Float, Char)): Unit = {
    println(f"\nStudent Recode\nName : ${tuple._1}\nMarks : ${tuple._2}\nTotal Possible Marks: ${tuple._3}\nPercentage: ${tuple._4}%.2f\nGrade: ${tuple._5}")
}

def validateInput(name: String, marks: Int, totalPossibleMarks: Int, precentage: Float, grade: Char): (Boolean, Option[String]) ={
    if(name.isEmpty){
        (false,Some("Name cannot be empty."))
    }
    else if(totalPossibleMarks<= 0){
        (false, Some("Invalide total possible marks."))
    }
    else if(marks < 0){
        (false, Some("Marks must be positive."))
    }
    else if(marks > totalPossibleMarks){
        (false, Some("Marks cannot grater than total possible marks."))
    }
    else{
        (true, None)
    }
}

def getStudentInfoWithRetry(): (String, Int, Int, Float, Char) = {
    val name = scala.io.StdIn.readLine("\nEnter Student's name: ").trim()
    val totalPossibleMarks = scala.io.StdIn.readLine("Enter total possible marks: ").trim().toInt
    val marks = scala.io.StdIn.readLine("Enter marks: ").trim().toInt
    val percentage = (marks.toFloat / totalPossibleMarks) * 100
    val grade = percentage match {
        case x if x < 50 => 'D'
        case x if x < 75 => 'C'
        case x if x < 90 => 'B'
        case _ => 'A'
    }

    val (valid, err) = validateInput(name, marks, totalPossibleMarks, percentage, grade)
    if (!valid) {
        println(s"\nValidation failed: ${err.get}\n")
        getStudentInfo()  
    } else {
        (name, marks, totalPossibleMarks, percentage, grade)
    }
}

@main def practical6_2(): Unit = {
    println("using getStudentInfo")
    val student1 = getStudentInfo()
    printStudentRecord(student1)

    println("\nusing getStudentInfoWithRetry")
    val student2 = getStudentInfoWithRetry()
    printStudentRecord(student2)

}