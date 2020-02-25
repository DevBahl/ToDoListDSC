package com.dbsrm.todolistdsc

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(c: Context, p: ArrayList<ToDoes>) :
    RecyclerView.Adapter<ToDoAdapter.MyViewHolder>() {
    internal var context: Context
    internal var toDoes: ArrayList<ToDoes>

    init {
        context = c
        toDoes = p
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_do2, viewGroup, false
            )
        )
    }

    override fun onBindViewHolder(@NonNull myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.titledoes.setText(toDoes.get(i).getTitle())
        myViewHolder.descdoes.setText(toDoes.get(i).getDesc())
        myViewHolder.datedoes.setText(toDoes.get(i).getDate())
        val getTitleDoes = toDoes.get(i).getTitle()
        val getDescDoes = toDoes.get(i).getDesc()
        val getDateDoes = toDoes.get(i).getDate()
        val getKeyDoes = toDoes.get(i).getKey()
        myViewHolder.itemView.setOnClickListener(object : View.OnClickListener {
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

    override fun getItemCount(): Int {
        return toDoes.size
    }

    class MyViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titledoes: TextView
        var descdoes: TextView
        var datedoes: TextView
        lateinit var keydoes: TextView

        init {
            titledoes = itemView.findViewById(R.id.titledoes) as TextView
            descdoes = itemView.findViewById(R.id.descdoes) as TextView
            datedoes = itemView.findViewById(R.id.datedoes) as TextView
        }
    }
}