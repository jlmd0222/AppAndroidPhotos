package jlmd.dev.android.appandroid.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import jlmd.dev.android.appandroid.*
import jlmd.dev.android.appandroid.databinding.FragmentPhotosBinding
import jlmd.dev.android.appandroid.view.adapters.OnItemClickListener
import jlmd.dev.android.appandroid.view.adapters.PhotoAdapter
import jlmd.dev.android.appandroid.view.model.Photo
import jlmd.dev.android.appandroid.viewmodel.MainViewModel
import jlmd.dev.android.appandroid.viewmodel.PhotosViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment(), OnItemClickListener {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentPhotosBinding
    private lateinit var adapterPhoto: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindObservers()
    }

    private fun bindObservers() {
        viewModel.photosViewState.observe(viewLifecycleOwner) {
            when (it) {
                is PhotosViewState.Loading -> showLoading(true)
                is PhotosViewState.ShowPhotos -> showList(it.photos)
                is PhotosViewState.Error -> showMessageError()
            }
        }
    }

    private fun showList(photosList: List<Photo>) {
        binding.photosRecycler.visibility = View.VISIBLE

        binding.photosRecycler.scheduleLayoutAnimation()
        adapterPhoto = PhotoAdapter(requireContext(), photosList as ArrayList<Photo>, this)

        binding.photosRecycler.apply {
            adapter = adapterPhoto
            setHasFixedSize(true)
        }

        showLoading(false)
    }

    private fun showLoading(show: Boolean){
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
            binding.photosRecycler.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.photosRecycler.visibility = View.VISIBLE
        }
    }

    private fun showMessageError(){
        Toast.makeText(requireContext(), getString(R.string.error_message_default), Toast.LENGTH_SHORT).show()
        binding.photosRecycler.visibility = View.GONE
    }

    override fun onDeleteItemClick(photo: Photo, position: Int) {
        adapterPhoto.removeItem(position)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_photos, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.refresh -> {
                Toast.makeText(requireContext(), getString(R.string.refresh_label), Toast.LENGTH_SHORT).show()
                viewModel.getPhotos()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}