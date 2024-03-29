package com.example.domain.models



import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable


@Serializable
data class CamerasResponse(
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("data"    ) var data    : Data?    = Data()
)

@Serializable
data class Data (

    @SerializedName("room"    ) var room    : ArrayList<String>  = arrayListOf(),
    @SerializedName("cameras" ) var cameras : ArrayList<Camera> = arrayListOf()

)

@Serializable
data class Camera (

    @SerializedName("name"      ) var name      : String?  = null,
    @SerializedName("snapshot"  ) var snapshot  : String?  = null,
    @SerializedName("room"      ) var room      : String?  = null,
    @SerializedName("id"        ) var id        : Int?     = null,
    @SerializedName("favorites" ) var favorites : Boolean? = null,
    @SerializedName("rec"       ) var rec       : Boolean? = null

)

class CamerasDataBaseModel(): RealmObject {
    @PrimaryKey
    var id: Int = 0
    var name: String  = ""
    var snapshot: String  = ""
    var room: String  = ""
    var favorites: Boolean = false
    var rec: Boolean = false
}



