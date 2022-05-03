package wordle

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldNotBeTrue
import io.kotest.matchers.shouldBe
import java.util.concurrent.atomic.AtomicLong

internal class WordleTest : StringSpec({

    "워들을 맞추면 끝난다" {
        val wordle = Wordle(Words("abcde"))

        wordle.solve(Word("abcde"))

        wordle.isEnd.shouldBeTrue()
    }

    "워들을 맞추지 못하면 끝나지 않는다" {
        val wordle = Wordle(Words("abcde"))

        wordle.solve(Word("abcdd"))

        wordle.isEnd.shouldNotBeTrue()
    }

    "워들은 6번 맞추면 종료된다" {
        val wordle = Wordle(Words("abcde"))

        wordle.solve(Word("aaaaa"))
        wordle.solve(Word("bbbbb"))
        wordle.solve(Word("ccccc"))
        wordle.solve(Word("ddddd"))
        wordle.solve(Word("eeeee"))
        wordle.solve(Word("fffff"))

        wordle.isEnd.shouldBeTrue()
    }

    "워들은 6번 이상 정답 확인을 시도하지 못한다" {
        val wordle = Wordle(Words("abcde"))

        wordle.solve(Word("aaaaa"))
        wordle.solve(Word("bbbbb"))
        wordle.solve(Word("ccccc"))
        wordle.solve(Word("ddddd"))
        wordle.solve(Word("eeeee"))
        wordle.solve(Word("fffff"))

        shouldThrow<IllegalStateException> { wordle.solve(Word("ggggg")) }
    }

    "워들을 맞췄다면 정답 확인을 시도하지 못한다" {
        val wordle = Wordle(Words("abcde"))

        wordle.solve(Word("aaaaa"))
        wordle.solve(Word("abcde"))

        shouldThrow<IllegalStateException> {
            wordle.solve(Word("ggggg"))
        }
    }

    "워들을 푸는 중 다음 문제로 변경된다면 풀지 못한다" {
        val autoIncrement = AtomicLong()
        val incrementWordPickStrategy =
            WordPickStrategy { words, _ -> words.pick(autoIncrement.getAndIncrement()) }
        val wordle = Wordle(Words("abcde", "query"), incrementWordPickStrategy)

        shouldThrow<IllegalStateException> {
            wordle.solve(Word("aaaaa"))
        }
    }

    "정답을 확인한 횟수를 확인한다" {
        val wordle = Wordle(Words("abcde"))
        for (round in 1..6) {
            wordle.solve(Word("aaaaa"))

            wordle.countAnswer shouldBe round
        }
    }

    "정답을 확인하면 정답을 맞춘 상태가 아니다" {
        val wordle = Wordle(Words("abcde"))
        wordle.solve(Word("abcde"))

        wordle.isCorrect.shouldBeTrue()
    }

    "오답을 확인하면 정답을 맞춘 상태가 아니다" {
        val wordle = Wordle(Words("abcde"))
        wordle.solve(Word("aaaaa"))

        wordle.isCorrect.shouldNotBeTrue()
    }
})
