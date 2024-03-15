package com.example.makeitmove

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    //Declare variables
    lateinit var imageView: ImageView

    lateinit var seekBar: SeekBar

    lateinit var buttonColor: Button
    lateinit var buttonFade: Button
    lateinit var buttonRotate: Button
    lateinit var buttonScale: Button
    lateinit var buttonShower: Button
    lateinit var buttonTranslate: Button

    val drawables = arrayOf(
        //Hearts
        R.drawable.ace_of_hearts,
        R.drawable.two_of_hearts,
        R.drawable.three_of_hearts,
        R.drawable.four_of_hearts,
        R.drawable.five_of_hearts,
        R.drawable.six_of_hearts,
        R.drawable.seven_of_hearts,
        R.drawable.eight_of_hearts,
        R.drawable.nine_of_hearts,
        R.drawable.ten_of_hearts,
        R.drawable.jack_of_hearts,
        R.drawable.queen_of_hearts,
        R.drawable.king_of_hearts,

        //Diamonds
        R.drawable.ace_of_diamonds,
        R.drawable.two_of_diamonds,
        R.drawable.three_of_diamonds,
        R.drawable.four_of_diamonds,
        R.drawable.five_of_diamonds,
        R.drawable.six_of_diamonds,
        R.drawable.seven_of_diamonds,
        R.drawable.eight_of_diamonds,
        R.drawable.nine_of_diamonds,
        R.drawable.ten_of_diamonds,
        R.drawable.jack_of_diamonds,
        R.drawable.queen_of_diamonds,
        R.drawable.king_of_diamonds,

        //Clubs
        R.drawable.ace_of_clubs,
        R.drawable.two_of_clubs,
        R.drawable.three_of_clubs,
        R.drawable.four_of_clubs,
        R.drawable.five_of_clubs,
        R.drawable.six_of_clubs,
        R.drawable.seven_of_clubs,
        R.drawable.eight_of_clubs,
        R.drawable.nine_of_clubs,
        R.drawable.ten_of_clubs,
        R.drawable.jack_of_clubs,
        R.drawable.queen_of_clubs,
        R.drawable.king_of_clubs,

        //Spades
        R.drawable.ace_of_spades,
        R.drawable.two_of_spades,
        R.drawable.three_of_spades,
        R.drawable.four_of_spades,
        R.drawable.five_of_spades,
        R.drawable.six_of_spades,
        R.drawable.seven_of_spades,
        R.drawable.eight_of_spades,
        R.drawable.nine_of_spades,
        R.drawable.ten_of_spades,
        R.drawable.jack_of_spades,
        R.drawable.queen_of_spades,
        R.drawable.king_of_spades
    )

    private var clickCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Link widgets to variables
        imageView = findViewById(R.id.imageView)
        imageView.scaleType = ImageView.ScaleType.FIT_XY

        seekBar = findViewById(R.id.seekBar)

        buttonColor = findViewById(R.id.buttonColor)
        buttonFade = findViewById(R.id.buttonFade)
        buttonRotate = findViewById(R.id.buttonRotate)
        buttonScale = findViewById(R.id.buttonScale)
        buttonShower = findViewById(R.id.buttonShower)
        buttonTranslate = findViewById(R.id.buttonTranslate)

        //Setup View
        randomImageSelector()
        seekBar.isEnabled = false
        buttonColor.setBackgroundColor(Color.LTGRAY)
        buttonFade.setBackgroundColor(Color.LTGRAY)
        buttonRotate.setBackgroundColor(Color.LTGRAY)
        buttonScale.setBackgroundColor(Color.LTGRAY)
        buttonShower.setBackgroundColor(Color.LTGRAY)
        buttonTranslate.setBackgroundColor(Color.LTGRAY)

        //onClickListeners
        buttonColor.setOnClickListener {
            randomImageSelector()

            val shrinkAnimator = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("scaleX", 0.5f),
                PropertyValuesHolder.ofFloat("scaleY", 0.5f)
            )
            shrinkAnimator.duration = 1000

            var colorAnimator = ObjectAnimator.ofArgb(imageView.parent, "backgroundColor", Color.BLACK, Color.RED)

            colorAnimator.setDuration(500)
            colorAnimator.repeatCount = 1
            colorAnimator.repeatMode = ObjectAnimator.REVERSE

            val enlargeAnimator = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("scaleX", 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f)
            )
            enlargeAnimator.duration = 1000

            val animatorSet = AnimatorSet()

            animatorSet.playSequentially(shrinkAnimator, colorAnimator, enlargeAnimator)
            animatorSet.start()
        }

        buttonFade.setOnClickListener {
            val animator = ObjectAnimator.ofFloat(imageView, "alpha", 0f)

            randomImageSelector()
            animator.duration = 1000
            animator.repeatCount = 1
            animator.repeatMode = ObjectAnimator.REVERSE
            animator.start()
        }

        buttonRotate.setOnClickListener {
            if (clickCounter == 0) {
                clickCounter += 1
                seekBar.isEnabled = true
                randomImageSelector()

                buttonColor.isEnabled = false
                buttonFade.isEnabled = false
                buttonScale.isEnabled = false
                buttonShower.isEnabled = false
                buttonTranslate.isEnabled = false

                buttonRotate.setBackgroundColor(android.graphics.Color.DKGRAY)

                seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        applicationContext?.let {
                            val rotation = progress.toFloat()

                            imageView.rotation = rotation
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        // Implementation if needed
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        // Implementation if needed
                    }
                })
            }
            else {
                clickCounter -= 1
                seekBar.isEnabled = false

                buttonColor.isEnabled = true
                buttonFade.isEnabled = true
                buttonScale.isEnabled = true
                buttonShower.isEnabled = true
                buttonTranslate.isEnabled = true

                buttonRotate.setBackgroundColor(Color.LTGRAY)

                seekBar.progress = 0
            }
        }

        buttonScale.setOnClickListener {
            if (clickCounter == 0) {
                clickCounter += 1
                seekBar.isEnabled = true
                randomImageSelector()

                buttonColor.isEnabled = false
                buttonFade.isEnabled = false
                buttonRotate.isEnabled = false
                buttonShower.isEnabled = false
                buttonTranslate.isEnabled = false

                buttonScale.setBackgroundColor(android.graphics.Color.DKGRAY)

                seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        applicationContext?.let {
                            val scale = progress.toFloat() / seekBar!!.max.toFloat()

                            imageView.scaleX = scale
                            imageView.scaleY = scale
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        // Implementation if needed
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        // Implementation if needed
                    }
                })
            }
            else {
                clickCounter -= 1
                seekBar.isEnabled = false

                buttonColor.isEnabled = true
                buttonFade.isEnabled = true
                buttonRotate.isEnabled = true
                buttonShower.isEnabled = true
                buttonTranslate.isEnabled = true

                buttonScale.setBackgroundColor(Color.LTGRAY)

                seekBar.progress = 100
            }
        }

        buttonShower.setOnClickListener {
            val container = imageView.parent as ViewGroup
            val containerW = container.width
            val containerH = container.height
            var cardW: Float = imageView.width.toFloat()
            var cardH: Float = imageView.height.toFloat()

            val newCard = AppCompatImageView(this)
            val randomIndex = Random.nextInt(drawables.size)

            newCard.setImageResource(drawables[randomIndex])
            newCard.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)

            container.addView(newCard)

            newCard.scaleX = Math.random().toFloat() *1.5f + .1f
            newCard.scaleY = newCard.scaleX
            cardW *= newCard.scaleX
            cardH *= newCard.scaleY

            newCard.translationX = Math.random().toFloat() * containerW - cardW / 2

            val mover = ObjectAnimator.ofFloat(newCard, View.TRANSLATION_Y, -cardH, containerH + cardH)
            mover.interpolator = AccelerateInterpolator(1f)

            val rotator = ObjectAnimator.ofFloat(newCard, View.ROTATION, (Math.random()*1000).toFloat())
            rotator.interpolator = LinearInterpolator()

            val set = AnimatorSet()
            set.playTogether(mover, rotator)
            set.duration = (Math.random() * 1500 + 500).toLong()

            set.addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    container.removeView(newCard)
                }
            })
            set.start()
        }

        buttonTranslate.setOnClickListener {
            randomImageSelector()

            val shrinkAnimator = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("scaleX", 0.5f),
                PropertyValuesHolder.ofFloat("scaleY", 0.5f)
            )
            shrinkAnimator.duration = 1000

            val translateAnimator = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("translationX", 200f),
                PropertyValuesHolder.ofFloat("translationY", 200f)
            )
            translateAnimator.duration = 1000

            val moveBackAnimator = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("translationX", 0f),
                PropertyValuesHolder.ofFloat("translationY", 0f)
            )
            moveBackAnimator.duration = 1000

            val enlargeAnimator = ObjectAnimator.ofPropertyValuesHolder(
                imageView,
                PropertyValuesHolder.ofFloat("scaleX", 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f)
            )
            enlargeAnimator.duration = 1000

            val animatorSet = AnimatorSet()

            animatorSet.playSequentially(shrinkAnimator, translateAnimator, moveBackAnimator, enlargeAnimator)
            animatorSet.start()
        }
    }

    private fun randomImageSelector() {
        val randomIndex = Random.nextInt(drawables.size)

        imageView.setImageResource(drawables[randomIndex])
    }
}