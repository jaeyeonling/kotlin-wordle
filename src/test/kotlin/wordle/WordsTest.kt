package wordle

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.engine.spec.tempfile
import io.kotest.matchers.shouldBe

internal class WordsTest : StringSpec({

    "존재하지 않은 파일로 단어들을 만들 수 없다" {
        val notExistsFile = Resources.open("not-exists")
        shouldThrow<IllegalArgumentException> { notExistsFile.toWords() }
    }

    "파일을 기반으로 단어들을 만든다" {
        val file = tempfile().apply { writeText("Hello\nWorld") }

        file.toWords() shouldBe Words("Hello", "World")
    }

    "1개의 단어들 중 첫 위치에 존재하는 단어를 선택한다" {
        val words = Words("abcde")

        words.pick(0) shouldBe Word("abcde")
    }

    "여러개의 단어들 중 첫 위치에 존재하는 단어를 선택한다" {
        val words = Words("abcde", "hello", "world")

        words.pick(0) shouldBe Word("abcde")
    }

    "여러개의 단어들 중 두번째 위치에 존재하는 단어를 선택한다" {
        val words = Words("abcde", "hello", "world")

        words.pick(1) shouldBe Word("hello")
    }

    "1개의 단어들 중 특정 위치에 존재하는 단어를 선택한다" {
        assertSoftly(Words("abcde")) {
            pick(1) shouldBe Word("abcde")
            pick(10) shouldBe Word("abcde")
            pick(100) shouldBe Word("abcde")
        }
    }

    "여러개의 단어들 중 특정 위치에 존재하는 단어를 선택한다" {
        assertSoftly(Words("abcde", "hello", "world")) {
            pick(1) shouldBe Word("hello")
            pick(2) shouldBe Word("world")
            pick(3) shouldBe Word("abcde")
            pick(4) shouldBe Word("hello")
            pick(7) shouldBe Word("hello")
            pick(9) shouldBe Word("abcde")
        }
    }
})
