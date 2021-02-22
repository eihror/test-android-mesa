package com.eihror.mesatestigor.presentation.home

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.eihror.mesatestigor.R
import com.eihror.mesatestigor.presentation.base.BaseFragment
import com.eihror.mesatestigor.providers.models.response.News
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private lateinit var navController: NavController
    private lateinit var homeViewModel: HomeViewModel

    private val toolbar get() = toolbar_home
    private val recyclerLatestNews get() = recyclerview_latest_news

    private val newsAdapter: NewsAdapter = NewsAdapter(::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setTransparentStatusBar()

        setupView()
        setupObservable()
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.fetchNews(true)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    private fun setupView() {
        toolbar.apply {
            setTitle(R.string.main_nav_graph_home_toolbar_title)
            setButtonEndIcon(R.drawable.ic_filter)
            setButtonEndAction(::onButtonEndAction)
        }

        recyclerLatestNews.adapter = newsAdapter
    }

    private fun setupObservable() {
        homeViewModel.newsListFounded().observe(viewLifecycleOwner, Observer {
            newsAdapter.setList(it)
        })
    }

    private fun onItemClicked(news: News) {
        val action = HomeFragmentDirections.actionHomeFragmentToNewsFragment()
        navController.navigate(action)
    }

    private fun onButtonEndAction(view: View) {
        val action = HomeFragmentDirections.actionHomeFragmentToFilterFragment()
        navController.navigate(action)
    }
}