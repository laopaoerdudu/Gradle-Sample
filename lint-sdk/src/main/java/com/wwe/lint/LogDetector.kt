package com.wwe.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement
import org.jetbrains.uast.util.isMethodCall
import java.util.*

class LogDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE = Issue.create(
            "LogUsage",
            "Log Usage",
            "Please use the unified LogUtil class!",
            Category.CORRECTNESS,
            6,
            Severity.ERROR,
            Implementation(LogDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return Collections.singletonList(UCallExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return LogHandler(context)
    }

    class LogHandler(private val context: JavaContext) : UElementHandler() {
        override fun visitCallExpression(node: UCallExpression) {
            if (!node.isMethodCall()) return

            try {
                if (node.receiver != null && !node.methodName.isNullOrBlank()) {
                    when (node.methodName) {
                        "i", "d", "e", "v", "w", "wtf" -> {
                            if (context.evaluator.isMemberInClass(
                                    node.resolve(),
                                    "android.util.Log"
                                )
                            ) {
                                reportAllocation(context, node)
                            }
                        }
                        else -> {
                            // No need to handle
                        }
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        private fun reportAllocation(context: JavaContext, node: UCallExpression) {
            context.report(
                ISSUE,
                context.getLocation(node),
                "\u21E2 请使用Logger替换Log"
            )
        }
    }
}