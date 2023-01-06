package encryptdecrypt

fun encrypt(message: MutableList<Char>, key: Int) {
    for (i in message.indices) {
        message[i] = message[i] + key
        print(message[i])
    }
}

fun decrypt(message: MutableList<Char>, key:Int) {
    for (i in message.indices) {
        message[i] = message[i] - key
        print(message[i])
    }
}

fun main() {
    val choice = readln()
    val message = readln().toMutableList()
    val key = readln().toInt()
    when(choice){
        "enc" -> encrypt(message, key)
        "dec" -> decrypt(message, key)
    }
}