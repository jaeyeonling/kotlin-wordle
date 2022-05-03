package wordle

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue

internal class MatchResultTest : StringSpec({

    "매치 결과는 색 5개로 생성할 수 있다" {
        shouldNotThrowAny { MatchResult(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN) }
    }

    "매치 결과는 색 5개 보다 작게 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> { MatchResult(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN) }
    }

    "매치 결과는 색 5자 보다 크게 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            MatchResult(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN)
        }
    }

    "모든 색이 GREEN이면 완벽한 상태다" {
        val allGreen = MatchResult(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN)

        allGreen.isPerfect.shouldBeTrue()
    }
})
