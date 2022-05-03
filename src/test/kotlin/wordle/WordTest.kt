package wordle

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldNotBeTrue
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

internal class WordTest : StringSpec({

    "단어는 5자로 생성할 수 있다" {
        assertDoesNotThrow { Word("aaaaa") }
    }

    "단어는 5자 보다 작게 생성할 수 없다" {
        assertThrows<IllegalArgumentException> { Word("aaaa") }
    }

    "단어는 5자 보다 크게 생성할 수 없다" {
        assertThrows<IllegalArgumentException> { Word("aaaaaa") }
    }

    "해당 문자를 포함하고 있는지 확인한다" {
        assertSoftly(Word("abcde")) {
            contains('a').shouldBeTrue()
            contains('b').shouldBeTrue()
            contains('c').shouldBeTrue()
            contains('d').shouldBeTrue()
            contains('e').shouldBeTrue()
        }
    }

    "해당 문자를 포함하고 있지 않은지 확인한다" {
        assertSoftly(Word("abcde")) {
            contains('q').shouldNotBeTrue()
            contains('w').shouldNotBeTrue()
            contains('u').shouldNotBeTrue()
            contains('m').shouldNotBeTrue()
            contains('z').shouldNotBeTrue()
        }
    }

    "해당 위치에 문자가 위치하고 있는지 확인한다" {
        assertSoftly(Word("abcde")) {
            existAt('a', 0).shouldBeTrue()
            existAt('b', 1).shouldBeTrue()
            existAt('c', 2).shouldBeTrue()
            existAt('d', 3).shouldBeTrue()
            existAt('e', 4).shouldBeTrue()
        }
    }

    "해당 위치에 문자가 위치하고 있지 않은지 확인한다" {
        assertSoftly(Word("abcde")) {
            existAt('a', 1).shouldBeFalse()
            existAt('b', 2).shouldBeFalse()
            existAt('c', 3).shouldBeFalse()
            existAt('d', 4).shouldBeFalse()
            existAt('e', 0).shouldBeFalse()
            existAt('q', 0).shouldBeFalse()
            existAt('w', 4).shouldBeFalse()
            existAt('y', 3).shouldBeFalse()
            existAt('z', 0).shouldBeFalse()
        }
    }

    "해당 위치에 문자가 위치하고 있지 않은지 확인 시 위치할 수 없는 위치를 넘길 경우 예외처리 한다" {
        assertSoftly(Word("abcde")) {
            assertThrows<IllegalArgumentException> { existAt('a', -1) }
            assertThrows<IllegalArgumentException> { existAt('a', 5) }
            assertThrows<IllegalArgumentException> { existAt('b', 100) }
        }
    }
})
