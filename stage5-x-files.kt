package encryptdecrypt

import java.io.File

fun encrypt(message: MutableList<Char>, key: Int, file: String) {
    val fileOut = File(file)
    if (file.isEmpty()) {
        for (i in message.indices) {
            message[i] = message[i] + key
            print(message[i])
        }
    } else if (fileOut.exists()) {
        fileOut.writeText("")
        message.forEach {
            fileOut.appendText((it + key).toString())
        }
    } else {
        fileOut.createNewFile()
        fileOut.writeText("")
        message.forEach {
            fileOut.appendText((it + key).toString())
        }
    }
}

fun decrypt(message: MutableList<Char>, key:Int, file: String) {
    val fileOut = File(file)
    if (file.isEmpty()) {
        for (i in message.indices) {
            message[i] = message[i] - key
            print(message[i])
        }
    } else if (fileOut.exists()) {
        fileOut.writeText("")
        message.forEach {
            fileOut.appendText((it - key).toString())
        }
    } else {
        fileOut.createNewFile()
        fileOut.writeText("")
        message.forEach {
            fileOut.appendText((it - key).toString())
        }
    }
}

fun main(args: Array<String>) {
    var mode = "enc"
    var key = 0
    var data = "".toMutableList()
    var fileIn = ""
    var fileOut = ""

    for (i in args.indices) {
        when(args[i]){
            "-mode" -> mode = args[i + 1]
            "-key" -> key = args[i + 1].toInt()
            "-data" -> data = args[i + 1].toMutableList()
            "-in" -> fileIn = args[i + 1]
            "-out" -> fileOut = args[i + 1]
        }
    }

    if (data.isEmpty()) {
        val file = File(fileIn)
        if (file.exists()) data = file.readText().toMutableList()
    }

    when(mode){
        "enc" -> encrypt(data, key, fileOut)
        "dec" -> decrypt(data, key, fileOut)
    }
}
