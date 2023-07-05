package com.irmak
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.irmak.themoviedc.R

class MyCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val actorPoster: ImageView
    private val actorName: TextView
    private val actorCharacter: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_view_layout, this, true)

        actorPoster = findViewById(R.id.actorPoster)
        actorName = findViewById(R.id.actorName)
        actorCharacter = findViewById(R.id.actorCharacter)

        // Custom initialization or additional configuration can be done here
    }

    fun setActorPoster(resourceId: Int) {
        actorPoster.setImageResource(resourceId)
    }

    fun setActorName(name: String) {
        actorName.text = name
    }

    fun setActorCharacter(character: String) {
        actorCharacter.text = character
    }
}
