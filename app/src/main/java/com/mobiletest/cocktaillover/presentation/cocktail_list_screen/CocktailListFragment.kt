package com.mobiletest.cocktaillover.presentation.cocktail_list_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiletest.cocktaillover.R
import com.mobiletest.cocktaillover.databinding.FragmentCocktailListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailListFragment : Fragment() {

    private var _binding: FragmentCocktailListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CocktailListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onViewCreatedCalled()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenCreated {
            initViews()
        }
    }

    private var adapter: CocktailListAdapter? = null

    private fun initViews() {
        adapter = CocktailListAdapter {
            viewModel.onListItemClick(it)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.cocktailList.adapter = adapter
        binding.cocktailList.layoutManager = LinearLayoutManager(activity)

        viewModel.cocktailsWithPictureSources.observe(viewLifecycleOwner) {
            adapter?.updateItems(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}