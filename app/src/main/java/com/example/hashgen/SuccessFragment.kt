package com.example.hashgen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.hashgen.databinding.FragmentSuccesssBinding

class SuccessFragment : Fragment() {

    private val args: SuccessFragmentArgs by navArgs()

    private var _binding: FragmentSuccesssBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuccesssBinding.inflate(inflater, container, false)

        binding.hashTextview.text = args.hash

        binding.copyButton.setOnClickListener {
            onCopyClicked()
        }
        return binding.root
    }

    private fun onCopyClicked() {
        copyToClipboard(args.hash)

    }

    private fun copyToClipboard(hash: String) {
        val clipboardManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Encrypted text", hash)
        clipboardManager.setPrimaryClip(clipData)

    }


}
