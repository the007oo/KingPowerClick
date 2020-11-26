package com.phattarapong.kingpowerclick

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.phattarapong.kingpowerclick.model.PhotoRemoteModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var photoRemoteModel: PhotoRemoteModel

    companion object {
        const val ARG_PHOTO_MODEL = "arg_photo_model"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        intent?.extras?.let {
            photoRemoteModel = it.getParcelable<PhotoRemoteModel>(ARG_PHOTO_MODEL)!!
            setUpView(photoRemoteModel)
        }
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true);
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpView(photoRemoteModel: PhotoRemoteModel) {
        Glide.with(this)
            .load(photoRemoteModel.thumbnailUrl)
            .error(android.R.drawable.ic_menu_report_image)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .into(imageView)
    }


}