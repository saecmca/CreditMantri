package com.creditmantri.apiResponse

/**
 * Created by Mani on 20-02-2018.
 */
 class WheatherResp{
    lateinit var  name:String
    internal lateinit var list: ArrayList<WhetherModel>
    fun getList(): java.util.ArrayList<WhetherModel> {
        return list
    }

    fun setList(list: java.util.ArrayList<WhetherModel>) {
        this.list
    }
}