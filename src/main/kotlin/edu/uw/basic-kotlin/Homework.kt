package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(input: Any): String {
    return when (input) {
        "Hello" -> "world"
        "Howdy" -> "Say what?"
        "Bonjour" -> "Say what?"
        is String -> "I don't understand"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(a: Int, b: Int): Int {
    return a + b
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(a: Int, b: Int): Int {
    return a - b
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(a: Int, b: Int, operator: (Int, Int) -> Int): Int {
    return operator(a,b)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(var amount: Int, var currency: String) {

    init {
        require(amount >= 0)
        require(currency in setOf("USD", "EUR", "CAN", "GBP"))
    }

    private val conversionRates = mapOf(
            "USD" to mapOf("USD" to 1.0, "GBP" to 0.5, "EUR" to 1.5, "CAN" to 1.25),
            "GBP" to mapOf("USD" to 2.0, "GBP" to 1.0, "EUR" to 3.0, "CAN" to 2.5),
            "EUR" to mapOf("USD" to 2/3.0, "GBP" to 1/3.0, "EUR" to 1.0, "CAN" to 5/6.0),
            "CAN" to mapOf("USD" to 4/5.0, "GBP" to 2/5.0, "EUR" to 6/5.0, "CAN" to 1.0)
    )

    fun convert(newCurrency: String): Money {
        require(newCurrency in setOf("USD", "EUR", "CAN", "GBP"))
        if (currency == newCurrency) {
            return Money(amount, newCurrency)
        }
        val conversionRate = conversionRates[currency]?.get(newCurrency)
                ?: throw IllegalArgumentException("Currency conversion not supported")
        val newAmount = (amount * conversionRate).toInt()
        return Money(newAmount, newCurrency)
    }

    operator fun plus(other: Money): Money {
        val convertedOther = other.convert(currency)
        return Money(amount + convertedOther.amount, currency)
    }
}
