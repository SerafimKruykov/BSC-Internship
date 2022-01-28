package com.example.note.mainScreen

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log

import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.note.*
import com.example.note.data.NoteRepository
import com.example.note.data.backUp.BackUpWorker
import com.example.note.databinding.FragmentListBinding
import com.example.note.Constants.Location
import com.example.note.R
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.common.api.GoogleApi
import com.google.android.gms.location.*
import java.util.concurrent.TimeUnit
import java.util.logging.LogRecord


class ListFragment : Fragment(R.layout.fragment_list) {

    private lateinit var viewModel: AllNotesViewModel
    private lateinit var communicator: Communicator
    private lateinit var myAdapter: NotesAdapter

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private var binding: FragmentListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())

        communicator = activity as Communicator
        viewModel =
            ViewModelProvider(this, AllNotesViewModelFactory(NoteRepository(requireContext()))).get(
                AllNotesViewModel::class.java
            )
        subscribeToViewModel()
        initWorker()
    }

    private fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                Location.REQUEST_CODE
            )
        }else{
            val task = fusedLocationProviderClient.lastLocation
            task.addOnSuccessListener {
                if (it != null) {
                    val lastLoc = it.latitude.toString() + it.longitude.toString()
                    binding?.locationTextView?.text = lastLoc
                }

            }
            task.addOnFailureListener {
                Toast.makeText(requireContext(), resources.getString(R.string.location_failed), Toast.LENGTH_SHORT).show()
                Log.i("loc",it.toString())
            }
        }
    }



    private fun subscribeToViewModel() {
        viewModel.onAboutBtnPressed.observe(this) {
            openAboutScreen()
        }
        viewModel.onNotePressed.observe(this) {
            openNote()
        }
        viewModel.onAddBtnPressed.observe(this) {
            addNote()
        }
        viewModel.notes.observe(this) {
            myAdapter.submitList(viewModel.notes.value)
        }
    }

    private fun initWorker() {
        WorkManager.getInstance(requireContext())
            .enqueue(
                PeriodicWorkRequest.Builder(BackUpWorker::class.java, 15, TimeUnit.MINUTES).build()
            )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun initView() {
        myAdapter = NotesAdapter { note -> viewModel.tryToOpen(note) }.apply {
            submitList(viewModel.notes.value)
        }
        binding?.notesRecyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = myAdapter
        }
        binding?.addButton.also {
            it?.setOnClickListener {
                viewModel.tryToCreateNote()
            }
        }
        binding?.webViewButton?.setOnClickListener {
            communicator.openWebView()
        }
        binding?.customTextViewButton?.setOnClickListener {
            communicator.openTextView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
        setUpSearch(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpSearch(menu: Menu) {
        (menu
            .findItem(R.id.notes_search_view)
            .actionView as SearchView)
            .setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    viewModel.searchNotes(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchNotes(newText)
                    return true
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_download -> {
                viewModel.downloadNote()
                true
            }
            R.id.menu_about -> {
                viewModel.openAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openNote() {
        communicator.openNote(viewModel.currentNote.value)
    }

    private fun addNote() {
        communicator.addNote()
    }

    private fun openAboutScreen() {
        val intent = Intent(context, AboutActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadAllNotes()
        initView()
    }

    override fun onResume() {
        super.onResume()
        fetchLocation()
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

