package com.wwe.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

class IssueRegistryRepository : IssueRegistry() {

    override val issues: List<Issue>
        get() {
            return mutableListOf(
                LogDetector.ISSUE
            )
        }
}