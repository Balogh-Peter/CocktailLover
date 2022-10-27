package com.mobiletest.cocktaillover.presentation.instructions_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mobiletest.cocktaillover.databinding.FragmentInstructionsBinding
import com.mobiletest.cocktaillover.domain.model.CocktailWithPictureSource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstructionsFragment : Fragment() {

    private lateinit var cocktailData: CocktailWithPictureSource
    private val args: InstructionsFragmentArgs by navArgs()

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<InstructionsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cocktailData = args.cocktailData
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onCreateCalled(cocktailData)
        setBackButtonListener()
        listenForOnBackPressedEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setBackButtonListener() {
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