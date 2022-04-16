package id.dipoengoro.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.dipoengoro.marsrealestate.databinding.GridViewItemBinding
import id.dipoengoro.marsrealestate.network.MarsProperty

class PhotoGridAdapter(
    private val onClickListener: OnClickListener
) : ListAdapter<
        MarsProperty,
        PhotoGridAdapter.MarsPropertyViewHolder
        >(DiffCallback) {
    inner class MarsPropertyViewHolder(private val binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty) {
            binding.property = marsProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean =
            oldItem.id == newItem.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPropertyViewHolder =
        MarsPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: MarsPropertyViewHolder, position: Int) {
        holder.apply {
            getItem(position).let {
                itemView.setOnClickListener { _ ->
                    onClickListener.onClick(it)
                }
                bind(it)
            }
        }
    }

    class OnClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
        fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
    }
}