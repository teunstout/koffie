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


class UpdateCoffeeActivity : AppCompatActivity() {
    companion object {
        const val COFFEE_LIST = "COFFEE_LIST"
    }

    private var listCoffee = ArrayList<Coffee>()
    private val updateCoffeeActivity: UpdateCoffeeViewModel by viewModels()

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
        listCoffee = intent.getParcelableArrayListExtra(COFFEE_LIST)
        if (listCoffee.isNullOrEmpty()) finish()
        addItemToCard()

        btnUpdate.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch {
                listCoffee.forEach {
                    updateCoffeeActivity.updateCoffee(it)
                }
                finish()
            }
        }
    }

    private fun addItemToCard() {
        val card = findViewById<LinearLayout>(R.id.linearLayoutTable)
        listCoffee.forEachIndexed { index, coffee ->

            val coffeeRow = LayoutInflater.from(this)
                .inflate(R.layout.model_coffee_row_update, card, false)

            Glide.with(this).load(coffee.imgUrl).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    Toast.makeText(this@UpdateCoffeeActivity, "Coulden't find image of ${coffee.imgUrl}", Toast.LENGTH_LONG).show()
                    return true
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    return false
                }
            }).into(coffeeRow.findViewById<ImageView>(R.id.imgCoffee))
//            coffeeRow.findViewById<ImageView>(R.id.imgCoffee).setImageResource(coffee.imgUrl)
            coffeeRow.findViewById<TextView>(R.id.tvTypeCoffee).text = coffee.type
            coffeeRow.findViewById<TextView>(R.id.tvAmountCoffee).text = coffee.amount.toString()

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

            card.addView(coffeeRow)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteCoffee -> {
                AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_baseline_delete_24)
                    .setTitle("Delete all coffee for ${CoffeeActivity.today()}?")
                    .setMessage("After you deleted your coffee, there is no way to get it back")
                    .setPositiveButton("Yes") { dialog, which ->
                        listCoffee.forEach {
                            updateCoffeeActivity.deleteCoffee(it)
                        }
                        finish()
                    }
                    .setNegativeButton("No", null)
                    .show()
                return false
            }
            else ->  super.onOptionsItemSelected(item)
        }
    }
}