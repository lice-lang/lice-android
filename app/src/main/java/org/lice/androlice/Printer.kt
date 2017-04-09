package org.lice.androlice

/**
 * Created by ice1000 on 2017/4/10.
 *
 * @author ice1000
 */

object Printer {
	var printer: ((String) -> Unit)? = null

	fun print(string: Any?) {
		printer?.invoke(string.toString())
	}

	fun println(string: Any?) {
		print("$string\n")
	}
}
