package org.lice.androlice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.lice.core.*
import org.lice.lang.Echoer
import org.lice.repl.Repl

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		Echoer.closeOutput()
		val sl = SymbolList(false)
		sl.run {
			addFileFunctions()
			addMathFunctions()
			addStringFunctions()
			addConcurrentFunctions()
			addStandard()
		}
		Echoer.printer = { output_main.append(toString()) }
		Echoer.printerErr = { output_main.append(toString()) }
		val repl = Repl(sl)
		eval_main.setOnClickListener { _ ->
			val txt = input_main.text.toString()
			Echoer.echoln(txt)
			repl.handle(txt)
			input_main.text.clear()
		}
	}
}
