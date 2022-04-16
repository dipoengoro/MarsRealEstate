package id.dipoengoro.marsrealestate.overview

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.dipoengoro.marsrealestate.R
import id.dipoengoro.marsrealestate.databinding.FragmentOverviewBinding
import id.dipoengoro.marsrealestate.network.MarsApiFilter

class OverviewFragment : Fragment() {

    private val overViewViewModel: OverviewViewModel by lazy {
        ViewModelProvider(this)[OverviewViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentOverviewBinding.inflate(inflater)
        setHasOptionsMenu(true)
        overViewViewModel.apply {
            navigateToSelectedProperty.observe(viewLifecycleOwner) {
                it?.let {
                    this@OverviewFragment.findNavController().navigate(
                        OverviewFragmentDirections.actionShowDetail(it)
                    )
                    this.displayPropertyDetailsComplete()
                }
            }
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = overViewViewModel
            photosGrid.adapter = PhotoGridAdapter(
                PhotoGridAdapter.OnClickListener {
                    overViewViewModel.displayPropertyDetails(it)
                })
            return root
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        overViewViewModel.updateFilter(when (item.itemId) {
            R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
            R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
            else -> MarsApiFilter.SHOW_ALL}
        )
        return true
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}