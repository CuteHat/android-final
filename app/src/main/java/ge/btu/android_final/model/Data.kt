package ge.btu.android_final.model

import com.google.gson.annotations.SerializedName

class Data(
    @SerializedName("id")
    var id: Int,
    @SerializedName("dishName_EN")
    var dishnameEn: String,
    @SerializedName("dishName_GE")
    var dishnameGe: String,
    @SerializedName("dish_imgURL")
    var dishImageUrl: String,
    @SerializedName("locations")
    var locations: List<Location>
)