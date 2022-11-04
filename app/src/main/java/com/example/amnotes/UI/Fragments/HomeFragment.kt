package com.example.amnotes.UI.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.amnotes.Model.Notes
import com.example.amnotes.R
import com.example.amnotes.UI.Adapter.NotesAdapter
import com.example.amnotes.ViewModel.NotesViewModel
import com.example.amnotes.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()
    var oldMyNotes = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)


        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
            oldMyNotes = notesList as ArrayList<Notes>
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = NotesAdapter(requireContext(), notesList)
            binding.rcvAllNotes.adapter = adapter
        }

        binding.AllNotes.setOnClickListener {
            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }
        binding.filterHigh.setOnClickListener {
            viewModel.gethighNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }
        binding.filterMedium.setOnClickListener {
            viewModel.getmediumNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }
        binding.filterLow.setOnClickListener {
            viewModel.getlowNotes().observe(viewLifecycleOwner) { notesList ->
                oldMyNotes = notesList as ArrayList<Notes>
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = NotesAdapter(requireContext(), notesList)
                binding.rcvAllNotes.adapter = adapter
            }

        }

        binding.CreateAction.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_search_item)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter the note..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                NoteFiltering(p0)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NoteFiltering(p0: String?) {
        val newFilteredList = arrayListOf<Notes>()
        for (i in oldMyNotes) {
            if (i.title.contains(p0!!) || i.subtitle.contains(p0)) {
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }


}


