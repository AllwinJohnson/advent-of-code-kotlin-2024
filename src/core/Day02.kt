package core

import utils.println
import kotlin.math.abs

import java.io.File

fun main() {
    val reports = readInputAsIntLists("Day02")

    val safeReportsCount = reports.count { isSafeOrDampenedSafe(it) }
    println("Number of safe reports: $safeReportsCount")
}

/**
 * Checks if a report is safe or can be made safe by removing a single level.
 */
fun isSafeOrDampenedSafe(levels: List<Int>): Boolean {
    if (isSafeReport(levels)) return true

    // Try removing each level one at a time and check if the resulting list is safe
    return levels.indices.any { index ->
        val newLevels = levels.toMutableList().apply { removeAt(index) }
        isSafeReport(newLevels)
    }
}

/**
 * Checks if a report is safe based on the original criteria:
 * - Levels are strictly increasing or strictly decreasing.
 * - Differences between adjacent levels are between 1 and 3.
 */
fun isSafeReport(levels: List<Int>): Boolean {
    if (levels.size < 2) return true // A single level or empty list is trivially safe

    val differences = levels.zipWithNext { a, b -> b - a }
    val allIncreasing = differences.all { it in 1..3 }
    val allDecreasing = differences.all { it in -3..-1 }

    return allIncreasing || allDecreasing
}

fun readInputAsIntLists(fileName: String): List<List<Int>> {
    return File("src\\tests", "$fileName.txt").readLines().map { line ->
        line.trim().split(" ").map { it.toInt() }
    }
}
