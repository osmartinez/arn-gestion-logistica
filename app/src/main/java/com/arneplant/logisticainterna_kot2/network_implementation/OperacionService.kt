package com.arneplant.logisticainterna_kot2.network_implementation

import com.arneplant.logisticainterna_kot2.model.Labor
import com.arneplant.logisticainterna_kot2.network.RetrofitInstance
import com.arneplant.logisticainterna_kot2.network_ifaces.IOperacionService
import retrofit2.Call

class OperacionService:IOperacionService {
    private var service: IOperacionService = RetrofitInstance.getRetrofitInstance()!!.create(
        IOperacionService::class.java)
    override fun findLabores(): Call<List<Labor>> {
        return service.findLabores()
    }
}