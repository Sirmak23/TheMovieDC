package com.irmak.themoviedc

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog


class EntryActivity : AppCompatActivity() {
    private val delayMillis: Long = 3000
    private val delayMillisText: Long = 1500
    private lateinit var textViewD: TextView
    private lateinit var textViewC: TextView
    private lateinit var textViewE: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        textViewD = findViewById(R.id.textViewEntry)
        textViewC = findViewById(R.id.textViewEntry2)
        textViewE = findViewById(R.id.textViewEntry3)
        checkInternetConnection()
    }
    private fun showNoInternetDialog() {
//        val dialogBuilder = AlertDialog.Builder(this)
//        dialogBuilder.setMessage("Lütfen internet bağlantınızı kontrol edin!")
//            .setCancelable(false)
//            .setPositiveButton("Ayarlar") { dialog, _ ->
//                dialog.dismiss()
//                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
//            }
//            .setNegativeButton("Vazgeç") { dialog, _ ->
//                dialog.dismiss()
//                finish()
//            }
//
//        val alert = dialogBuilder.create()
//        alert.setTitle("Uyarı")
//        alert.show()

//        SweetAlertDialog(this)
//            .setTitleText("Here's a message!")
//            .show()





        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("İnternet bağlantınız yok!")
//            .setContentText("İnternet bağlantısı için wifi ayarlarına gitmek ister misiniz?")
            .setCancelButton(
                "Kapat"
            ) { sDialog ->  sDialog.dismiss()
                finish()}
            .setConfirmText("Ayarlar ")
            .setConfirmClickListener { sDialog ->
                sDialog.dismiss()
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                alertAgain()
            }
            .show()

    }
    private fun alertAgain(){
        SweetAlertDialog(this)
            .setTitleText("Tekrar dene")
            .setConfirmText("Tekrar dene!")
            .setConfirmClickListener { sDialog -> sDialog.dismiss()
                val intent = Intent(this, EntryActivity::class.java)
                startActivity(intent)
                finish()}
            .show()
    }
    private fun checkInternetConnection() {
        if (isInternetAvailable(this)) {
            animateText()
        } else {
            // Internet bağlantısı yok, kullanıcıya bilgi verin ve tekrar deneme seçeneği sunun
            showNoInternetDialog()
        }
    }
    @SuppressLint("ObsoleteSdkInt")
    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }

    private fun animateText() {
        val animationDuration = 1500L
        val animationDuration2 = 1500L
        val animationDuration3 = 1500L
        val distance = 500f // Harflerin yukarıdan aşağıya hareket edeceği mesafe
        val distanceX = 300f
        val animatorD = ObjectAnimator.ofFloat(textViewD, "translationY", -distance, 0f)
        animatorD.duration = animationDuration
        animatorD.startDelay = 0
        animatorD.start()

        val animatorC = ObjectAnimator.ofFloat(textViewC, "translationY", distance,0f)
        animatorC.duration = animationDuration2
        animatorC.startDelay = 0
        animatorC.start()

        val animatorE = ObjectAnimator.ofFloat(textViewE, "translationX", distanceX,0f)
        animatorE.duration = animationDuration3
        animatorE.startDelay = 0
        animatorE.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delayMillis)
    }
    }
