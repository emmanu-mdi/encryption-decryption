package encryptdecrypt

fun main() {
    val message = readln().toMutableList()
    val key = readln().toInt()
    for (i in message.indices) {
        if (message[i] in 'a'..'z') {
            message[i] = message[i] + key
            if (message[i] > 'z') message[i] = 'a' + (message[i] - 'z' - 1)
        }
        print(message[i])
    }
}
