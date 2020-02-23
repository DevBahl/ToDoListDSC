package com.dbsrm.todolistdsc

public class ToDoes {
    var titledo:String? = null

    var datedo:String? = null

    var descdo:String? = null

    var keydo:String? = null


    fun ToDoes() {}
    fun ToDoes(titledo:String, datedo:String, descdo:String, keydo:String) {
        this.titledo = titledo
        this.datedo = datedo
        this.descdo = descdo
        this.keydo = keydo
    }

    fun getTitle(): String? {
        return titledo
    }

    fun setTitle(titledo:String?) {
        this.titledo = titledo
    }

    fun getDate(): String? {
        return datedo
    }

    fun setDate(datedo:String?) {
        this.datedo = datedo
    }

    fun getDesc(): String? {
        return descdo
    }

    fun setDesc(descdo:String?) {
        this.descdo = descdo
    }

    fun getKey(): String? {
        return keydo
    }

    fun setKey(keydo:String?) {
        this.keydo = keydo
    }
}