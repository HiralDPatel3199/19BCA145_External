package com.example.a19bca145_external

class Car(var cname:String,var cprice:Double, var ccolor:String) {
    var id = 0
    constructor(id:Int,cname: String,cprice: Double,ccolor: String):this(cname,cprice,ccolor)
    {
        this.id = id
    }
}