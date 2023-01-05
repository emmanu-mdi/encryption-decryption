package encryptdecrypt

fun main() {
    val message = "we found a treasure!".toMutableList()
    for (i in message.indices) {
        if (message[i] != ' ' && message[i] != '!') {
            message[i] = 'z' - (message[i] - 'a')
        }
        print(message[i])
    }
}