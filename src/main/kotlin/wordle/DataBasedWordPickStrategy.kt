package wordle

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object DataBasedWordPickStrategy : WordPickStrategy {

    private val startAt = LocalDateTime.of(2021, 6, 19, 0, 0)

    override fun pick(words: Words, baseDate: LocalDateTime): Word {
        val index = ChronoUnit.DAYS.between(startAt, baseDate)
        return words.pick(index)
    }
}
