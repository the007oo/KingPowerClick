package com.phattarapong.kingpowerclick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.phattarapong.kingpowerclick.DetailActivity.Companion.ARG_PHOTO_MODEL
import com.phattarapong.kingpowerclick.adapter.PhotosAdapter
import com.phattarapong.kingpowerclick.database.PhotoDatabase
import com.phattarapong.kingpowerclick.model.PhotoRemoteModel
import com.phattarapong.kingpowerclick.network.Response
import com.phattarapong.kingpowerclick.util.GridSpacingItemDecoration
import com.phattarapong.kingpowerclick.viewmodel.PhotosViewModel
import com.phattarapong.kingpowerclick.viewmodel.PhotosViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_loading.*

class MainActivity : AppCompatActivity() {

    private lateinit var photosViewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photosViewModel = ViewModelProvider(this,PhotosViewModelFactory(this,PhotoDatabase.getInstance(this)))
            .get(PhotosViewModel::class.java)

        setUpObserve()
    }

    private fun setUpObserve(){
        photosViewModel.photos.observe(this, Observer {
            if (it is Response.Loading) {
                loadingLayout.visibility = View.VISIBLE
            } else if (it is Response.Success) {
                loadingLayout.visibility = View.GONE
                setUpAdpater(it.data)
            } else if (it is Response.Error) {
                loadingLayout.visibility = View.GONE
            }
        })
    }

    private fun setUpAdpater(dataList : List<PhotoRemoteModel>){
        recyclerView.layoutManager = GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        val space = resources.getDimension(R.dimen.space_item_photo).toInt()
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2,space,true))
        PhotosAdapter(dataList).apply {
            recyclerView.adapter = this
            this.clickListener = {
                Intent(this@MainActivity,DetailActivity::class.java).apply {
                    putExtra(ARG_PHOTO_MODEL,it)
                }.run {
                    startActivity(this)
                }
            }
        }
    }
}