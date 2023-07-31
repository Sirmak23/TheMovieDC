package com.irmak.themoviedc.model.storyModel

data class StoryModel(
    val dates: com.irmak.themoviedc.model.storyModel.StoryNP? = null,
    val page: Int? = null,
    val results: List<com.irmak.themoviedc.model.storyModel.ResultStoryNP>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)