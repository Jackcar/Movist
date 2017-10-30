package com.jacksonueda.movist.data.remote

import com.jacksonueda.movist.App
import com.jacksonueda.movist.data.AppDataStore
import com.jacksonueda.movist.data.Local.AppLocalDataStore
import com.jacksonueda.movist.model.Movie
import com.jacksonueda.movist.model.Sort
import io.reactivex.Observable
import javax.inject.Inject
import retrofit2.Retrofit

/**
 * Created by Jackson on 20/07/17.
 */
class AppRemoteDataStore : AppDataStore {

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var appLocalDataStore: AppLocalDataStore

    init {
        App.appComponent().inject(this)
    }

    override fun movies(page: Int): Observable<List<Movie>> {
        return retrofit.create(RestApi::class.java).discoverMovies("popularity.desc", page)
                .retry(2)
                .map { response -> response.results }
                .doOnNext { movies ->
                    appLocalDataStore.saveMovies(movies)
                }
    }

    override fun movie(movieId: Long): Observable<Movie> {
        return retrofit.create(RestApi::class.java).movie(movieId)
                .doOnNext { movie ->
                    appLocalDataStore.saveMovie(movie)
                }
    }

//    fun signUpWithEmail(name: String, email: String, password: String, imageUri: Uri?, gender: String):
//            Observable<Disposable> {
//        val user = User(name, email, password, imageUri.toString(), gender, Utils.getTimeNow())
//
//        return RxFirebaseAuth.createUserWithEmailAndPassword(auth, email, password)
//                .map { authResult ->
//                    user.uid = authResult.user.uid
//                    authResult.user.uid
//                }
//                .flatMap<UploadTask.TaskSnapshot>(
//                        { uid ->
//                            if (imageUri != null)
//                                RxFirebaseStorage.putFile(mFirebaseStorage
//                                        .getReference(STORAGE_IMAGE_REFERENCE)
//                                        .child(uid), imageUri)
//                            else
//                                Maybe.error(Exception("null imageUri"))
//                        }
//                )
//                .map { taskSnapshot -> user.photoUrl = taskSnapshot.downloadUrl!!.toString() }
//                .onErrorReturn { user.photoUrl = "" }
//                .map {
//                    RxFirebaseDatabase
//                            .setValue(mFirebaseDatabase.getReference("user")
//                                    .child(user.uid), user).subscribe()
//                }
//                .doOnComplete { appLocalDataStore.saveUser(user) }
//                .toObservable()
//    }
//
//    fun signInWithEmail(email: String, password: String): Observable<Disposable> {
//        return RxFirebaseAuth.signInWithEmailAndPassword(auth, email, password)
//                .map { authResult -> authResult.user.uid }
//                .map { uid ->
//                    RxFirebaseDatabase.observeSingleValueEvent(
//                            mFirebaseDatabase.getReference(DATABASE_USER_REFERENCE).child(uid),
//                            User::class.java).subscribe { user -> appLocalDataStore.saveUser(user) }
//                }
//                .toObservable()
//    }
//
//    fun signUpWithFacebook(name: String, email: String, photoUrl: String, gender: String, facebookId: String, accessToken: AccessToken): Observable<Disposable> {
//        val credential = FacebookAuthProvider.getCredential(accessToken.token)
//
//        return RxFirebaseAuth.signInWithCredential(auth, credential)
//                .map { authResult -> authResult.user.uid }
//                .map { uid ->
//                    RxFirebaseDatabase.observeSingleValueEvent(
//                            mFirebaseDatabase.getReference(DATABASE_USER_REFERENCE).child(uid), User::class.java)
//                            .map { user -> appLocalDataStore.saveUser(user) }
//                            .onErrorReturn {
//                                val tmpUser = User(name, email, "", photoUrl, gender, Utils.getTimeNow(), facebookId, uid = uid)
//                                RxFirebaseDatabase
//                                        .setValue(mFirebaseDatabase.getReference("user").child(tmpUser.uid), tmpUser)
//                                        .doOnComplete { appLocalDataStore.saveUser(tmpUser) }
//                                        .subscribe()
//                            }
//                            .subscribe()
//                }
//                .toObservable()
//    }

}
