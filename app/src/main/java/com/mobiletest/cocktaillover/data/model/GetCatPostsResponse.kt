package com.mobiletest.cocktaillover.data.model

import com.google.gson.annotations.SerializedName

data class GetCatPostsResponse(
    @SerializedName("data") val data: Data
) {

    data class Data(
        @SerializedName("children") val posts: List<Post>,
    ){

        data class Post(
            @SerializedName("data") val postData: PostData,
        ){

            data class PostData(
                @SerializedName("url") val imageUrl: String,
                @SerializedName("media_metadata") val mediaMetadata: Any
            )

        }

    }

}