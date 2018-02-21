package com.creditmantri.apiResponse

import com.creditmantri.CloudsModel
import com.creditmantri.MainModel
import com.creditmantri.WhetherInner
import com.creditmantri.WindModel

/**
 * Created by Mani on 20-02-2018.
 */
public class WhetherModel{
    lateinit var dt_txt:String
    lateinit var main:MainModel

    lateinit var clouds:CloudsModel
    lateinit var wind:WindModel

    internal lateinit var weather: ArrayList<WhetherInner>
}