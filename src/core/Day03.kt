package core

import java.io.File

fun main() {
    val input = File("src/tests/Day03.txt").readText()

    val mulRegex = """mul\((\d+),(\d+)\)""".toRegex()
    val doRegex = """do\(\)""".toRegex()
    val dontRegex = """don't\(\)""".toRegex()

    var isMulEnabled = true
    var totalSum = 0

    val tokens = input.split(Regex("""\s+|(?=do\(\))|(?=don't\(\))|(?=mul\(\d+,\d+\))"""))
    tokens.forEach { line ->

        when {
            doRegex.containsMatchIn(line) -> isMulEnabled = true
            dontRegex.containsMatchIn(line) -> isMulEnabled = false
            mulRegex.containsMatchIn(line) -> {
                if (isMulEnabled) {
                    val (x, y) = mulRegex.find(line)!!.destructured
                    totalSum += x.toInt() * y.toInt()
                }
            }
        }

    }
    println("Total sum of enabled multiplications: $totalSum")
}
