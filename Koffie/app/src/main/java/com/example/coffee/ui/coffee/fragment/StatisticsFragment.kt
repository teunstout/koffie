package com.example.coffee.ui.coffee.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.coffee.R
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.ui.coffee.CoffeeActivityViewModel
import kotlinx.android.synthetic.main.fragment_statistics.*
import kotlinx.android.synthetic.main.model_coffee_card.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsFragment : Fragment() {
    private val coffeeActivityViewModel: CoffeeActivityViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tvTitleStatistics.text = getString(R.string.fragment_statistics_title)

        coffeeActivityViewModel.totalPerCoffee.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                modelCoffeeCard.visibility = View.VISIBLE
                buildViewDynamically(it)
            } else modelCoffeeCard.visibility = View.INVISIBLE
        })
    }

    /**
     * Build view dynamically with all the cups of coffee
     */
    private fun buildViewDynamically(coffeeTotalList: List<Coffee>) {
        // Root layout where we add a row for each coffee
        val rootLayout: LinearLayout? = view?.findViewById(R.id.linearLayoutTable)

        CoroutineScope(Dispatchers.Main).launch {

            // For each coffee make new row.
            coffeeTotalList.forEach { coffee ->
                val coffeeRow = LayoutInflater.from(this@StatisticsFragment.context)
                    .inflate(R.layout.model_coffee_row, rootLayout, false)
                loadImages(coffee,  coffeeRow.findViewById(R.id.imgCoffee))
                coffeeRow.findViewById<TextView>(R.id.tvTypeCoffee).text = coffee.type
                coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text =
                    coffee.amount.toString()
                rootLayout?.addView(coffeeRow)
            }

        }

    }

    /**
     * Load images in the image view for each coffee row
     */
    private fun loadImages(coffee: Coffee, imageView: ImageView){

        // Check if there is a context
        this.context?.let {
            // Load image with glide and context
            Glide.with(it).load(coffee.imgUrl).listener(object : RequestListener<Drawable> {
                // If we can't find a image with the url show snackbar
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Toast.makeText(this@StatisticsFragment.context, "Coulden't find image of ${coffee.type}", Toast.LENGTH_LONG).show()
                    return true
                }

                // If we can find a image load it into the imageview
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            }).into(imageView)
        }

    }

}
