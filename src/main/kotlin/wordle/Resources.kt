package wordle

import java.io.File

object Resources {

    fun open(fileName: String): File =
        object {}.javaClass.classLoader.getResource(fileName)?.let { File(it.toURI()) } ?: File(fileName)
}
