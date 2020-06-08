package com.example.coffee.ui.coffee.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.coffee.R
import com.example.coffee.ui.StartActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_coffee_choice.*
import kotlinx.android.synthetic.main.fragment_add_coffee_choice.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class AddCoffeeChoiceFragment : Fragment() {
    private var url: String = "" // Url where we can find the img
    private var coffeeChoiceName: String = "" // Name for coffee
    private var database: FirebaseDatabase? =
        FirebaseDatabase.getInstance() // Firebase real time database

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_coffee_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        enableButtonForUpload(false)

        // Click listeners
        btnUploadPicture.btnUploadPicture.setOnClickListener { uploadImg() }
        btnGetImage.setOnClickListener { getImage() }
    }

    /**
     * Upload the name and url to firebase real time database.
     * Firebase automatically overwrites coffee with the same name.
     * formattedName = the key of the data we store
     */
    private fun uploadImg() {
        coffeeChoiceName = tiName.text.toString()
        if (coffeeChoiceName.isEmpty()) return toastMessage(getString(R.string.fragment_add_coffee_choice_no_name))

        // Upload image
        CoroutineScope(Dispatchers.Main).launch {
            // Format name first
            val formattedName =
                withContext(Dispatchers.Default) { formatCoffeeName(coffeeChoiceName) }
            val pathToStore =
                "${StartActivity.PATH_COFFEE_CHOICES}/${formattedName}" // Path to save
            val coffeeChoiceAddToDatabase =
                withContext(Dispatchers.IO) { database?.getReference(pathToStore) } // Get reference to path we want to store
            coffeeChoiceAddToDatabase?.setValue(url) // Set value
            coffeeChoiceAddToDatabase?.push() // push to online database
            toastMessage(formattedName)
        }
    }

    /**
     * We format the name so the each word starts with a capital letter and is low case after that.
     * return formatted name
     */
    private fun formatCoffeeName(name: String): String {
        var formattedName = "" // Formatted name
        if (name.isEmpty()) return formattedName // If name is empty return
        val nameToLowercase =
            name.toLowerCase(Locale.ROOT) // Root so that all words go to lowercase
        val nameArray = nameToLowercase.split(" ") // Split the words and add to array
        // For each word capitalize first character
        nameArray.forEach { partOfName ->
            formattedName += "${partOfName.capitalize()} "
        }
        return formattedName
    }

    /**
     * Load in images with glide
     */
    private fun getImage() {
        url = tiUrl.text.toString()
        Log.i("EmptyUrl", url.toString())
        if (url.isEmpty()) return

        this.context?.let { context ->
            Glide.with(context).load(url).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    toastMessage(getString(R.string.glide_no_img_on_url)) // On fail display img
                    enableButtonForUpload(false)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    enableButtonForUpload(true)
                    return false
                }
            }).into(imgUploadPicture) // Load image from web into picture.
        }
    }

    /**
     * Enable the button when there is a valid url
     */
    private fun enableButtonForUpload(boolean: Boolean) {
        btnUploadPicture.isEnabled = boolean // Disable
        btnUploadPicture.isClickable = boolean // Not clickable
    }

    private fun toastMessage(toastMessage: String) {
        Toast.makeText(this.context, toastMessage, Toast.LENGTH_LONG).show()
    }
}