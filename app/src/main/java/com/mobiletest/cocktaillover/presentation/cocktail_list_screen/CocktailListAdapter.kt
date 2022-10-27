package com.mobiletest.cocktaillover.presentation.cocktail_list_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiletest.cocktaillover.databinding.CocktailListItemBinding
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import com.mobiletest.cocktaillover.presentation.GetBitmapFromByteArray

class CocktailListAdapter(
    private var onItemClick: ((position: Int) -> Unit)?,
    private var onInfoButtonClick: ((position: Int) -> Unit)?
) :
    RecyclerView.Adapter<CocktailListAdapter.ViewHolder>() {

    private var cocktails = listOf<CocktailWithPictureBitmap>()

    fun updateItems(cocktails: List<CocktailWithPictureSource>) {
        this.cocktails = listOf()
        notifyItemRangeRemoved(0, cocktails.size)
        this.cocktails = getCocktailsWithPictureBitmap(cocktails)
        notifyItemRangeInserted(0, cocktails.size)
    }

    class ViewHolder(
        private val itemBinding: CocktailListItemBinding,
        private val onItemClick: ((position: Int) -> Unit)?,
        private var onInfoButtonClick: ((position: Int) -> Unit)?
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(cocktail: CocktailWithPictureBitmap, listPosition: Int) {
            itemBinding.image.setImageBitmap(cocktail.bitmap)
            itemBinding.name.text = cocktail.cocktail.name
            itemBinding.category.text = cocktail.cocktail.category
            itemBinding.root.setOnClickListener {
                onItemClick?.invoke(listPosition)
            }
            itemBinding.infoButton.setOnClickListener {
                onInfoButtonClick?.invoke(listPosition)
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = CocktailListItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding, onItemClick, onInfoButtonClick)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(cocktails[position], position)
    }

    override fun getItemCount() = cocktails.size


    private fun getCocktailsWithPictureBitmap(cocktails: List<CocktailWithPictureSource>)
            : List<CocktailWithPictureBitmap> {

        val cocktailsWithPictureBitmap = arrayListOf<CocktailWithPictureBitmap>()
        cocktails.forEach {
            cocktailsWithPictureBitmap.add(
                CocktailWithPictureBitmap(
                    bitmap = GetBitmapFromByteArray()(it.byteArray),
                    cocktail = it.cocktail
                )
            )
        }

        return cocktailsWithPictureBitmap
    }

}