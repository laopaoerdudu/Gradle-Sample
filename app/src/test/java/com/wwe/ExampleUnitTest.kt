package com.wwe

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun test() {
        val lambda: (result: Int) -> Unit = { result ->
            println(result)
        }

        (1..10).forEach { value ->
            calculate(value, lambda)
        }
    }

    fun calculate(x: Int, lambda: (result: Int) -> Unit) {
        lambda(x + 10)
    }

    @Test
    fun testKotlin() {
        if (true) println("A")
        if (true) { println("B") }
        if (true) {
            { println("C") }.invoke()
        }
        { println("D") }.invoke()

        printE().invoke()

        when {
            true -> { println("F") }
        }
    }

    fun printE() = { println("E") }
}