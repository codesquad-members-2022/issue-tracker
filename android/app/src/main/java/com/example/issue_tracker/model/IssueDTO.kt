package com.example.issue_tracker.model

import com.example.issue_tracker.model.LabelDTO.Companion.toLabel
import com.google.gson.annotations.SerializedName

data class IssueDTO(
    @SerializedName("issues")
    val issues: List<IssueDTOItem>,
)

data class LabelListDTO(
    @SerializedName("labels")
    val labels: List<LabelDTO>,
)

data class IssueDTOItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("issueId")
    val issueId: Int,
    @SerializedName("label")
    val label: LabelDTO,
    @SerializedName("milestoneTitle")
    val milestoneTitle: String,
    @SerializedName("title")
    val title: String,
)

data class LabelDTO(
    @SerializedName("backgroundColor")
    val backgroundColor: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
) {
    companion object {
        fun LabelDTO.toLabel(): Label {
            val parsedColorString = this.backgroundColor.substring(2)
            val color = buildString {
                append("#")
                append("FF")
                append(parsedColorString)
            }

            return Label(
                labelId = requireNotNull(this.id),
                labelTitle = requireNotNull(this.name),
                labelColor = color,
                labelContents = this.description
            )
        }
    }
}

fun List<IssueDTOItem>.toClientIssue(): MutableList<Issue> {
    return this.map { issueDTOItem ->
        val issueId = requireNotNull(issueDTOItem.issueId)
        val mileStone = issueDTOItem.milestoneTitle
        val title = requireNotNull(issueDTOItem.title)
        val contents = issueDTOItem.description
        val label = issueDTOItem.label.toLabel()
        Issue(
            issueId = issueId,
            mileStone = mileStone,
            title = title,
            contents = contents,
            label = label
        )
    }.toMutableList()
}

fun List<LabelDTO>.toLabelList(): MutableList<Label> =
    this.map { labelDTO ->
        labelDTO.toLabel()
    }.toMutableList()