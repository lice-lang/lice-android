package org.lice.repl

import org.lice.androlice.Printer
import org.lice.compiler.parse.buildNode
import org.lice.compiler.parse.mapAst
import org.lice.compiler.util.DEBUGGING
import org.lice.compiler.util.SymbolList
import org.lice.compiler.util.VERBOSE

/**
 * starting the read-eval-print-loop machine
 * Created by ice1000 on 2017/2/23.
 *
 * @author ice1000
 * @since 1.0.0
 */
class Repl {
	var stackTrace: Throwable? = null

	init {
		println("""Lice language repl $VERSION_CODE
			|see: https://github.com/ice1000/org.lice

			|回首向来萧瑟处,也无风雨也无晴。
			|Stay young stay simple, and make yourself naive.

			|for help please input: help
			|""".trimMargin())
		print(HINT)
		DEBUGGING = false
		VERBOSE = false
	}

	@JvmOverloads
	fun handle(
		str: String,
		symbolList: SymbolList = SymbolList(true)): Boolean {
		when (str) {
			"exit" -> {
				Printer.println("Have a nice day :)")
				return false
			}
			"pst" ->
				if (stackTrace != null) stackTrace?.printStackTrace()
				else Printer.println("No stack trace.")
			"help" -> Printer.println("""
				|This is the repl for org.lice language.

				|You have 4 special commands which you cannot use in the language but the repl:

				|exit: exit the repl
                |pst: print the most recent stack trace
                |help: print this doc
				|version: check the version""".trimMargin())
			"version" -> Printer.println("""
				|Lice language interpreter $VERSION_CODE
				|by ice1000""".trimMargin())
			else -> try {
//				Parser
//					.defaultParser(str)
//					.mapAst(symbolList)
//					.eval()
				mapAst(buildNode(str), symbolList).eval()
			} catch(e: Throwable) {
				stackTrace = e
				Printer.println(e.message ?: "")
			}
		}
		print("\n$HINT")
		return true
	}

	companion object {
		val HINT = "Lice > "
	}
}
