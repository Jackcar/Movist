package com.jacksonueda.movist.features.movies

import com.jacksonueda.movist.base.BaseMvpPresenterImpl
import com.jacksonueda.movist.App
import com.jacksonueda.movist.data.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Jackson on 13/07/17.
 */

class MoviesPresenter : BaseMvpPresenterImpl<MoviesContract.View>(), MoviesContract.Presenter {

    @Inject
    lateinit var mRepository: AppRepository

    init {
        App.appComponent().inject(this)
    }

    override fun getMovies(page: Int) {
        mRepository.movies(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    mView?.showLoading()
                }
                .doOnTerminate {
                    mView?.dismissLoading()
                }
                .subscribe(
                        { movies ->
                            mView?.displayMovies(movies)
                        },
                        {
                            mView?.showMessage("Deu erro")
                        }
                )
    }
}