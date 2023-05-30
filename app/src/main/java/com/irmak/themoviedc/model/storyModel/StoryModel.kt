package com.irmak.themoviedc.model.storyModel

data class StoryModel(
    val dates: StoryNP? = null,
    val page: Int? = null,
    val results: List<ResultStoryNP>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)