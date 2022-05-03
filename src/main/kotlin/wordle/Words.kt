package wordle

import java.io.File

data class Words(val value: List<Word> = emptyList()) {

    constructor(vararg value: String) : this(value.map(::Word))

    constructor(vararg value: Word) : this(value.toList())

    val size: Int
        get() = value.size

    fun pick(index: Long): Word = this[index % value.size]

    private operator fun get(index: Long): Word =
        value.getOrNull(index.toInt()) ?: throw IllegalArgumentException("존재하지 않는 단어의 인덱스입니다.")

    fun match(word: Word): List<MatchResult> {
        return value.map(word::match).toList()
    }

    operator fun plus(word: Word): Words = Words(value + word)
}

fun File.toWords(): Words {
    require(exists()) { "$name 파일이 존재하지 않습니다." }

    return Words(readLines().map(::Word))
}
