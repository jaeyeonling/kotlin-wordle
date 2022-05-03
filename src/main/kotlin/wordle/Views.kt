package wordle

object InputView {

    fun readAnswer(): String {
        OutputView.printAnswer()

        return readlnOrNull() ?: throw NoSuchElementException("입력받은 값이 없습니다.")
    }
}

object OutputView {

    fun printRule() {
        println("WORDLE을 6번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printAnswer() = println("정답을 입력해 주세요.")

    fun printNewLine() = println()

    fun print(wordle: Wordle) {
        if (wordle.isCorrect) {
            printNewLine()
            println("${wordle.countAnswer} / 6")
        }

        wordle.results
            .forEach {
                print(it)
                printNewLine()
            }
    }

    fun print(matchResult: MatchResult) {
        matchResult.value.forEach(::print)
    }

    fun print(color: Color) {
        val symbol = when (color) {
            Color.GREEN -> "\uD83D\uDFE9"
            Color.YELLOW -> "\uD83D\uDFE8"
            Color.WHITE -> "⬜"
        }
        print(symbol)
    }
}
