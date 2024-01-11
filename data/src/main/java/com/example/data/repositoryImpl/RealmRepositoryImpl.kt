package com.example.data.repositoryImpl

import android.util.Log
import com.example.domain.models.Camera
import com.example.domain.models.CamerasRealmModel
import com.example.domain.models.CamerasResponse
import com.example.domain.models.DoorsRealmModel
import com.example.domain.repository.RealmRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import org.mongodb.kbson.BsonObjectId

class RealmRepositoryImpl : RealmRepository {
    override suspend fun addCamerasToRealm(data: List<Camera>): Boolean {
        return try {
            val config = RealmConfiguration.create(schema = setOf(CamerasRealmModel::class))
            val realm: Realm = Realm.open(config)
            data.forEach{data->
                realm.writeBlocking {
                    copyToRealm(CamerasRealmModel().apply {
                        name = data.name.toString()
                        snapshot  = data.snapshot.toString()
                        room = data.room.toString()
                        id = data.id.toString()
                        favorites = data.favorites!!
                        rec = data.rec!!
                    })


                }
            }

            true
        }
        catch (e: Exception){
            Log.d("Adding Exception", e.toString())
            false
        }
    }

    override suspend fun updateDataInRealm(data: DoorsRealmModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getCamersFromDb(data: DoorsRealmModel):List<CamerasRealmModel> {
        val config = RealmConfiguration.create(schema = setOf(CamerasRealmModel::class))
        val realm: Realm = Realm.open(config)
        val items: RealmResults<CamerasRealmModel> = realm.query<CamerasRealmModel>().find()
        val listCameras: MutableList<CamerasRealmModel> = mutableListOf()
        items.forEach {
            listCameras.add(it)
        }
        return listCameras.reversed()
    }
}