package com.example.hubtv.remote.ModelClass

data class Data(
    val content_types_id: String?,
    val custom_meta_data: CustomMetaData?,
    val episode_no: String?,
    val isFreeContent: Int?,
    val is_converted: String?,
    val is_episode: String?,
    val is_livestream_enabled: Int?,
    val is_media_published: Int?,
    val movie_stream_uniq_id: String?,
    val muvi_uniq_id: String?,
    val name: String?,
    val parent_poster_for_mobile_apps: String?,
    val parent_title: String?,
    val permalink: String?,
    val poster_url: String?,
    val preview_video_url: String?,
    val release_date: String?,
    val season_no: String?,
    val story: String?,
    val total_season: Int?,
    val video_duration: String?
)