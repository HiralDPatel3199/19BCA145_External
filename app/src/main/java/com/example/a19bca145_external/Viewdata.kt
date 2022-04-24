package com.example.a19bca145_external

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_viewdata.*
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.viewdatarecycle.*

class Viewdata : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewdata)
       refreshRecycler()
    }
    fun refreshRecycler()
    {
        var db=DBhelper(this)
        var arr:ArrayList<Car> = db.retriveAll()
        var adapter=caradapter(this,arr)
        recycleviewdata.adapter=adapter
    }
    fun delete(position:Int)
    {
        var db=DBhelper(this)
        var arr:ArrayList<Car> = db.retriveAll()
        var car=arr.get(position)
        db.delete(car.id)
        refreshRecycler()
    }
    fun update(position: Int)
    {
        var db=DBhelper(this)
        var arr:ArrayList<Car> = db.retriveAll()
        var car=arr.get(position)
        var dialog= Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        dialog.txtUpadateId.setText(car.id.toString())
        dialog.edtUpadateName.setText(car.cname)
        dialog.edtUpdateprice.setText(car.cprice.toString())
        dialog.edtUpdatecolor.setText(car.ccolor.toString())
        dialog.btnUpdate.setOnClickListener {
            var id=dialog.txtUpadateId.text.toString().toInt()
            var name=dialog.edtUpadateName.text.toString()
            var price=dialog.edtUpdateprice.text.toString().toDouble()
            var color =dialog.edtUpdatecolor.text.toString()
            var c=Car(id,name,price,color)
            db.update(c)
            dialog.dismiss()
            refreshRecycler()
        }
        dialog.show()
    }
}