package wordle

private val PERFECT = MatchResult(Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN, Color.GREEN)

data class MatchResult(val value: List<Color>) {

    init {
        require(value.size == 5) { "매치 결과는 5개만 허용합니다." }
    }

    constructor(vararg value: Color) : this(value.toList())

    val isPerfect: Boolean
        get() = this == PERFECT
}
