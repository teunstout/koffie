package com.example.coffee.ui.coffee.fragment

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.coffee.R
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.ui.coffee.CoffeeActivity
import com.example.coffee.ui.coffee.CoffeeActivityViewModel
import com.example.coffee.ui.coffee.activity.UpdateCoffeeActivity
import com.example.coffee.ui.coffee.adapter.CoffeeAdapter
import kotlinx.android.synthetic.main.fragment_coffee.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val RESTART_ACTIVITY = 100

class CoffeeFragment : Fragment() {
    private val coffeeActivityViewModel: CoffeeActivityViewModel by activityViewModels()
    private var coffeeList: ArrayList<ArrayList<Coffee>> = ArrayList()
    private var coffeeAdapter =
        CoffeeAdapter(
            coffeeList,
            { coffeeListEdit -> editCoffee(coffeeListEdit) },
            { coffee, imageView -> loadImages(coffee, imageView) }
        )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coffee, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        // Adapter
        rvCoffee.adapter = coffeeAdapter
        rvCoffee.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        // Observe all coffee as list
        coffeeActivityViewModel.coffee.observe(viewLifecycleOwner, Observer {
            addCoffeeToList(it as ArrayList<Coffee>)
        })

        // Observe all coffee that has been consumed today as int
        coffeeActivityViewModel.totalAllCoffeeInt.observe(viewLifecycleOwner, Observer {
            if (it == null) buildSmileyMessageView(0)
            else buildSmileyMessageView(it)

        })
    }

    /**
     * Each smiley has their own text and image.
     */
    private fun buildSmileyMessageView(amount: Int) {
        when (amount) {
            0 -> setImageWithText(R.drawable.disappointment, getString(R.string.smiley_disappointment))
            1, 2 -> setImageWithText(R.drawable.thinking, getString(R.string.smiley_thinking))
            in 3..6 -> setImageWithText(R.drawable.smile, getString(R.string.smiley_smile))
            in 7..10 -> setImageWithText(R.drawable.nervous, getString(R.string.smiley_nervous))
            else -> setImageWithText(R.drawable.shocked, getString(R.string.smiley_shocked))
        }
    }

    /**
     * Loads in the image and message.
     * Used for loading in smileys and text
     */
    private fun setImageWithText(imageInt: Int, message: String) {
        imgSmiley.setImageResource(imageInt)
        tvMessage.text = message
    }

    /**
     * Start the intent to update the coffee.
     * We pass the array of coffee of the date someone long pressed.
     * In this intent we can modify and update the coffee.
     */
    private fun editCoffee(coffeeListEdit: ArrayList<Coffee>) {
        val updateCoffeeIntent = Intent(this.context, UpdateCoffeeActivity::class.java)
        updateCoffeeIntent.putParcelableArrayListExtra(UpdateCoffeeActivity.COFFEE_LIST, coffeeListEdit)
        startActivityForResult(updateCoffeeIntent, RESTART_ACTIVITY)
    }

    /**
     * When intent is finished and data is updated we need to refresh the view.
     * We do this by finishing and starting the coffee intent.
     * We do this so the data on screen is properly updated.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESTART_ACTIVITY) {
            val newCoffeeIntent = Intent(this.context, CoffeeActivity::class.java)
            startActivity(newCoffeeIntent)
            activity?.finish()
        }
    }

    /**
     * Add the coffee to list. List is sorted by date.
     * We go over the list inverted because items are added to the end of the list.
     * After that we start a new list for every date.
     * Now we grouped coffee per day.
     */
    private fun addCoffeeToList(allCoffee: ArrayList<Coffee>) {
        if (allCoffee.isNullOrEmpty() || allCoffee.size == 0) return // check if list is empty and return if so
        coffeeList.clear()

        CoroutineScope(Dispatchers.Main).launch {
            var coffeeListDateDay = "" // Date of last inserted coffee

            // -1 Because the first will always fail.
            // We increment before we get the index so this will be 0 when we get the first item from the list.
            var coffeeListIndex = -1

            // Iterate over the list and add for each coffee a new list with coffee.
            // We hold a list of lists from coffee.
            allCoffee.reversed().forEach {
                if (coffeeListDateDay == it.date) {
                    coffeeList[coffeeListIndex].add(it)
                } else {
                    coffeeListDateDay = it.date
                    coffeeList.add(ArrayList())
                    coffeeList[++coffeeListIndex].add(it)
                }
            }

            coffeeAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Load images from the web with glide
     */
    private fun loadImages(coffee: Coffee, imageView: ImageView) {
        this.context?.let {
            Glide.with(it).load(coffee.imgUrl).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Toast.makeText(this@CoffeeFragment.context, getString(R.string.glide_no_img,coffee.type), Toast.LENGTH_LONG).show()
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            }).into(imageView)
        }
    }
}
