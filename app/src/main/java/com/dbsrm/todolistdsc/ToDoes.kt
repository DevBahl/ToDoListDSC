package com.dbsrm.todolistdsc

public class ToDoes {
    var titledoes:String? = null

    var datedoes:String? = null

    var descdoes:String? = null

    var keydoes:String? = null

    var name:String?=null


    fun ToDoes(titledoes:String, datedoes:String, descdoes:String, keydoes:String,name:String) {
        this.titledoes = titledoes
        this.datedoes = datedoes
        this.descdoes = descdoes
        this.keydoes = keydoes
        this.name=name
    }

    fun getTitle(): String? {
        return titledoes
    }

    fun setTitle(titledoes:String?) {
        this.titledoes = titledoes
    }

    fun getDate(): String? {
        return datedoes
    }

    fun setDate(datedoes:String?) {
        this.datedoes = datedoes
    }

    fun getDesc(): String? {
        return descdoes
    }

    fun setDesc(descdoes:String?) {
        this.descdoes = descdoes
    }

    fun getKey(): String? {
        return keydoes
    }

    fun setKey(keydoes:String?) {
        this.keydoes = keydoes
    }

    fun getUname():String?{
        return name
    }
}