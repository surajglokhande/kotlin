// !LANGUAGE: +MultiPlatformProjects
// WITH_RUNTIME
// IGNORE_BACKEND: JVM_IR
// FILE: common.kt

expect open class C() {
    open fun f(p: Int = 2)
}

// FILE: jvm.kt

import kotlin.test.assertEquals

var result = ""

actual open class C {
    actual open fun f(p: Int) {
        result += p
    }
}

open class D : C() {
    override open fun f(p: Int) {
        result += p
    }
}

fun box(): String {

    C().f()
    assertEquals("2", result)

    C().f(9)
    assertEquals("29", result)

    D().f()
    assertEquals("292", result)

    D().f(5)
    assertEquals("2925", result)

    return "OK"
}