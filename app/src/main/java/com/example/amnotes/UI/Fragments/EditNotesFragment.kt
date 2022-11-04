package com.example.amnotes.UI.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.amnotes.Model.Notes
import com.example.amnotes.R
import com.example.amnotes.ViewModel.NotesViewModel
import com.example.amnotes.databinding.FragmentEditNotesBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

class EditNotesFragment : Fragment() {


    val oldnotes by navArgs<EditNotesFragmentArgs>()
    lateinit var binding: FragmentEditNotesBinding
    var priority: String = "1"
    val viewmodel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditNotesBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        binding.EditTitle.setText(oldnotes.data.title)
        binding.EditSubTitle.setText(oldnotes.data.subtitle)
        binding.EditNotes.setText(oldnotes.data.notes)


        when (oldnotes.data.priority) {
            "1" -> {
                priority = "1"
                binding.PGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.PRed.setImageResource(0)
                binding.PYellow.setImageResource(0)

            }
            "2" -> {
                priority = "2"
                binding.PYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.PRed.setImageResource(0)
                binding.PGreen.setImageResource(0)

            }
            "3" -> {
                priority = "3"
                binding.PRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.PGreen.setImageResource(0)
                binding.PYellow.setImageResource(0)
            }
        }

        binding.PGreen.setOnClickListener {
            priority = "1"
            binding.PGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.PRed.setImageResource(0)
            binding.PYellow.setImageResource(0)


        }
        binding.PYellow.setOnClickListener {
            priority = "2"
            binding.PYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.PRed.setImageResource(0)
            binding.PGreen.setImageResource(0)

        }
        binding.PRed.setOnClickListener {
            priority = "3"
            binding.PRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.PGreen.setImageResource(0)
            binding.PYellow.setImageResource(0)
        }

        binding.btnEditNotes.setOnClickListener {
            updateNotes(it)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {
        val title = binding.EditTitle.text.toString()
        val subTitle = binding.EditSubTitle.text.toString()
        val notes = binding.EditNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d,yyyy ", d.time)
        val data = Notes(
            oldnotes.data.id,
            title = title,
            subtitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewmodel.addNotes(data)

        Toast.makeText(requireContext(), "Notes Updated Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {



        if (item.itemId == R.id.menuDelete) {
            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textViewYes?.setOnClickListener {
                viewmodel.deleteNotes(oldnotes.data.id!!)
                bottomSheet.dismiss()
            }
            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            bottomSheet.show()
        }

        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}