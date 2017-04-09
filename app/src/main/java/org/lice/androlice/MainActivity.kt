package org.lice.androlice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.lice.Lice
import org.lice.compiler.model.Node.Objects.getNullNode
import org.lice.compiler.model.ValueNode
import org.lice.compiler.util.SymbolList
import org.lice.compiler.util.serr
import org.lice.core.*
import java.io.File

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
			defineFunction("load", { ln, ls ->
				ls.forEach { node ->
					val res = node.eval()
					val res_o = res.o
					if (res_o is String) {
						if (res_o in loadedModules)
							return@defineFunction ValueNode(false, ln)
						loadedModules.add(res_o)
						val file = File(res_o)
						when {
							file.exists() -> Lice.run(file, this)
							else -> {
								serr("$res_o not found!")
								return@defineFunction getNullNode(ln)
							}
						}
					}
				}
				ValueNode(true, ln)
			})
		}
		output_main

	}
}
