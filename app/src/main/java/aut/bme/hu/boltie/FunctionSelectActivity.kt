package aut.bme.hu.boltie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FunctionSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_select)
    }

    fun fsaOnClickListener(v: android.view.View) {
        val intent = Intent(this, BarcodeReaderActivity::class.java)
        startActivity(intent)
    }
}
