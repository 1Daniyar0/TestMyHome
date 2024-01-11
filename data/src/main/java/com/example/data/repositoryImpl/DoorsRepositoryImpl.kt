package com.example.data.repositoryImpl

import android.util.Log
import com.example.data.remote.ApiClient
import com.example.data.remote.ApiRoutes
import com.example.domain.models.CamerasDataBaseModel
import com.example.domain.models.Door
import com.example.domain.models.DoorsDataBaseModel
import com.example.domain.models.DoorsResponse
import com.example.domain.repository.DoorsRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class DoorsRepositoryImpl:DoorsRepository {
    override suspend fun getDoorsList(): List<Door>? {
        val list = getDoorsFromDb()
        if (list.isEmpty()){
            try {
                fetchDoorsDataFromApi(getDoorsApi().data)
                return getDoorsApi().data
            }catch (e: Exception){
                Log.e("RepositoryImpl",e.toString())
            }
        }
        else{
            return list
        }
        return null
    }
    override suspend fun getDoorsApi(): DoorsResponse = ApiClient.client.get(ApiRoutes.DOORS_GET).body()

    override suspend fun fetchDoorsDataFromApi(data: List<Door>): Boolean {
        return try {
            val config = RealmConfiguration.create(schema = setOf(DoorsDataBaseModel::class))
            val realm: Realm = Realm.open(config)
            data.forEach{data->
                realm.writeBlocking {
                    copyToRealm(DoorsDataBaseModel().apply {
                        name = data.name.toString()
                        snapshot  = data.snapshot.toString()
                        room = data.room.toString()
                        id = data.id!!
                        favorites = data.favorites!!
                    })
                }
            }

            true
        }
        catch (e: Exception){
            Log.d("Adding doors Exception", e.toString())
            false
        }
    }

    override suspend fun updateDoorsDataInDb(door: Door) {
        val config = RealmConfiguration.create(schema = setOf(DoorsDataBaseModel::class))
        val realm: Realm = Realm.open(config)
        realm.write {
            val cameraDb = query<DoorsDataBaseModel>("id == ${door.id}").find().first()
            cameraDb.favorites = door.favorites!!
        }
    }

    override suspend fun getDoorsFromDb(): List<Door> {
        val config = RealmConfiguration.create(schema = setOf(DoorsDataBaseModel::class))
        val realm: Realm = Realm.open(config)
        val items: RealmResults<DoorsDataBaseModel> = realm.query<DoorsDataBaseModel>().find()
        val listCameras: MutableList<Door> = mutableListOf()
        items.forEach {
            listCameras.add(
                Door(
                    name = it.name,
                    snapshot = it.snapshot,
                    room = it.room,
                    id = it.id,
                    favorites = it.favorites,
                )
            )
        }
        return listCameras.reversed()
    }

}