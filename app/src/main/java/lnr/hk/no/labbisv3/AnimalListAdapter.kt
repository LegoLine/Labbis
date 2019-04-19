package lnr.hk.no.labbisv3

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AnimalListAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var animals = emptyList<Animal>()

    inner class AnimalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val animalItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return AnimalViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val current = animals[position]
        holder.animalItemView.text = current.animal
    }

    internal fun setAnimals(animals: List<Animal>){
        this.animals = animals
        notifyDataSetChanged()
    }

    override fun getItemCount() = animals.size
}