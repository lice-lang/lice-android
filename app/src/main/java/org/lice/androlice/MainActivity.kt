package org.lice.androlice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.lice.compiler.util.SymbolList
import org.lice.core.*
import org.lice.repl.Repl

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val sl = SymbolList(false)
		sl.run {
			addFileFunctions()
			addMathFunctions()
			addStringFunctions()
			addConcurrentFunctions()
			addStandard()
		}
		Printer.printer = { output_main.append(it) }
		val repl = Repl()
		eval_main.setOnClickListener { _ ->
			repl.handle(input_main.text.toString(), sl)
			input_main.text.clear()
		}
	}
}
