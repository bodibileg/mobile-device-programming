package com.example.chemicalguessinggame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chemicalguessinggame.databinding.FragmentFirstBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    private val chemicalList = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdd.setOnClickListener {
            val chemicalNameText = binding.editTextAdd.text.toString()
            if (chemicalList.contains(chemicalNameText)) {
                binding.textviewFirst.text = "Chemical '${chemicalNameText}' is already available"
            } else {
                chemicalList.add(chemicalNameText)
                binding.textviewFirst.text = "Chemical '${chemicalNameText}' added successfully"
            }
            binding.editTextAdd.text.clear()
        }

        binding.buttonGuess.setOnClickListener {
            val chemicalGuessText = binding.editTextGuess.text.toString()
            val randomElement = chemicalList.randomOrNull()
            if (randomElement != null) {
                val message = if (randomElement == chemicalGuessText) {
                    "Congratulations! You guessed it right. It was $randomElement"
                } else {
                    "Sorry, wrong guess. The correct answer was $randomElement"
                }
                binding.textviewFirst.text = message
            } else {
                binding.textviewFirst.text = "No chemicals available to guess"
            }
            binding.editTextGuess.text.clear()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}