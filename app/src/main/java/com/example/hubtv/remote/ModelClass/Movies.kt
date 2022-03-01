package com.example.hubtv.remote.ModelClass

data class Movies(
    val banner_section_list: List<BannerSection>?,
    val banner_text: String?,
    val code: Int?,
    val is_featured: Int?,
    val msg: String?,
    val section_count: Int?,
    val section_name: List<SectionName>?,
    val status: String?
)