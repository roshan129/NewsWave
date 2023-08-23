package com.roshanadke.dashboard.data.dto

import com.roshanadke.dashboard.domain.model.Source

data class SourceDto(
    val id: String,
    val name: String
) {
    fun toSource(): Source {
        return Source(
            id = id,
            name = name,
        )
    }
}