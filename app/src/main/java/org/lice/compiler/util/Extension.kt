/**
 * Created by ice1000 on 2017/2/16.
 *
 * @author ice1000
 * @since 1.0.0
 */
@file:JvmName("Utilities")
@file:JvmMultifileClass

package org.lice.compiler.util

var DEBUGGING = true

var VERBOSE = true

inline fun forceRun(block: () -> Unit) {
	try {
		block()
	} catch (e: Throwable) {
	}
}
