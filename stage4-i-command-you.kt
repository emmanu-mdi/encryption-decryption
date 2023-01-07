package encryptdecrypt

fun encrypt(message: MutableList<Char>, key: Int): String {
    for (i in message.indices) {
        message[i] = message[i] + key
        print(message[i])
    }
    println()
    return message.toString()
}

fun decrypt(message: MutableList<Char>, key:Int): String {
    for (i in message.indices) {
        message[i] = message[i] - key
        print(message[i])
    }
    println()
    return message.toString()
}

fun main(args: Array<String>) {
    var mode = "enc"
    var key = 0
    var data = "".toMutableList()
    for (i in args.indices) {
        when(args[i]){
            "-mode" -> {
                mode = if (args[i + 1] == "dec") "dec" else "enc"
            }
            "-key" -> key = args[i + 1].toInt()
            "-data" -> data = args[i + 1].toMutableList()
        }
    }
    if (mode == "enc") encrypt(data, key) else decrypt(data, key)
}
