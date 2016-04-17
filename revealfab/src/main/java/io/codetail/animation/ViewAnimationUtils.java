package io.codetail.animation;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.lang.ref.WeakReference;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static io.codetail.animation.RevealAnimator.CLIP_RADIUS;

public class ViewAnimationUtils {

    private final static boolean LOLLIPOP_PLUS = SDK_INT >= LOLLIPOP;

    public static final int SCALE_UP_DURATION = 500;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static SupportAnimator createCircularReveal(View view,
                                                       int centerX, int centerY,
                                                       float startRadius, float endRadius) {

        return createCircularReveal(view, centerX, centerY, startRadius, endRadius, View.LAYER_TYPE_SOFTWARE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static SupportAnimator createCircularReveal(View view,
                                                       int centerX, int centerY,
                                                       float startRadius, float endRadius, int layerType) {

        if (!(view instanceof RevealAnimator)) {
            throw new IllegalArgumentException("View must be inside RevealFrameLayout or RevealLinearLayout.");
        }

        RevealAnimator revealLayout = (RevealAnimator) view;
        revealLayout.attachRevealInfo(new RevealAnimator.RevealInfo(centerX, centerY, startRadius, endRadius,
                new WeakReference<>(view)));


        ObjectAnimator reveal = ObjectAnimator.ofFloat(revealLayout, CLIP_RADIUS, startRadius, endRadius);
        reveal.addListener(new RevealAnimator.RevealFinishedIceCreamSandwich(revealLayout, layerType));
        return new SupportAnimatorImpl(reveal, revealLayout);
    }

    @Deprecated
    public static void liftingFromBottom(View view, float baseRotation, float fromY, int duration, int startDelay) {
        view.setRotationX(baseRotation);
        view.setTranslationY(fromY);

        view.animate()
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .setStartDelay(startDelay)
                .rotationX(0)
                .translationY(0)
                .start();

    }

    @Deprecated
    public static void liftingFromBottom(View view, float baseRotation, int duration, int startDelay) {
        view.setRotationX(baseRotation);
        view.setTranslationY(view.getHeight() / 3);

        view.animate().setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .setStartDelay(startDelay)
                .rotationX(0)
                .translationY(0)
                .start();

    }

    @Deprecated
    public static void liftingFromBottom(View view, float baseRotation, int duration) {
        view.setRotationX(baseRotation);
        view.setTranslationY(view.getHeight() / 3);

        view.animate().setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(duration)
                .rotationX(0)
                .translationY(0)
                .start();

    }
}