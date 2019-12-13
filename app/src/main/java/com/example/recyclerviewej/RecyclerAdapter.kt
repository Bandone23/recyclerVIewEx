package com.example.recyclerviewej

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private var superheros: MutableList<Superhero> = ArrayList()
    lateinit var context: Context

     fun RecyclerAdapter(superheros: MutableList<Superhero>, context: Context) {
        this.superheros = superheros
        this.context = context
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheros.get(position)
        holder.bind(item, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_superhero_list, parent, false))
    }

    override fun getItemCount(): Int {
        return superheros.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val superheroName = view.findViewById(R.id.tvSuperhero) as TextView
        private val realName = view.findViewById(R.id.tvRealName) as TextView
        private val publisher = view.findViewById(R.id.tvPublisher) as TextView
        private val avatar = view.findViewById(R.id.ivAvatar) as ImageView

        fun bind(superhero: Superhero, context: Context) {
            superheroName.text = superhero.superhero
            realName.text = superhero.realName
            publisher.text = superhero.publisher
            avatar.loadUrlGlide(superhero.photo)
            itemView.setOnClickListener(View.OnClickListener {
                Toast.makeText(
                    context,
                    superhero.superhero,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }

        private fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
        private fun ImageView.loadUrlGlide(url:String) {
            Glide.with(context).load(url).into(this)
        }
    }
}