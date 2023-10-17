package com.raka.gitapps.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raka.gitapps.data.favorite.FavoriteViewModel
import com.raka.gitapps.data.favorite.FavoriteViewModelFactory
import com.raka.gitapps.data.response.ItemsItem
import com.raka.gitapps.databinding.ActivityFavoriteUserBinding

class FavoriteUserActivity : AppCompatActivity() {
    private lateinit var favBinding: ActivityFavoriteUserBinding
    private lateinit var favAdapter: UserAdapter
    private lateinit var favViewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favBinding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(favBinding.root)

        val favoriteFactory = FavoriteViewModelFactory(application)
        favViewModel = ViewModelProvider(this, favoriteFactory).get(FavoriteViewModel::class.java)

        favAdapter = UserAdapter()
        val layoutManager = LinearLayoutManager(this)
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)

        with(favBinding.rvFavoriteUser) {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
            adapter = favAdapter
        }

        favViewModel.fav.observe(this) { favorite ->
            val items = arrayListOf<ItemsItem>()
            favorite?.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl.toString())
                items.add(item)
            }
            favAdapter.submitList(items)
        }

        // Hide the ActionBar
        supportActionBar?.hide()

    }

}