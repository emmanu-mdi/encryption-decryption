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

fun shiftEncFile(message: MutableList<Char>, key: Int, file: String) {
    val fileOut = File(file)
    for( i in message.indices) {
        if (message[i] in 'A'..'Z') {
            if ((message[i] + key) > 'Z') {
                message[i] = 'A' + (key - ('Z' - message[i]) - 1)
            } else {
                message[i] = message[i] + key
            }
        } else if (message[i] in 'a'..'z') {
            if ((message[i] + key) > 'z') {
                message[i] = 'a' + (key - ('z' - message[i]) -1)
            } else {
                message[i] = message[i] + key
            }
        } else {
            message[i] = message[i]
        }
        fileOut.appendText(message[i].toString())
    }
}

fun shiftDecFile(message: MutableList<Char>, key: Int, file: String) {
    val fileOut = File(file)
    for (i in message.indices) {
        if (message[i] in 'A'..'Z') {
            if ((message[i] - key) < 'A') {
                message[i] = 'Z' - (key - (message[i] + 1 - 'A'))
            } else {
                message[i] = message[i] - key
            }
        } else if (message[i] in 'a'..'z'){
            if ((message[i] - key) < 'a') {
                message[i] = 'z' - (key - (message[i] + 1 - 'a'))
            } else {
                message[i] = message[i] - key
            }
        } else {
            message[i] = message[i]
        }
        fileOut.appendText(message[i].toString())
    }
}


fun shiftEncrypt(message: MutableList<Char>, key: Int, file: String) {
    val fileOut = File(file)
    if (file.isEmpty()) {
        for( i in message.indices) {
            if (message[i] in 'A'..'Z') {
                if ((message[i] + key) > 'Z') {
                        message[i] = 'A' + (key - ('Z' - message[i]) - 1)
                } else {
                    message[i] = message[i] + key
                }
            } else if (message[i] in 'a'..'z') {
                if ((message[i] + key) > 'z') {
                    message[i] = 'a' + (key - ('z' - message[i]) -1)
                } else {
                    message[i] = message[i] + key
                }
            } else {
                message[i] = message[i]
            }
            print(message[i])
        }
    } else if (fileOut.exists()) {
        fileOut.writeText("")
        shiftEncFile(message, key, file)
    } else {
        fileOut.createNewFile()
        fileOut.writeText("")
        shiftEncFile(message, key, file)
    }
}

fun shiftDecrypt(message: MutableList<Char>, key: Int, file: String) {
    val fileOut = File(file)
    if (file.isEmpty()) {
        for (i in message.indices) {
            if (message[i] in 'A'..'Z') {
                if ((message[i] - key) < 'A') {
                    message[i] = 'Z' - (key - (message[i] + 1 - 'A'))
                } else {
                    message[i] = message[i] - key
                }
            } else if (message[i] in 'a'..'z'){
                if ((message[i] - key) < 'a') {
                    message[i] = 'z' - (key - (message[i] + 1 - 'a'))
                } else {
                    message[i] = message[i] - key
                }
            } else {
                message[i] = message[i]
            }
            print(message[i])
        }

    } else if (fileOut.exists()) {
        fileOut.writeText("")
        shiftDecFile(message, key, file)
    } else {
        fileOut.createNewFile()
        fileOut.writeText("")
        shiftDecFile(message, key, file)
    }
}

fun main(args: Array<String>) {
    var mode = "enc"
    var key = 0
    var data = "".toMutableList()
    var fileIn = ""
    var fileOut = ""
    var alg = "shift"

    try {
        for (i in args.indices) {
            when(args[i]){
                "-mode" -> mode = args[i + 1]
                "-key" -> key = args[i + 1].toInt()
                "-data" -> data = args[i + 1].toMutableList()
                "-in" -> fileIn = args[i + 1]
                "-out" -> fileOut = args[i + 1]
                "-alg" -> alg = args[i + 1]
            }
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }


    if (data.isEmpty()) {
        val file = File(fileIn)
        if (file.exists()) data = file.readText().toMutableList()
    }

    when(alg) {
        "shift" -> {
            when(mode) {
                "enc" -> shiftEncrypt(data, key, fileOut)
                "dec" -> shiftDecrypt(data, key, fileOut)
            }
        }
        "unicode" -> {
            when(mode) {
                "enc" -> encrypt(data, key, fileOut)
                "dec" -> decrypt(data, key, fileOut)
            }
        }
    }

}
