// !LANGUAGE: +MultiPlatformProjects
// WITH_RUNTIME
// IGNORE_BACKEND: JVM_IR
// FILE: common.kt

expect open class A() {
    open fun f(p: Int = 1)
}

expect open class B : A {
    override open fun f(p: Int)
}

// FILE: jvm.kt

import kotlin.test.assertEquals

var result = ""

actual open class A {
    actual open fun f(p: Int) {
        result += p
    }
}

actual open class B : A() {
    actual override open fun f(p: Int) {
        result += p
    }
}

fun box(): String {

    A().f()
    assertEquals("1", result)

    A().f(9)
    assertEquals("19", result)

    B().f()
    assertEquals("191", result)

    B().f(5)
    assertEquals("1915", result)

    return "OK"
}