// !LANGUAGE: +MultiPlatformProjects
// WITH_RUNTIME
// IGNORE_BACKEND: JVM_IR
// FILE: common.kt

expect open class C() {
    open fun f(p: Int = 1)
    open fun f2(p1: Int = 1, p2: Int = 2)
    open fun ff(p1: Int, p2: Int = 2)
    open fun fff(p1: Int, p2: Int, p3: Int = 3)
    open fun fffx(p1: Int, p2: Int = 4, p3: Int = 5)
}
// FILE: jvm.kt

import kotlin.test.assertEquals

var result = ""

actual open class C {
    actual open fun f(p: Int) {
        result += p
    }
    actual open fun f2(p1: Int, p2: Int) {
        result += p1
        result += p2
    }
    actual open fun ff(p1: Int, p2: Int) {
        result += p1
        result += p2
    }
    actual open fun fff(p1: Int, p2: Int, p3: Int) {
        result += p1
        result += p2
        result += p3
    }
    actual open fun fffx(p1: Int, p2: Int, p3: Int) {
        result += p1
        result += p2
        result += p3
    }
}

fun box(): String {

    C().f()
    assertEquals("1", result)

    C().f2()
    assertEquals("112", result)

    C().ff(1)
    assertEquals("11212", result)

    C().fff(1, 2)
    assertEquals("11212123", result)

    C().fffx(3)
    assertEquals("11212123345", result)

    return "OK"
}