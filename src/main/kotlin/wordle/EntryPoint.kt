package wordle

fun main() {
    OutputView.printRule()

    val wordle = Wordle()
    while (wordle.nonEnd) {
        OutputView.printNewLine()

        val word = Word(InputView.readAnswer())
        wordle.solve(word)

        OutputView.print(wordle)
    }
}
