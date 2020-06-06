package com.example.coffee.ui.fragments.add_coffee_choice_fragment

import android.R.attr
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.coffee.R
import kotlinx.android.synthetic.main.fragment_add_coffee_choice.*
import java.io.FileNotFoundException
import java.io.InputStream


private const val CODE_IMAGE = 100

class AddCoffeeChoiceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_coffee_choice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        imgUploadPicture.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, CODE_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            try {
                val imageUri: Uri? = data?.data
                val imageStream: InputStream? = imageUri?.let {
                    activity?.contentResolver?.openInputStream(
                        it
                    )
                }
                val selectedImage = BitmapFactory.decodeStream(imageStream)
                imgUploadPicture.setImageBitmap(selectedImage)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Toast.makeText(this.context, "Something went wrong", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this.context, "You haven't picked an Image", Toast.LENGTH_LONG).show();
        }
    }
}