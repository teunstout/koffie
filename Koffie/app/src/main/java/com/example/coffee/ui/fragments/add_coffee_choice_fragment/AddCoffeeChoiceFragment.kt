package com.example.coffee.ui.fragments.add_coffee_choice_fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.coffee.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_add_coffee_choice.*
import kotlinx.android.synthetic.main.fragment_add_coffee_choice.view.*
import java.util.*


class AddCoffeeChoiceFragment : Fragment() {
    private var url: String = ""
    private var coffeeChoiceName: String = ""
    private var database: FirebaseDatabase? = FirebaseDatabase.getInstance()
//    private var myRef = database!!.getReference("CoffeeChoice")

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
        Log.i("myRef", database.toString())

        btnUploadPicture.btnUploadPicture.setOnClickListener {
            uploadImg()
        }

        btnGetImage.setOnClickListener {
            getImage()
        }
    }

    private fun uploadImg() {
        coffeeChoiceName = tiName.text.toString()
        if (coffeeChoiceName.isEmpty()) snackBarMessage("Please fill in a name")

        val coffeeChoiceAddToDatabase = database?.getReference(formatCoffeeName(coffeeChoiceName))
        coffeeChoiceAddToDatabase?.setValue(url)
        coffeeChoiceAddToDatabase?.push()
    }

    private fun formatCoffeeName(name: String): String {
        var formattedName = ""
        if (name.isEmpty()) return formattedName
        val nameToLowercase = name.toLowerCase(Locale.ROOT)
        val nameArray = nameToLowercase.split(" ")
        nameArray.forEach { partOfName ->
            formattedName += "${partOfName.capitalize()} "
        }
        snackBarMessage(formattedName)
        return formattedName
    }

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
                    snackBarMessage("No image found on this Url")
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

    private fun enableButtonForUpload(boolean: Boolean) {
        btnUploadPicture.isEnabled = boolean // Disable
        btnUploadPicture.isClickable = boolean // Not clickable
    }

    private fun snackBarMessage(snackBarMessage: String) {
        this@AddCoffeeChoiceFragment.view?.let { view ->
            Snackbar.make(
                view,
                snackBarMessage,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}