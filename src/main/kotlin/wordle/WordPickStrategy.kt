package wordle

import java.time.LocalDateTime

fun interface WordPickStrategy {

    fun pick(words: Words, baseDate: LocalDateTime): Word
}
