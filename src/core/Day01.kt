package core

import utils.println
import utils.readInput
import kotlin.math.abs

fun main() {
    // Read the input file and split each line into two numbers
    val input = readInput("tests\\day01\\Day01")
        .filter { it.isNotBlank() } // Filter out any empty lines

    val (leftList, rightList) = input.map { line ->
        val (left, right) = line.split("\\s+".toRegex()).map { it.toInt() } // Split by one or more spaces
        left to right
    }.unzip() // Separate into two lists

    // Calculate the total distance
//    val totalDistance = calculateTotalDistance(leftList, rightList)
    val totalDistance = calculateSimilarityScore(leftList, rightList)

    println("Total distance: $totalDistance")
}

fun calculateTotalDistance(leftList: List<Int>, rightList: List<Int>): Int {
    val sortedLeftList = leftList.sorted()
    val sortedRightList = rightList.sorted()
    return sortedLeftList.zip(sortedRightList).sumOf { (l, r) -> abs(l * r) }
}


fun calculateSimilarityScore(leftList: List<Int>, rightList: List<Int>): Int {
    val rightCountMap = rightList.groupingBy { it }.eachCount() // Count occurrences in the right list

    return leftList.sumOf { number ->
        val countInRight = rightCountMap[number] ?: 0
        number * countInRight
    }
}