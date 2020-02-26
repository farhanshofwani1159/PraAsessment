package com.example.praassesment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.praassesment.databinding.FragmentSegitigaBinding
import kotlinx.android.synthetic.main.fragment_segitiga.*
import kotlin.math.sqrt

/**
 * A simple [Fragment] subclass.
 */
class SegitigaFragment : Fragment() {
    private lateinit var binding: FragmentSegitigaBinding
    private var nilaiAlas: Double = 0.00
    private var nilaiTinggi: Double = 0.00
    private var nilaiPythagoras: Double = 0.00
    private var nilaiLuas: Double = 0.00
    private var nilaiKeliling: Double = 0.00

    companion object {
        const val KEY_NILAIPYTHAGORAS = "key_nilaiPythagoras"
        const val KEY_NILAILUAS = "key_nilaiLuas"
        const val KEY_NILAIKELILING = "key_nilaiKeliling"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = DataBindingUtil.inflate(inflater, R.layout.fragment_segitiga,container,false)
        if (savedInstanceState != null) {
            nilaiPythagoras = savedInstanceState.getDouble(KEY_NILAIPYTHAGORAS)
            nilaiLuas = savedInstanceState.getDouble(KEY_NILAILUAS)
            nilaiKeliling = savedInstanceState.getDouble(KEY_NILAIKELILING)
            updateNilai(nilaiPythagoras, nilaiLuas, nilaiKeliling)
        }
        binding.btnHitung2.setOnClickListener {
            if (binding.etAlas.text.toString().isNotEmpty() && binding.etTinggi2.text.toString().isNotEmpty()) {
                nilaiAlas = binding.etAlas.text.toString().toDouble()
                nilaiTinggi = binding.etTinggi2.text.toString().toDouble()
                nilaiPythagoras = sqrt(nilaiAlas * nilaiAlas + nilaiTinggi * nilaiTinggi)
                nilaiLuas = 0.5 * nilaiAlas * nilaiTinggi
                nilaiKeliling = nilaiAlas + nilaiTinggi + nilaiPythagoras
                binding.tvLuas2.text = "Luas = $nilaiLuas"
                binding.tvKeliling2.text = "Keliling = $nilaiKeliling"
            } else {
                Toast.makeText(this.activity, "Field tidak boleh kosong!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.btnShare2.setOnClickListener {
            val textLuasSegitiga = tvLuas2.text.toString()
            val textKelilingSegitiga = tvKeliling2.text.toString()
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT,
                textLuasSegitiga + "\n" + textKelilingSegitiga
            )
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hasil hitung segitiga")
            startActivity(Intent.createChooser(shareIntent, "Share text via..."))
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(KEY_NILAIPYTHAGORAS, nilaiPythagoras)
        outState.putDouble(KEY_NILAIKELILING, nilaiKeliling)
        outState.putDouble(KEY_NILAILUAS, nilaiLuas)
    }

    @SuppressLint("SetTextI18n")
    private fun updateNilai(s1: Double, s2: Double, s3: Double) {
        binding.tvLuas2.setText("Luas = " + s2.toString())
        binding.tvKeliling2.setText("Keliling = " + s3.toString())
    }
}
