package ge.btu.android_final.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Location(
    @SerializedName("id")
    var id: Int,
    @SerializedName("dish_id")
    var dishId: Int,
    @SerializedName("locationName_EN")
    var locationNameEn: String,
    @SerializedName("locationName_GE")
    var locationNameGe: String,
    @SerializedName("location_EN")
    var locationEn: String,
    @SerializedName("location_GE")
    var locationGe: String,
    @SerializedName("last_modify_date")
    var lastModifyDate: Date
) : Parcelable