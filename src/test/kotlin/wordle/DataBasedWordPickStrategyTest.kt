package wordle

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

internal class DataBasedWordPickStrategyTest : StringSpec({

    "시작일에 해당하는 날짜를 뽑으면 첫 단어가 나온다" {
        val startAt = LocalDateTime.of(2021, 6, 19, 0, 0)

        val pickWord = DataBasedWordPickStrategy.pick(Words("abcde", "query", "hello"), startAt)

        pickWord shouldBe Word("abcde")
    }

    "시작 다음날에 해당하는 날짜를 뽑으면 두 번째 단어가 나온다" {
        val nextAt = LocalDateTime.of(2021, 6, 19, 0, 0)
            .plusDays(1)

        val pickWord = DataBasedWordPickStrategy.pick(Words("abcde", "query", "hello"), nextAt)

        pickWord shouldBe Word("query")
    }

    "단어의 갯수 다음날에 해당하는 날짜를 뽑으면 첫 단어가 나온다" {
        val nextAt = LocalDateTime.of(2021, 6, 19, 0, 0)
            .plusDays(3)

        val pickWord = DataBasedWordPickStrategy.pick(Words("abcde", "query", "hello"), nextAt)

        pickWord shouldBe Word("abcde")
    }
})
