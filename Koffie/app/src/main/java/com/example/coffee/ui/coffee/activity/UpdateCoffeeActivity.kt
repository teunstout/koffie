package com.example.coffee.ui.coffee.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.coffee.R
import com.example.coffee.model.database_model.Coffee
import com.example.coffee.ui.coffee.CoffeeActivity
import com.example.coffee.ui.coffee.viewmodel.UpdateCoffeeViewModel
import kotlinx.android.synthetic.main.activity_update_coffee.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") // Gives back false warning on "getParcelableArrayListExtra(COFFEE_LIST)"
class UpdateCoffeeActivity : AppCompatActivity() {
    companion object {
        const val COFFEE_LIST = "COFFEE_LIST"
    }

    private var listCoffee = ArrayList<Coffee>()
    private val updateCoffeeViewModel: UpdateCoffeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_coffee)

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.update_coffee_menu, menu)
        supportActionBar?.show()
        return super.onCreateOptionsMenu(menu)
    }

    private fun initView() {
        // Get coffee list
        listCoffee = intent.getParcelableArrayListExtra(COFFEE_LIST)

        // Check if list is null
        if (listCoffee.isNullOrEmpty()) finish()
        addItemToCard()

        // On update button save the coffee list
        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                listCoffee.forEach { coffee -> updateCoffeeViewModel.updateCoffee(coffee)
                }

                finish()
            }
        }
    }

    /**
     * Dynamically add a row for each coffee.
     * Each row has a add and minus button for the amount of coffee.
     */
    private fun addItemToCard() {
        val card =
            findViewById<LinearLayout>(R.id.linearLayoutTable) // Material card everything will be displayed in

        listCoffee.forEachIndexed { index, coffee ->

            // Create new coffee row.
            val coffeeRow =
                LayoutInflater.from(this).inflate(R.layout.model_coffee_row_update, card, false)

            // With glide, get the images of the web and load them into the imageviews.
            Glide.with(this).load(coffee.imgUrl).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(
                        this@UpdateCoffeeActivity,
                        getString(R.string.glide_no_img, coffee.imgUrl),
                        Toast.LENGTH_LONG
                    ).show()
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(coffeeRow.findViewById(R.id.imgCoffee))

            coffeeRow.findViewById<TextView>(R.id.tvTypeCoffee).text =
                coffee.type // Set type of row
            coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text =
                coffee.amount.toString() // Set amount of row

            // Add click listeners on the buttons for add and minus
            coffeeRow.findViewById<Button>(R.id.btnAdd).setOnClickListener {
                listCoffee[index].amount++
                coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text =
                    listCoffee[index].amount.toString()
            }

            coffeeRow.findViewById<Button>(R.id.btnRemove).setOnClickListener {
                listCoffee[index].amount--
                coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text =
                    listCoffee[index].amount.toString()
            }

            card.addView(coffeeRow) // Add row to card view
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.deleteCoffee -> {
                // Build an alert box to ask the user if they are sure they want to delete all coffee of today.
                AlertDialog.Builder(this, R.style.AlertDialogCustom)
                    .setIcon(R.drawable.ic_baseline_delete_24)
                    .setTitle(
                        getString(
                            R.string.activity_update_coffee_alert_title,
                            CoffeeActivity.today()
                        )
                    )
                    .setMessage(getString(R.string.activity_update_coffee_alert_message))
                    .setPositiveButton("Yes") { dialog, which ->
                        listCoffee.forEach {
                            updateCoffeeViewModel.deleteCoffee(it)
                        }
                        finish()
                    }
                    .setNegativeButton("No", null)
                    .show()

                return false
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}