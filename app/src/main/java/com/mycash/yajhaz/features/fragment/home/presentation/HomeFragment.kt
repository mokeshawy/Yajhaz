package com.mycash.yajhaz.features.fragment.home.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mycash.yajhaz.R
import com.mycash.yajhaz.core.base_fragment.BaseFragment
import com.mycash.yajhaz.core.state.State
import com.mycash.yajhaz.databinding.FragmentHomeBinding
import com.mycash.yajhaz.features.fragment.home.data.model.request.TrendingPopularSellersRequestDto
import com.mycash.yajhaz.features.fragment.home.domain.viewmodel.HomeViewModel
import com.mycash.yajhaz.features.fragment.home.presentation.adapter.CategoriesAdapter
import com.mycash.yajhaz.features.fragment.home.presentation.adapter.PopularNowAdapter
import com.mycash.yajhaz.features.fragment.home.presentation.adapter.TrendingNowAdapter
import com.mycash.yajhaz.features.fragment.login.domain.viewmode.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {


    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private val viewModel: HomeViewModel by viewModels()
    private val loginViewModel: LoginViewModel by hiltNavGraphViewModels(R.id.nav_graph)
    private val loginEntity get() = loginViewModel.getLoginEntity()

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var popularNowAdapter: PopularNowAdapter
    private lateinit var trendingNowAdapter: TrendingNowAdapter

    private val listOfBaseCategories get() = viewModel.getListOfBaseCategories() ?: emptyList()
    private val listOfPopularSellers get() = viewModel.getListOfPopularSellers()
    private val listOfTrendingSellers get() = viewModel.getListOfTrendingSellers()

    private val userName get() = loginEntity?.name
    private val address
        get() = loginEntity?.addresses?.firstOrNull()?.address ?: "Riyadh ( 15 -Jasmine neighbo..."

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setUpView()
    }

    private fun FragmentHomeBinding.setUpView() {
        setOnBackClicked()
        setUpUserProfile()
        handleInternetConnection()
        lifecycleScope.apply {
            launch { observeOnBaseCategoriesResponseState() }
            launch { observeOnPopularSellersResponseState() }
            launch { observeOnTrendingSellersResponseState() }
        }
    }

    private fun FragmentHomeBinding.setOnBackClicked() =
        yajhazBarView.setOnBackCLicked { activity?.finish() }

    private fun FragmentHomeBinding.setUpUserProfile() {
        profileUserView.setUserNameTv { it.text = userName }
        profileUserView.setAddressTv { it.text = address }
    }


    private fun handleInternetConnection() {
        connectivityManager.isNetworkConnected.observe(viewLifecycleOwner) {
            if (!it) {
                showShortToast(getString(R.string.internetConnection))
                return@observe
            }
            handleRequests()
        }
    }


    private fun handleRequests() {
        val requestDto = TrendingPopularSellersRequestDto(lat = 29.1931, lng = 30.6421, filter = 1)
        viewModel.getAllResult(requestDto)
    }


    private suspend fun observeOnBaseCategoriesResponseState() {
        viewModel.baseCategoriesResponseState.collect {
            when (it) {
                is State.Error -> it.error.handleError { }
                is State.Initial -> {}
                is State.Loading -> showProgressDialog()
                is State.Success -> setCategoriesAdapter()
            }
        }
    }


    private fun setCategoriesAdapter() {
        binding.categoriesGroup.isVisible = listOfBaseCategories.isNotEmpty()
        categoriesAdapter = CategoriesAdapter(listOfBaseCategories)
        binding.categoriesRV.adapter = categoriesAdapter
    }

    private suspend fun observeOnPopularSellersResponseState() {
        viewModel.popularSellersResponseState.collect {
            when (it) {
                is State.Error -> it.error.handleError { }
                is State.Initial -> {}
                is State.Loading -> showProgressDialog()
                is State.Success -> setPopularNowAdapter()
            }
        }
    }


    private fun setPopularNowAdapter() {
        binding.popularNowGroup.isVisible = listOfPopularSellers.isNotEmpty()
        popularNowAdapter = PopularNowAdapter(listOfPopularSellers)
        binding.popularNowRV.adapter = popularNowAdapter
    }

    private suspend fun observeOnTrendingSellersResponseState() {
        viewModel.trendingSellersResponseState.collect {
            hideProgressDialog()
            when (it) {
                is State.Error -> it.error.handleError { }
                is State.Initial -> {}
                is State.Loading -> showProgressDialog()
                is State.Success -> setTrendingNowAdapter()
            }
        }
    }

    private fun setTrendingNowAdapter() {
        binding.trendingNowGroup.isVisible = listOfTrendingSellers.isNotEmpty()
        trendingNowAdapter = TrendingNowAdapter(listOfTrendingSellers)
        binding.trendingNowRV.adapter = trendingNowAdapter
    }
}