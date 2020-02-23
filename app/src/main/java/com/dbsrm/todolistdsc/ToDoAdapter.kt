package com.dbsrm.todolistdsc

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(c: Context, p:ArrayList<ToDoes>): RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {
    internal var context:Context
    internal var toDoes:ArrayList<ToDoes>
    init{
        context = c
        toDoes = p
    }
    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i:Int):MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_do, viewGroup, false))
    }
    override fun onBindViewHolder(@NonNull myViewHolder:MyViewHolder, i:Int) {
        myViewHolder.titledo.setText(toDoes.get(i).getTitle())
        myViewHolder.descdo.setText(toDoes.get(i).getDesc())
        myViewHolder.datedo.setText(toDoes.get(i).getDate())
        val getTitleDoes = toDoes.get(i).getTitle()
        val getDescDoes = toDoes.get(i).getDesc()
        val getDateDoes = toDoes.get(i).getDate()
        val getKeyDoes = toDoes.get(i).getKey()
        myViewHolder.itemView.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View) {
                val aa = Intent(context, EditToDo::class.java)
                aa.putExtra("titledoes", getTitleDoes)
                aa.putExtra("descdoes", getDescDoes)
                aa.putExtra("datedoes", getDateDoes)
                aa.putExtra("keydoes", getKeyDoes)
                context.startActivity(aa)
            }
        })
    }
    inner class MyViewHolder(@NonNull itemView:View):RecyclerView.ViewHolder(itemView) {
        var titledo:TextView
        var descdo:TextView
        var datedo:TextView
        lateinit var keydo:TextView
        init{
            titledo = itemView.findViewById(R.id.titledo) as TextView
            descdo = itemView.findViewById(R.id.descdo) as TextView
            datedo = itemView.findViewById(R.id.datedo) as TextView
        }
    }

    override fun getItemCount(): Int {
        return toDoes.size
    }
}