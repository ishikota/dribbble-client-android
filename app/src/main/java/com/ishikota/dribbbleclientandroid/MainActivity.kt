package com.ishikota.dribbbleclientandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ishikota.dribbbleclientandroid.data.repository.ShotsRepository
import com.ishikota.dribbbleclientandroid.data.repository.ShotsRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
class MainActivity : AppCompatActivity() {

    private lateinit var repository: ShotsRepository

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO test code
        repository = ShotsRepositoryImpl.Factory.create(this)
        repository.getShot(471756)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("HOGE", "shots=$it")
            }, { error ->
                Log.e("HOGE", "error=${error.localizedMessage}", error)
            }).also { compositeDisposable.add(it) }
    }
}
