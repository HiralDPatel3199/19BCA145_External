package com.example.a19bca145_external

import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.android.synthetic.main.custom_dialog.*

class DBhelper (var context: Context) : SQLiteOpenHelper(context, DB_NAME,null, DB_VER) {
    companion object{
        private val DB_NAME = "PracticeDB"
        private val TB_USER = "User"
        private val TB_CAR = "Car"
        private val DB_VER = 1

        private val ID_USER = "U_Id"
        private val PASSWORD_USER = "U_Password"
        private val EMAIL_USER = "U_Email"
        private val AGE_USER = "U_Age"

        private val ID_CAR = "c_id"
        private val NAME_CAR = "c_name"
        private val PRICE_CAR = "c_price"
        private val COLOR_CAR = "c_color"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        var sql1 = "CREATE TABLE $TB_USER ($ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, $PASSWORD_USER TEXT, $EMAIL_USER TEXT, $AGE_USER INTEGER)"
        var sql2 = "CREATE TABLE $TB_CAR ($ID_CAR INTEGER PRIMARY KEY AUTOINCREMENT, $NAME_CAR TEXT, $PRICE_CAR DOUBLE,$COLOR_CAR TEXT)"

        p0?.execSQL(sql1)
        p0?.execSQL(sql2)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insertUser(us : User): Long
    {
        var db = writableDatabase
        var cn = ContentValues()
        cn.put(EMAIL_USER,us.email)
        cn.put(AGE_USER,us.age)
        cn.put(PASSWORD_USER, us.password)

        var res = db.insert(TB_USER,null,cn)
        return res
        db.close()
    }
    fun getUser(uname:String): ArrayList<User>
    {
        var db = readableDatabase
        var sql = "Select * from $TB_USER where $EMAIL_USER = '$uname'"
        var arr = ArrayList<User>()
        var cursor = db.rawQuery(sql,null)
        while(cursor.moveToNext())
        {
            var id = cursor.getInt(0)
            var password = cursor.getString(1)
            var email = cursor.getString(2)
            var age = cursor.getInt(3)

            var us = User(id,password,email,age)

            arr.add(us)
        }
        return arr
        db.close()
    }
    fun insert(c:Car):Long
    {
        var db=writableDatabase
        var cv=ContentValues()
        cv.put(NAME_CAR,c.cname)
        cv.put(PRICE_CAR,c.cprice)
        cv.put(COLOR_CAR,c.ccolor)
        var flag = db.insert(TB_CAR,null,cv)
        return flag
    }
    fun retriveAll():ArrayList<Car>
    {
        var arr=ArrayList<Car>()
        var db=readableDatabase
        var cursor=db.query(TB_CAR,null,null,null,null,null,null)
        while(cursor.moveToNext())
        {

            var id=cursor.getInt(0)
            var name=cursor.getString(1)
            var price=cursor.getDouble(2)
            var color=cursor.getString(3)
            var c=Car(id,name,price,color)
            arr.add(c)
        }
        return arr

    }
    fun delete(id:Int)
    {
        var db=writableDatabase
        db.delete(TB_CAR,"$ID_CAR=$id",null)
        db.close()
    }

    fun update(c:Car)
    {
        var db=writableDatabase
        var cv=ContentValues()
        cv.put(NAME_CAR,c.cname)
        cv.put(PRICE_CAR,c.cprice)
        cv.put(COLOR_CAR,c.ccolor)
        var flag=db.update(TB_CAR,cv,"$ID_CAR=${c.id}",
            null)
        db.close()
    }
}



