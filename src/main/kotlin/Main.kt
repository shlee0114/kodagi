fun main() {
    println(test1(::square)(::triple)(2))
    println(test1(::triple)(::square)(2))
    println(test2(::triple)(::square)(2))
    val test3 = `3-9after`<Int, Int, Int, Int>()
    test3(1)(2)(3)(4)

    println(addTax(17.0)(10.0))
    println(addTax(10.0)(17.0))

    println(swapArgs(addTax)(10.0)(17.0))
}

val test1 = squareOfTriple<Int, Int, Int>()
val test2 = reverseSquareOfTriple<Int, Int, Int>()

fun <T, U, V> squareOfTriple(): ((U) -> T) -> ((V) -> U) -> (V) -> T =
    { f ->
        { g ->
            { x ->
                f(g(x))
            }
        }
    }

fun <T, U, V> reverseSquareOfTriple(): ((V) -> U) -> ((U) -> T) -> (V) -> T =
    { f ->
        { g ->
            { x ->
                g(f(x))
            }
        }
    }

fun square(i: Int): Int = i * i
fun triple(i: Int): Int = i * 3

fun <T, U, V> compose(f: (U) -> V, g: (T) -> U): (T) -> V = {
    f(g(it))
}

val add: IntBinOp = { a -> { b -> a + b } }

typealias IntBinOp = (Int) -> (Int) -> Int

val tuple1: ((Int) -> Int) -> (Int) -> Int = { a -> { b -> a(b) } }

fun <T, U, V> `3-7`(tuple1: T, tuple2: (T) -> (U) -> V): (U) -> V {
    return tuple2(tuple1)
}

fun <T, U, V> `3-8`(tuple1: U, tuple2: (T) -> (U) -> V): (T) -> V = { a: T ->
    tuple2(a)(tuple1)
}

fun <A, B, C, D> `3-9before`(a: A, b: B, c: C, d: D) = "$a,$b,$c,$d"

fun <A, B, C, D> `3-9after`(): (A) -> (B) -> (C) -> (D) -> String =
    { a -> { b -> { c -> { d -> "" } } } }

fun <A, B, C> `3-10`(f: (A, B) -> C): (A) -> (B) -> C = { a ->
    { b ->
        f(a, b)
    }
}

val addTax: (Double) -> (Double) -> Double = { a ->
    { b ->
        a + a / 100 * b
    }
}

fun <T, U, V> swapArgs(f: (T) -> (U) -> V): (U) -> (T) -> V = { u ->
    { t ->
        f(t)(u)
    }
}
