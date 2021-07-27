package com.lucasesteves.brasilflixapp.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lucasesteves.brasilflixapp.R
import com.lucasesteves.brasilflixapp.activity.HomeActivity
import com.lucasesteves.brasilflixapp.databinding.FragmentCadastrarBinding
import com.lucasesteves.brasilflixapp.util.CPFUtil
import com.lucasesteves.brasilflixapp.util.Mask


class CadastrarFragment : Fragment() {
    private var binding: FragmentCadastrarBinding? = null
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastrarBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload();
        }
    }

    private fun reload() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.campoCpf?.addTextChangedListener(binding?.campoCpf?.let {
            Mask.mask(
                "###.###.###-##",
                it
            )
        })

        binding?.campoTelefone?.addTextChangedListener(binding?.campoTelefone?.let {
            Mask.mask(
                "(##) #####-####",
                it
            )
        })

        binding?.buttonCadastrar?.setOnClickListener {
            if (CPFUtil.myValidateCPF(binding?.campoCpf?.text.toString())) {
                registerUser()
                Snackbar.make(
                    this.requireView(),
                    getString(R.string.cadastrar2),
                    Snackbar.LENGTH_SHORT
                ).show()
//                Toast.makeText(context, "Cadastro Realizado com sucesso", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_CadastrarFragment_to_LoginFragment)
            } else {
                binding?.layoutCpf?.error = "CPF inválido"
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    companion object {
        const val TAG = "EmailPassword"

    }

    private fun registerUser(): Task<AuthResult> {

        val email = binding?.campoEmail?.text.toString()
        val password = binding?.campoSenha?.text.toString()

        return auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    updateUI(null)
                }
            }
    }



}