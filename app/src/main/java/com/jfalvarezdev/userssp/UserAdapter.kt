package com.jfalvarezdev.userssp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jfalvarezdev.userssp.databinding.ItemUserBinding

class UserAdapter(private val users: List<User>, private val listener: OnClickListener): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private lateinit var context: Context

    //Inflamos el layout del item creado para el reciclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)

        return ViewHolder(view)
    }

    //Declaramos el contenido del item
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)

        with(holder){

            setListener(user, position+1)

            Glide.with(context)
                .load(user.url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.ivUsers)

            binding.tvOrder.text = (position +1).toString()
            binding.tvName.text = user.name
            binding.tvApellido.text = user.lastName
        }
    }

    //Declaramos el numero de items
    override fun getItemCount(): Int {
        return users.size
    }

    //Declaramos el viewholder del recyclerview y le establecemos un listener para el click
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view)

        fun setListener(user: User, position: Int){
            binding.root.setOnClickListener {
                listener.onClick(user, position)
            }
        }
    }
}