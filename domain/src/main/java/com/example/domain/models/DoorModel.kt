package com.example.domain.models

import com.google.gson.annotations.SerializedName
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import kotlinx.serialization.Serializable
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

@Serializable
data class DoorsResponse(
    @SerializedName("success" ) var success : Boolean?        = null,
    @SerializedName("data"    ) var data    : ArrayList<Door> = arrayListOf()
)

@Serializable
data class Door(
    @SerializedName("name"      ) var name      : String?  = null,
    @SerializedName("snapshot"  ) var snapshot  : String?  = null,
    @SerializedName("room"      ) var room      : String?  = null,
    @SerializedName("id"        ) var id        : Int?     = null,
    @SerializedName("favorites" ) var favorites : Boolean? = null
)

class DoorsRealmModel: RealmObject {
    @PrimaryKey
    var _id: ObjectId = BsonObjectId()
    var name: String  = ""
    var snapshot: String  = ""
    var room: String  = ""
    var id: Int = 0
    var favorites: Boolean = false
}
