package wordle

data class Word(private val value: String) {

    init {
        require(value.length == 5) { "단어는 5글자만 허용합니다." }
    }

    fun match(other: Word): MatchResult {
        return other.value.toCharArray()
            .mapIndexed(::toColor)
            .let(::MatchResult)
    }

    private fun toColor(index: Int, value: Char): Color {
        return when {
            existAt(value, index) -> Color.GREEN
            contains(value) -> Color.YELLOW
            else -> Color.WHITE
        }
    }

    fun contains(char: Char): Boolean = value.contains(char)

    fun existAt(expect: Char, index: Int): Boolean {
        val actual = value.getOrNull(index) ?: throw IllegalArgumentException("존재하지 않는 단어의 인덱스입니다.")
        return actual == expect
    }
}
