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
import com.example.praassesment.databinding.FragmentPersegiPanjangBinding
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*


/**
 * A simple [Fragment] subclass.
 */
class PersegiPanjangFragment : Fragment() {

    private lateinit var binding: FragmentPersegiPanjangBinding
    private var nilaiPanjang: Double = 0.00
    private var nilaiLebar: Double = 0.00
    private var nilaiLuas: Double = 0.00
    private var nilaiKeliling: Double = 0.00

    companion object {
        const val KEY_NILAILUAS = "key_nilaiLuas"
        const val KEY_NILAIKELILING = "key_nilaiKeliling"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_persegi_panjang, container, false)
        if (savedInstanceState != null) {
            nilaiLuas = savedInstanceState.getDouble(KEY_NILAILUAS)
            nilaiKeliling = savedInstanceState.getDouble(KEY_NILAIKELILING)
            updateNilai(nilaiLuas, nilaiKeliling)
        }
        binding.btnHitung.setOnClickListener {
            if (binding.etPanjang.text.toString().isEmpty() || binding.etLebar.text.toString().isEmpty()) {
                Toast.makeText(this.activity, "Field tidak boleh kosong!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                nilaiPanjang = binding.etPanjang.text.toString().toDouble()
                nilaiLebar = binding.etLebar.text.toString().toDouble()
                nilaiLuas = nilaiPanjang * nilaiLebar
                nilaiKeliling = 2 * (nilaiPanjang + nilaiLebar)
                binding.tvLuas.text = "Luas = $nilaiLuas"
                binding.tvKeliling.text = "Keliling = $nilaiKeliling"
            }
        }

        binding.btnShare.setOnClickListener {
            val textLuasPP = tvLuas.text.toString()
            val textKelilingPP = tvKeliling.text.toString()
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, textLuasPP + "\n" + textKelilingPP)
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hasil hitung persegi panjang")
            startActivity(Intent.createChooser(shareIntent, "Share text via..."))
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putDouble(KEY_NILAILUAS, nilaiLuas)
        outState.putDouble(KEY_NILAIKELILING, nilaiKeliling)
    }

    @SuppressLint("SetTextI18n")
    private fun updateNilai(s1: Double, s2: Double) {
        binding.tvLuas.setText("Luas =" + s1.toString())
        binding.tvKeliling.setText("Keliling =" + s2.toString())
    }
}

