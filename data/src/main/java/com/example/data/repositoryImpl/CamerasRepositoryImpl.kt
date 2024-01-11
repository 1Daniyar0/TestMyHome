package com.example.data.repositoryImpl

import android.util.Log
import com.example.data.remote.ApiClient
import com.example.data.remote.ApiRoutes
import com.example.domain.models.Camera
import com.example.domain.models.CamerasDataBaseModel
import com.example.domain.models.CamerasResponse
import com.example.domain.repository.CamerasRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults


class CamerasRepositoryImpl : CamerasRepository {
    override suspend fun getCamerasList(): List<Camera>? {
        val list = getCamerasFromDb()
        if (list.isEmpty()){
            try {
                fetchCamerasDataFromApi(getCamerasApi().data!!.cameras)
                return getCamerasApi().data!!.cameras
            }catch (e: Exception){
                Log.e("RepositoryImpl",e.toString())
            }
        }
        else{
            return list
        }
        return null
    }

    override suspend fun getCamerasApi(): CamerasResponse = ApiClient.client.get(ApiRoutes.CAMERAS_GET).body()


    override suspend fun fetchCamerasDataFromApi(data: List<Camera>): Boolean {
        return try {
            val config = RealmConfiguration.create(schema = setOf(CamerasDataBaseModel::class))
            val realm: Realm = Realm.open(config)
            data.forEach{data->
                realm.writeBlocking {
                    copyToRealm(CamerasDataBaseModel().apply {
                        name = data.name.toString()
                        snapshot  = data.snapshot.toString()
                        room = data.room.toString()
                        id = data.id!!
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

    override suspend fun updateCamerasDataDb(camera: Camera) {
        val config = RealmConfiguration.create(schema = setOf(CamerasDataBaseModel::class))
        val realm: Realm = Realm.open(config)
        realm.write {
            val cameraDb = query<CamerasDataBaseModel>("id == ${camera.id}").find().first()
            cameraDb.favorites = camera.favorites!!
        }
    }

    override suspend fun getCamerasFromDb():List<Camera> {
        val config = RealmConfiguration.create(schema = setOf(CamerasDataBaseModel::class))
        val realm: Realm = Realm.open(config)
        val items: RealmResults<CamerasDataBaseModel> = realm.query<CamerasDataBaseModel>().find()
        val listCameras: MutableList<Camera> = mutableListOf()
        items.forEach {
            listCameras.add(
                Camera(
                    name = it.name,
                    snapshot = it.snapshot,
                    room = it.room,
                    id = it.id,
                    favorites = it.favorites,
                    rec = it.rec
                )
            )
        }
        return listCameras.reversed()
    }


}