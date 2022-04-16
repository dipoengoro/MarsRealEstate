package id.dipoengoro.marsrealestate.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.dipoengoro.marsrealestate.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        val marsProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = DetailViewModelFactory(marsProperty, application)
        val detailViewModel = ViewModelProvider(this, viewModelFactory)[
                DetailViewModel::class.java
        ]
        binding.apply {
            viewModel = detailViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}