package wordle

import java.time.LocalDateTime

class Wordle(
    private val words: Words = Resources.open("words.txt").toWords(),
    private val wordPickStrategy: WordPickStrategy = DataBasedWordPickStrategy,
) {
    private val answer: Word = wordPickStrategy.pick(words, LocalDateTime.now())

    private var inputs: Words = Words()

    val isEnd: Boolean
        get() = isCorrect || noChanceToAnswer

    val nonEnd: Boolean
        get() = !isEnd

    val countAnswer: Int
        get() = inputs.size

    val results: List<MatchResult>
        get() = inputs.match(answer)

    val isCorrect: Boolean
        get() = last?.equals(answer) ?: false

    private val noChanceToAnswer: Boolean
        get() = inputs.size == 6

    private val last: Word?
        get() = inputs.value.lastOrNull()

    fun solve(word: Word) {
        check(nonEnd) { "게임이 끝났습니다." }
        check(wordPickStrategy.pick(words, LocalDateTime.now()) == this.answer) { "문제가 바꼈습니다. 다음 게임을 시작해주세요." }

        inputs += word
    }
}
