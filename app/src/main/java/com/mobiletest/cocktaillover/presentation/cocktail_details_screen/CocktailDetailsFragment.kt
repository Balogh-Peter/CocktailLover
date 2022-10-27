package com.mobiletest.cocktaillover.presentation.cocktail_details_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mobiletest.cocktaillover.databinding.FragmentCocktailDetailsBinding
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import com.mobiletest.cocktaillover.presentation.GetBitmapFromByteArray
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CocktailDetailsFragment : Fragment() {

    @Inject
    lateinit var getBitmapFromByteArray: GetBitmapFromByteArray

    private lateinit var cocktailData: CocktailWithPictureSource
    private val args: CocktailDetailsFragmentArgs by navArgs()

    private var _binding: FragmentCocktailDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CocktailDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailData = args.cocktailData
        viewModel.onCreateCalled(cocktailData)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        listenForOnBackPressedEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        binding.image.setImageBitmap(getBitmapFromByteArray(cocktailData.byteArray))
        binding.backButton.setOnClickListener {
            navigateBackToPreviousScreen()
        }
    }

    private fun listenForOnBackPressedEvents() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navigateBackToPreviousScreen()
                }
            }
        )
    }

    private fun navigateBackToPreviousScreen() {
        findNavController().popBackStack()
    }

}