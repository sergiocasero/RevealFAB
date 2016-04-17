package io.codetail.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;

import java.lang.ref.WeakReference;

public interface RevealAnimator {

    RevealRadius CLIP_RADIUS = new RevealRadius();

    void onRevealAnimationStart();

    void onRevealAnimationEnd();

    void onRevealAnimationCancel();

    void setRevealRadius(float value);

    float getRevealRadius();

    void invalidate(Rect bounds);

    void attachRevealInfo(RevealInfo info);

    SupportAnimator startReverseAnimation();

    class RevealInfo {
        public final int centerX;
        public final int centerY;
        public final float startRadius;
        public final float endRadius;
        public final WeakReference<View> target;

        public RevealInfo(int centerX, int centerY, float startRadius, float endRadius,
                          WeakReference<View> target) {
            this.centerX = centerX;
            this.centerY = centerY;
            this.startRadius = startRadius;
            this.endRadius = endRadius;
            this.target = target;
        }

        public View getTarget() {
            return target.get();
        }

        public boolean hasTarget() {
            return getTarget() != null;
        }
    }

    class RevealFinishedIceCreamSandwich extends AnimatorListenerAdapter {
        WeakReference<RevealAnimator> mReference;
        int mFeaturedLayerType;
        int mLayerType;

        RevealFinishedIceCreamSandwich(RevealAnimator target, int layerType) {
            mReference = new WeakReference<>(target);
            mLayerType = ((View) target).getLayerType();
            mFeaturedLayerType = layerType;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            RevealAnimator target = mReference.get();
            ((View) target).setLayerType(mFeaturedLayerType, null);
            target.onRevealAnimationStart();
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            RevealAnimator target = mReference.get();
            ((View) target).setLayerType(mLayerType, null);
            target.onRevealAnimationCancel();
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            RevealAnimator target = mReference.get();
            ((View) target).setLayerType(mLayerType, null);
            target.onRevealAnimationEnd();
        }
    }

    class RevealRadius extends Property<RevealAnimator, Float> {

        public RevealRadius() {
            super(Float.class, "revealRadius");
        }

        @Override
        public void set(RevealAnimator object, Float value) {
            object.setRevealRadius(value);
        }

        @Override
        public Float get(RevealAnimator object) {
            return object.getRevealRadius();
        }
    }
}