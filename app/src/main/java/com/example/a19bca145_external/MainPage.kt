package com.example.a19bca145_external

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.activity_viewdata.*

class MainPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        btninsert.setOnClickListener {
            if(edtcarname.text.toString().equals("")||edtcarprice.text.toString().equals("")||edtcolor.text.toString().equals(""))
            {
                Toast.makeText(this,"Enter Data Accurately", Toast.LENGTH_LONG).show()
            }
            else
            {
                btninsert.setOnClickListener {
                var name=edtcarname.text.toString()
                var price=edtcarprice.text.toString().toDouble()
                    var color = edtcolor.text.toString()
                var c= Car(name,price,color)
                var db=DBhelper(this)
                var flag=db.insert(c)
                if(flag > 0)
                {
                    Toast.makeText(this,"Record Inserted!!",
                        Toast.LENGTH_LONG).show()
                    edtcarname.setText("")
                    edtcarprice.setText("")
                    edtcolor.setText("")
                }

            }
            }
        }
        btnview.setOnClickListener {
            var view:Intent = Intent(applicationContext,Viewdata::class.java)
            startActivity(view)
            finish()

        }


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mymenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId
        if(id.equals(R.id.logoutbtn))
        {
            var sp: SharedPreferences = getSharedPreferences("MyPref", MODE_PRIVATE)
            var prefedit = sp.edit()
            prefedit.clear()
            prefedit.commit()
            var intent = Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}