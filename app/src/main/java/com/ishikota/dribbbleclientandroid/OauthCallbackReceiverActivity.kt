package com.ishikota.dribbbleclientandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ishikota.dribbbleclientandroid.data.repository.DribbbleOauthRepository
import com.ishikota.dribbbleclientandroid.data.repository.DribbbleOauthRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class OauthCallbackReceiverActivity : AppCompatActivity() {
    private lateinit var repository : DribbbleOauthRepository
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = DribbbleOauthRepositoryImpl.Factory.create(this)

        Log.i("OauthSampleActivity", "onCreate called with ${intent.data}")
        val uri = intent.data
        val code = uri?.getQueryParameter("code")
        if (uri != null && uri.scheme.equals("ishikota") && code != null) {
            repository.fetchAccessTokenAndSave(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    this::startMainActivity,
                    this::showOauthErrorMessageAndFinish
                ).also { compositeDisposable.add(it) }

        } else {
            Log.e("OauthSampleActivity", "unexpected data received. data=${intent.data}")
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showOauthErrorMessageAndFinish(error: Throwable) {
        Log.e("hoge", "error=${error.message}", error)
        Toast.makeText(this, "TODO Authentication failed...", Toast.LENGTH_SHORT).show()
        finish()
    }

}
