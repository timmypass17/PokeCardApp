package com.example.pokecardapp.binding

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokecardapp.R
import com.example.pokecardapp.adapter.CardListAdapter
import com.example.pokecardapp.adapter.PokemonAdapter
import com.example.pokecardapp.adapter.PokemonSetAdapter
import com.example.pokecardapp.data.Card
import com.example.pokecardapp.data.pokemon.Pokemon
import com.example.pokecardapp.data.pokeset.PokemonSet
import com.example.pokecardapp.viewmodels.PokemonApiStatus


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.ic_default_icon)
            error(R.drawable.ic_default_icon)
//            error(R.drawable.ic_no_wifi)
        }
    }
}

@BindingAdapter("progressBarStatus")
fun bindProgressBar(progressBar: ProgressBar, status: PokemonApiStatus?) {
        when (status) {
        PokemonApiStatus.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        PokemonApiStatus.ERROR -> {
            progressBar.visibility = View.GONE
        }
        PokemonApiStatus.DONE -> {
            progressBar.visibility = View.GONE
        }
    }
}

//@BindingAdapter("setApiStatus")
//fun bindSetStatus(statusImageView: ImageView,
//               status: PokemonApiStatus?) {
//    when (status) {
//        PokemonApiStatus.LOADING -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_default_icon)
//        }
//        PokemonApiStatus.ERROR -> {
//            statusImageView.visibility = View.VISIBLE
//            statusImageView.setImageResource(R.drawable.ic_default_icon)
//        }
//        PokemonApiStatus.DONE -> {
//            statusImageView.visibility = View.GONE
//        }
//    }
//}

/**
 * Using a BindingAdapter to set the RecyclerView data causes data binding to automatically
 * observe the LiveData for the list of PokemonSet objects.
 * Then the binding adapter is called automatically when the PokemonSet list changes.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<PokemonSet>?) {
    val adapter = recyclerView.adapter as PokemonSetAdapter
    adapter.submitList(data)
}

@BindingAdapter("setDetailListData")
fun bindSetDetailRecyclerView(recyclerView: RecyclerView, data: List<Pokemon>?) {
    val adapter = recyclerView.adapter as PokemonAdapter
    adapter.submitList(data)
}

@BindingAdapter("cardListData")
fun bindCardsRecyclerView(recyclerView: RecyclerView, data: List<Card>?) {
    val adapter = recyclerView.adapter as CardListAdapter
    adapter.submitList(data)
}