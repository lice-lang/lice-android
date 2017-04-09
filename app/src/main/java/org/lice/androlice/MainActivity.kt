package org.lice.androlice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.lice.compiler.util.SymbolList
import org.lice.core.*
import org.lice.repl.Repl
import java.io.OutputStream
import java.io.PrintStream

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
		val out = PrintStream(object : OutputStream() {
			override fun write(oneByte: Int) = output_main.append(oneByte.toChar().toString())
			override fun write(buffer: ByteArray?) = output_main.append(String(buffer ?: byteArrayOf()))
		})
		System.setOut(out)
		System.setErr(out)
		val repl = Repl()
		eval_main.setOnClickListener { _ ->
			repl.handle(input_main.text.toString(), sl)
			input_main.text.clear()
		}
	}
}
