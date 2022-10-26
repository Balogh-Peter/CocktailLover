package com.mobiletest.cocktaillover.presentation.cocktail_list_screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiletest.cocktaillover.databinding.CocktailListItemBinding
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource

class CocktailListAdapter(
    private var onItemClick: ((position: Int) -> Unit)?
) :
    RecyclerView.Adapter<CocktailListAdapter.ViewHolder>() {

    private var cocktails = listOf<CocktailWithPictureBitmap>()

    fun updateItems(cocktails: List<CocktailWithPictureSource>) {
        this.cocktails = listOf()

        notifyItemRangeRemoved(0, cocktails.size)
        this.cocktails = getCocktailsWithPictureBitmap(cocktails)
        notifyItemRangeInserted(0, cocktails.size)


        // notifyItemRangeRemoved(0, cocktails.size)
        //  notifyDataSetChanged()
    }

    class ViewHolder(
        private val itemBinding: CocktailListItemBinding,
        private val onItemClick: ((position: Int) -> Unit)?
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(cocktail: CocktailWithPictureBitmap, listPosition: Int) {
            itemBinding.image.setImageBitmap(cocktail.bitmap)
            itemBinding.text1.text = cocktail.cocktail.name
            itemBinding.text2.text = cocktail.cocktail.category
            itemBinding.root.setOnClickListener {
                onItemClick?.invoke(listPosition)
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = CocktailListItemBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )
        return ViewHolder(binding, onItemClick)
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
                    bitmap = getBitmapFromByteArray(it.byteArray),
                    cocktail = it.cocktail
                )
            )
        }

        return cocktailsWithPictureBitmap
    }

    private fun getBitmapFromByteArray(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }


}