package com.mobiletest.cocktaillover.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mobiletest.cocktaillover.databinding.FragmentCocktailListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CocktailListFragment : Fragment() {

    @Inject
    lateinit var bitmapFactoryHelper: BitmapFactoryHelper

    private var _binding: FragmentCocktailListBinding? = null
    private val binding get() = _binding!!

    private val cocktailListViewModel by viewModels<CocktailListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        cocktailListViewModel.onViewCreatedCalled()
    }

    private fun initViews() {


        cocktailListViewModel.pictureSourcesLiveData.observe(viewLifecycleOwner) {
            val bitmap = bitmapFactoryHelper.getByteArray(it[2].byteArray)
            binding.image.setImageBitmap(bitmap)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}