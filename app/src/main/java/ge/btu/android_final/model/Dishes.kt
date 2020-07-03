package ge.btu.android_final.model

import com.google.gson.annotations.SerializedName

class Dishes(
    @SerializedName("success")
    var success: Boolean,
    @SerializedName("data")
    var data: List<Data>
)
