package com.prolificinteractive.simcoe

private val WRITE_OUT_FORMAT = "[%s] %s"

/**
 * Defines an object as an output source.
 */
interface Output {
    /**
     * Prints a message to the output source.
     *
     * @param message The message to print.
     */
    fun print(message: String)

}

/**
 * Prints a message with a name to the output source.
 *
 * @param name The name to print.
 * @param message The message to print.
 */
fun Output.writeOut(name: String, message: String) {
    print(String.format(WRITE_OUT_FORMAT, name, message))
}

/**
 * Prints a message with a name to the output sources.
 *
 * @param name The name to print.
 * @param message The message to print.
 */
fun Array<out Output>.writeOut(name: String, message: String) {
    forEach { it.writeOut(name, message) }
}

