package com.example.sabaid.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sabaid.LoginActivity
import com.example.sabaid.databinding.FragmentAkunBinding
import kotlinx.android.synthetic.main.fragment_akun.view.*
import org.jetbrains.anko.support.v4.startActivity

class AkunFragment : Fragment() {

    //private lateinit var akunViewModel: AkunViewModel
    private var _binding: FragmentAkunBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //akunViewModel = ViewModelProvider(this).get(AkunViewModel::class.java)

        _binding = FragmentAkunBinding.inflate(inflater, container, false)
        val root: View = binding.root
        root.textViewLoginAkun_1.setOnClickListener {
            startActivity<LoginActivity>()
        }

        /*val textView: TextView = binding.textDashboard
        akunViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}