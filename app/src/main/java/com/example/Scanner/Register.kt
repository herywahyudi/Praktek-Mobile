package com.example.Scanner

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Scanner.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {

            val nim = binding.etNim.text.toString()
            val nama = binding.etNama.text.toString()
            val prodi = binding.etProdi.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            /**
             * Disini belum menggunakan pengecekan apakah semua field terisi atau tidak
             * silahkan tambahkan pengecekan apakah semua field terisi atau tidak (optional)
             */

            val client = ApiConfig.getApiService().postRegister(
                nim,
                nama,
                prodi,
                email,
                password
            )
            client.enqueue(object : retrofit2.Callback<Response> {
                override fun onFailure(call: retrofit2.Call<Response>, t: Throwable) {
                    Toast.makeText(
                        this@Register,
                        "Register Gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: retrofit2.Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@Register,
                            "Register Success, Anda akan di arahkan ke halaman Login",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}