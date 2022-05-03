package wordle

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.file.shouldExist
import io.kotest.matchers.file.shouldNotExist

internal class ResourcesTest : StringSpec({

    "존재하는 파일을 열면 존재한다" {
        val existsFile = Resources.open("words.txt")

        existsFile.shouldExist()
    }

    "존재하지 않은 파일을 열면 존재하지 않는다" {
        val notExistsFile = Resources.open("not-exists.txt")

        notExistsFile.shouldNotExist()
    }
})
