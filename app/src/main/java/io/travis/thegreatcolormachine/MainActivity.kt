package io.travis.thegreatcolormachine


import android.graphics.*
import android.graphics.drawable.Drawable
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

        menuInflater.inflate(R.menu.options_menu, menu)
        val nextImageDrawable: Drawable = menu.findItem(R.id.nextImage).icon
        nextImageDrawable.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)

        menu.findItem(R.id.red).isChecked = red
        menu.findItem(R.id.green).isChecked = green
        menu.findItem(R.id.blue).isChecked = blue

        menu.setGroupVisible(R.id.colorGroup, color)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.nextImage -> {
                imageIndex++
                if (imageIndex >= imageResIds.size)
                    imageIndex = 0
                loadImage()
            }
            R.id.color -> {
                color = !color
                updateSaturation()
                invalidateOptionsMenu()
            }
            R.id.red -> {
                red = !red
                updateColors()
                item.isChecked = red
            }
            R.id.green -> {
                green = !green
                updateColors()
                item.isChecked = green
            }
            R.id.blue -> {
                blue = !blue
                updateColors()
                item.isChecked = blue
            }
            R.id.reset -> {
                imageView.clearColorFilter()
                red = true
                green = true
                blue = true
                color = true
                invalidateOptionsMenu()
            }
        }

        return super.onOptionsItemSelected(item)
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
            1f,
            0f,
            0f,
            0f,
            0f,
            0f,
            1f,
            0f,
            0f,
            0f,
            0f,
            0f,
            1f,
            0f,
            0f,
            0f,
            0f,
            0f,
            1f,
            0f
        )
        if (!red) matrix[0] = 0f
        if (!green) matrix[6] = 0f
        if (!blue) matrix[12] = 0f
        colorMatrix.set(matrix)
        val colorFilter = ColorMatrixColorFilter(colorMatrix)
        imageView.colorFilter = colorFilter
    }

}