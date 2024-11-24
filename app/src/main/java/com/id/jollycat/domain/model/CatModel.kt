package com.id.jollycat.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatModel(
    val name: String,
    val description: String,
    val price: Int,
    val imageURL: String,
): Parcelable {
    companion object {
        val dummyCats = listOf(
            CatModel("Whiskers", "A playful kitten that loves to chase laser pointers.", 150, "image_url_1"),
            CatModel("Mittens", "A calm and affectionate cat with a knack for cuddles.", 200, "image_url_2"),
            CatModel("Shadow", "A mysterious black cat who enjoys quiet places.", 180, "image_url_3"),
            CatModel("Tiger", "A fierce-looking but gentle tabby cat.", 220, "image_url_4"),
            CatModel("Smokey", "A gray cat with a love for napping in sunny spots.", 170, "image_url_5"),
            CatModel("Luna", "A curious cat who loves exploring new places.", 190, "image_url_6"),
            CatModel("Bella", "An elegant cat with a soft, fluffy coat.", 210, "image_url_7"),
            CatModel("Oliver", "A friendly cat who gets along with everyone.", 160, "image_url_8"),
            CatModel("Chloe", "A playful cat who loves to play with toys.", 180, "image_url_9"),
            CatModel("Max", "A strong and energetic cat who loves to climb.", 200, "image_url_10")
        )

    }
}
