package io.codetail.animation;

import android.animation.Animator;

import java.lang.ref.WeakReference;

public abstract class SupportAnimator extends Animator {

    WeakReference<RevealAnimator> mTarget;

    public SupportAnimator(RevealAnimator target) {
        mTarget = new WeakReference<>(target);
    }

    public abstract boolean isNativeAnimator();

    public abstract Object get();

    public abstract void start();

    public abstract void addListener(AnimatorListener listener);

    public abstract boolean isRunning();

    public abstract void cancel();

    public void end() {
    }

    public void setupStartValues() {
    }

    public void setupEndValues() {
    }

    public SupportAnimator reverse() {
        if (isRunning()) {
            return null;
        }

        RevealAnimator target = mTarget.get();
        if (target != null) {
            return target.startReverseAnimation();
        }

        return null;
    }


    public interface AnimatorListener {

        void onAnimationStart();

        void onAnimationEnd();

        void onAnimationCancel();

        void onAnimationRepeat();
    }

    public static abstract class SimpleAnimatorListener implements AnimatorListener {

        @Override
        public void onAnimationStart() {

        }

        @Override
        public void onAnimationEnd() {

        }

        @Override
        public void onAnimationCancel() {

        }

        @Override
        public void onAnimationRepeat() {

        }
    }

}