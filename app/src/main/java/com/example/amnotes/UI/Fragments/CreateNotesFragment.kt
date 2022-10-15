package com.example.amnotes.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.amnotes.Model.Notes
import com.example.amnotes.R
import com.example.amnotes.ViewModel.NotesViewModel
import com.example.amnotes.databinding.FragmentCreateNotesBinding
import java.util.*

class CreateNotesFragment : Fragment() {



    lateinit var binding: FragmentCreateNotesBinding
    val viewmodel : NotesViewModel by viewModels()
    var priority: String = "1"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)
        binding.PGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.PGreen.setOnClickListener {
            priority= "1"
            binding.PGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.PRed.setImageResource(0)
            binding.PYellow.setImageResource(0)


        }
        binding.PYellow.setOnClickListener {
            priority= "2"
            binding.PYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.PRed.setImageResource(0)
            binding.PGreen.setImageResource(0)

        }
        binding.PRed.setOnClickListener {
            priority= "3"
            binding.PRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.PGreen.setImageResource(0)
            binding.PYellow.setImageResource(0)
        }
        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.EditTitle.text.toString()
        val subTitle = binding.EditSubTitle.text.toString()
        val Notes = binding.EditNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d,yyyy ", d.time)
        val data = Notes(
            null,
            title = title,
            subtitle = subTitle,
            notes = Notes,
            date = notesDate.toString(),
            priority
        )
        viewmodel.addNotes(data)

        Toast.makeText(requireContext(),"Notes Created Successfully",Toast.LENGTH_SHORT).show()



        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }

}