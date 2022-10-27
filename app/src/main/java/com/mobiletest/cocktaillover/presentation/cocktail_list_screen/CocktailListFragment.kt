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
import com.mobiletest.cocktaillover.databinding.FragmentCocktailListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailListFragment : Fragment() {

    private var adapter: CocktailListAdapter? = null

    private var _binding: FragmentCocktailListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CocktailListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onCreateCalled()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailListBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        initAdapter()
        observeChangesInViewModel()
    }

    private fun initAdapter() {
        adapter = CocktailListAdapter(
            {
                viewModel.onListItemClick(it)
            },
            {
                viewModel.onInfoItemClick(it)
            }
        )
        binding.cocktailList.adapter = adapter
        binding.cocktailList.layoutManager = LinearLayoutManager(activity)
    }

    private fun observeChangesInViewModel() {
        viewModel.cocktailsWithPictureSources.observe(viewLifecycleOwner) {
            adapter?.updateItems(it)
        }

        viewModel.openDetailsScreen.observe(viewLifecycleOwner) {
            if (it) {
                val action = CocktailListFragmentDirections.actionCocktailListToCocktailDetails(
                    viewModel.cocktailData
                )
                findNavController().navigate(action)
            }
        }

        viewModel.openInstructionsScreen.observe(viewLifecycleOwner) {
            if (it) {
                val action = CocktailListFragmentDirections.actionCocktailListToInstructions(
                    viewModel.cocktailData
                )
                findNavController().navigate(action)
            }
        }

    }

}