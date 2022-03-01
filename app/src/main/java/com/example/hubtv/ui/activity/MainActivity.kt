package com.example.hubtv.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hubtv.data.ViewModel.AppViewModel
import com.example.hubtv.databinding.ActivityMainBinding
import com.example.hubtv.remote.ModelClass.BannerSection
import com.example.hubtv.remote.ModelClass.Data
import com.example.hubtv.ui.recyclerView_adapters.*
import com.example.hubtv.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : AppViewModel by viewModels()
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var bannerListAdapter : BannerListAdapter
    private lateinit var newlyAddedListAdapter : NewlyAddedRVAdapter
    private lateinit var featuredAdapter : FeaturedRVAdapter
    private lateinit var traditionalAdapter : TraditionalRVAdapter
    private lateinit var dramaListAdapter : DramaRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        /**
         * Performing Action On Notification Button Clicked
         */
        mBinding.notificationButton.setOnClickListener{
            showToast("Notification Button Clicked")
        }

        /**
         * Performing Action On Search Button Clicked
         */
        mBinding.searchButton.setOnClickListener{
            showToast("Search Button Clicked")
        }

        /**
         *  Calling view model function to fetch data from API.
         */
        viewModel.getMovies()

        /**
         * setting layout manager
         */
        val layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        val newlyAddedLM = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        val featuredLM = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        val traditionalLM = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
        val dramaLM = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)

        /**
         * setting banner rv recyclerview
         */
        bannerListAdapter = BannerListAdapter(this@MainActivity, this@MainActivity::itemClicked)
        mBinding.bannerRecyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = bannerListAdapter
        }

        /**
         * setting newly added rv
         */
        newlyAddedListAdapter = NewlyAddedRVAdapter(this@MainActivity, this@MainActivity::sectionItemClicked)
        mBinding.newlyAddedRv.apply {
            this.layoutManager = newlyAddedLM
            this.adapter = newlyAddedListAdapter
        }

        /**
         * setting featured rv
         */
        featuredAdapter = FeaturedRVAdapter(this@MainActivity, this@MainActivity::sectionItemClicked)
        mBinding.featuredRv.apply {
            this.layoutManager = featuredLM
            this.adapter = featuredAdapter
        }

        /**
         * setting traditional rv
         */
        traditionalAdapter = TraditionalRVAdapter(this@MainActivity, this@MainActivity::sectionItemClicked)
        mBinding.traditionalRv.apply {
            this.layoutManager = traditionalLM
            this.adapter = traditionalAdapter
        }

        /**
         * setting drama rv
         */
        dramaListAdapter = DramaRVAdapter(this@MainActivity, this@MainActivity::sectionItemClicked)
        mBinding.dramaRv.apply {
            this.layoutManager = dramaLM
            this.adapter = dramaListAdapter
        }


        /**
         * Collecting new Data from view model.
         */
        viewModel.movies.observe(
            this
        ){
            when(it){
                is Status.Success -> {
                    mBinding.progressBar.visibility = View.INVISIBLE
                    bannerListAdapter.submitList(it.data.banner_section_list?.toMutableList())
                    traditionalAdapter.submitList(it.data.section_name?.get(2)?.data?.toMutableList())
                    newlyAddedListAdapter.submitList(it.data.section_name?.get(0)?.data?.toMutableList())
                    featuredAdapter.submitList(it.data.section_name?.get(1)?.data?.toMutableList())
                    dramaListAdapter.submitList(it.data.section_name?.get(3)?.data?.toMutableList())
                }
                is Status.Loading -> mBinding.progressBar.visibility = View.VISIBLE
                is Status.Error -> showToast("Something went wrong")
            }
        }


    }

    /**
     * Showing Message Toast
     */
    private fun showToast(msg : String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }

    /**
     * Item click for banner recycler view
     */
     private fun itemClicked(data : BannerSection){
         showToast("Banner Image Clicked")
    }

    /**
     * Item click for section recycler view
     */
    private fun sectionItemClicked(data : Data?){
        showToast("Section Image Clicked")
    }

}