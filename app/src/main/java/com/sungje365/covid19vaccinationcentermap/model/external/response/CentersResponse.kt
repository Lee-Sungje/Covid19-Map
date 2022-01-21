package com.sungje365.covid19vaccinationcentermap.model.external.response

data class CentersResponse(
    val page: Long,
    val perPage: Long,
    val totalCount: Long,
    val data: List<CenterResponse>
)
