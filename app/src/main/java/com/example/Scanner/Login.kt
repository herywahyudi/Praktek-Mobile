package com.example.Scanner

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.Scanner.Home.Companion.EMAIL
import com.example.Scanner.Home.Companion.NAMA
import com.example.Scanner.Home.Companion.NIM
import com.example.Scanner.Home.Companion.PRODI
import com.example.Scanner.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Login
         */
        binding.btnLogin.setOnClickListener {
            val nim = binding.etNim.text.toString()
            val password = binding.etPassword.text.toString()

            if (nim.isEmpty() ) {
                binding.etNim.error = "NIM tidak boleh kosong"
            } else if (password.isEmpty()){
                binding.etPassword.error = "Password tidak boleh kosong"
            } else {
                val client = ApiConfig.getApiService().getLogin(nim,password)
                client.enqueue(object : retrofit2.Callback<Response> {
                    override fun onFailure(call: retrofit2.Call<Response>, t: Throwable) {
                        Toast.makeText(
                            this@Login,
                            "Login Gagal",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    /**
                    Belum menggunakan indicator loading
                    silahkan tambahkan indicator loading (optional)
                     */

                    override fun onResponse(call: retrofit2.Call<Response>, response: retrofit2.Response<Response>) {
                        if (response.isSuccessful) {
                            val data = response.body()?.data
                            if (data != null) {
                                val intent = Intent(this@Login, Home::class.java)
                                intent.putExtra(NIM, data.nim)
                                intent.putExtra(NAMA, data.nama)
                                intent.putExtra(EMAIL, data.email)
                                intent.putExtra(PRODI, data.prodi)
                                startActivity(intent)
                                finish()
                                Toast.makeText(
                                    this@Login,
                                    "Login Berhasil",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                binding.etNim.error = "NIM tidak ditemukan"
                            }
                        }
                    }
                })
            }
        }

        /**
         * Register
         */

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}