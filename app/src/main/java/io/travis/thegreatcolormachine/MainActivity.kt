package io.travis.thegreatcolormachine


import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private var imageResIds = intArrayOf(R.drawable.cuba1, R.drawable.cuba2, R.drawable.cuba3)
    private var imageIndex = 0
    private var color = true
    private var red = true
    private var green = true
    private var blue = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById<ImageView>(R.id.imageView)
        loadImage()
    }

    private fun loadImage() {
        Glide.with(this).load(imageResIds[imageIndex]).into(imageView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuItem = menu.add("Next Image")
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        menuItem.setIcon(R.drawable.ic_baseline_add_a_photo_24)
        menuItem.icon.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        return true
    }

    private fun updateSaturation() {
        val colorMatrix = ColorMatrix()
        if (color) {
            blue = true
            green = blue
            red = green
            colorMatrix.setSaturation(1f)
        } else {
            colorMatrix.setSaturation(0f)
        }
        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        imageView.colorFilter = colorFilter
    }

    private fun updateColors() {
        val colorMatrix = ColorMatrix()
        val matrix = floatArrayOf(
            1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f,
            0f, 0f, 1f, 0f,
            0f, 0f, 0f, 1f
        )
        if (!red) matrix[0] = 0f
        if (!green) matrix[6] = 0f
        if (!blue) matrix[12] = 0f
        colorMatrix.set(matrix)
        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        imageView.colorFilter = colorFilter
    }

}