package com.example.hashgen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.hashgen.databinding.FragmentSuccesssBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        lifecycleScope.launch {
            copyToClipboard(args.hash)
            applyAnimations()
        }


    }

    private fun copyToClipboard(hash: String) {
        val clipboardManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Encrypted text", hash)
        clipboardManager.setPrimaryClip(clipData)

    }

    private suspend fun applyAnimations() {
        binding.include.messageBackground.animate().translationY(80f).duration = 200L
        binding.include.messageTextview.animate().translationY(80f).duration = 200L

        delay(2000L)

        binding.include.messageBackground.animate().translationY(-80f).duration = 200L
        binding.include.messageTextview.animate().translationY(-80f).duration = 200L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
