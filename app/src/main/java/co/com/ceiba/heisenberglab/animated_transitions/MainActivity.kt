package co.com.ceiba.heisenberglab.animated_transitions

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var numberSlider: NumberSlider = findViewById(R.id.numberSlider)
        // numberSlider.progress = 0.1f
    }


}
