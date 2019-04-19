package lnr.hk.no.labbisv3

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewAnimalActivity : AppCompatActivity() {

    private lateinit var editAnimalView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_animal)
        editAnimalView = findViewById(R.id.edit_animal)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editAnimalView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val animal = editAnimalView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, animal)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
    companion object {
        const val EXTRA_REPLY = "com.example.android.animallistsql.REPLY"
    }
}
