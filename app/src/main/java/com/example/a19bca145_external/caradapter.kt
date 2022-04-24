package com.example.a19bca145_external

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.viewdatarecycle.view.*

class caradapter(val context: Context, var arr:ArrayList<Car>)
    : RecyclerView.Adapter<caradapter.PersonViewHolde>()

{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolde {
        var inflater= LayoutInflater.from(parent.context)
        var view= inflater.inflate(R.layout.viewdatarecycle,parent,false)
        return PersonViewHolde(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolde, position: Int) {
        holder.bind(arr[position])
        holder.itemView.btndelete.setOnClickListener {
            if(context is Viewdata)
            {
                context.delete(position)
            }
        }

        holder.itemView.btnupdate.setOnClickListener {
            if(context is Viewdata)
            {
                context.update(position)
            }
        }


    }

    override fun getItemCount(): Int {
        return  arr.size
    }

    class PersonViewHolde(var view: View):RecyclerView.ViewHolder(view)
    {
        fun bind(c:Car)
        {
            view.txtid.setText(c.id.toString())
            view.txtname.setText(c.cname)
            view.txtprice.setText(c.cprice.toString())
            view.txtcolor.setText(c.ccolor.toString())

        }
    }
}