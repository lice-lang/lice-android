package org.lice.androlice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import org.lice.core.*
import org.lice.lang.Echoer
import org.lice.repl.Repl

class MainActivity : AppCompatActivity() {

	lateinit var repl: Repl

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
		repl = Repl(sl)
		eval_main.setOnClickListener { _ ->
			val txt = input_main.text.toString()
			Echoer.echoln(txt)
			repl.handle(txt)
			input_main.text.clear()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		var index = 0
		menu?.run {
			repl.symbolList.functions.forEach {
				add(0, 0, index++, it.key)
			}
		}
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		input_main.text.append(item?.title ?: "")
		return null != item
	}
}
