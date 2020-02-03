package com.adteccorp.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.media.browse.MediaBrowser;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    private String[] alphabets  ={"tt","eee","yyyy"};
    Observable observable;
    private Observer observer;
    private Observable<String> fetchFromGoogle;
    LottieAnimationView mLottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mLottie= findViewById( R.id.intro_lottie_animation_view );
//        transmitter();
//        receiver();
//        transmitter2();
        mLottie.playAnimation();
    }

    private void transmitter2() {

/*
        fetchFromGoogle = io.reactivex.Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> emitter) {
                        try {
                            for (String alphabet : alphabets) {
                                emitter.onNext(alphabet);

                            }

                        } catch(Exception e) {
                            emitter.onError(e); // In case there are network errors
                        }
                    }
                });
*/
        Observable.fromArray(new String[]{"A", "B", "C", "D", "E", "F"})
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {
                        System.out.println("onNext: " + string);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    private void receiver() {
        /*
         * We create an Observer that is subscribed to Observer.
         * The only function of the Observer in this scenario is
         * to print the valeus emitted by the Observer.
         *
         * */
        observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext: " + o);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        /*
         * We can call this method to subscribe
         * the observer to the Observable.
         * */
        fetchFromGoogle.subscribe(observer);
    }

    private void transmitter() {
         observable = Observable.create( new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) {

                try {

                    /*
                     * The emitter can be used to emit each list item
                     * to the subscriber.
                     *
                     * */
                     for (String alphabet : alphabets) {
                        emitter.onNext(alphabet);

                    }

                    /*
                     * Once all the items in the list are emitted,
                     * we can call complete stating that no more items
                     * are to be emitted.
                     *
                     * */
                    emitter.onComplete();

                } catch (Exception e) {

                    /*
                     * If an error occurs in the process,
                     * we can call error.
                     *
                     * */
                    emitter.onError(e);
                }
            }
        });



    }

}
