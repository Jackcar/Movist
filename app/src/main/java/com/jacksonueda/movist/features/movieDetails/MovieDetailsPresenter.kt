package com.jacksonueda.movist.features.movieDetails

import com.jacksonueda.movist.App
import com.jacksonueda.movist.base.BaseMvpPresenterImpl
import com.jacksonueda.movist.data.AppRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Jackson on 28/10/17.
 */

class MovieDetailsPresenter : BaseMvpPresenterImpl<MovieDetailsContract.View>(), MovieDetailsContract.Presenter {

    @Inject
    lateinit var mRepository: AppRepository

    init {
        App.appComponent().inject(this)
    }

    override fun getMovie(movieId: Int) {
        mRepository.movie(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    mView?.showLoading()
                }
                .doOnTerminate {
                    mView?.dismissLoading()
                }
                .subscribe(
                        { movie ->
                            mView?.displayMovieDetails(movie)
                        },
                        {
                            mView?.showMessage("Deu erro")
                        }
                )
    }

    override fun getVideos(movieId: Int) {
        mRepository.videos(movieId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .filter { videos -> !videos.isEmpty() }
                .subscribe(
                        { videos ->
                            mView?.loadVideos(videos)
                        },
                        {
                            mView?.showMessage("Deu erro")
                        }
                )
    }
}