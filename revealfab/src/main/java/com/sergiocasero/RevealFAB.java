package com.sergiocasero;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.sergiocaserohernandez.revealfab.R;

/**
 * Created by sergiocaserohernandez on 22/10/15.
 */
public class RevealFAB extends FrameLayout {

    private RelativeLayout fabLayout;
    private FloatingActionButton fab;

    private static OnClickListener onClickListener;

    private TypedArray attrs;
    private int fabBackground;
    private Context context;
    private Intent intent;

    public RevealFAB(Context context) {
        this(context, null);
    }

    public interface OnClickListener {
        void onClick(RevealFAB button, View v);
    }

    public RevealFAB(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.reveal_fab, this);

        this.context = context;
        this.attrs = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RevealFAB,
                0, 0);
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public void onResume() {
        this.fabLayout.setVisibility(INVISIBLE);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        loadViews();
        registerListeners();
        initAttrs();
    }

    private void loadViews() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabLayout = (RelativeLayout) findViewById(R.id.fab_container);
    }

    private void registerListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(RevealFAB.this, v);
            }
        });
    }

    private void initAttrs() {
        try {
            fab.setImageDrawable(attrs.getDrawable(R.styleable.RevealFAB_fab_icon));
            int white = 0x0fFFFFFF;
            fab.setBackgroundTintList(ColorStateList.valueOf(attrs.getColor(R.styleable.RevealFAB_fab_color, white)));
            fabBackground = attrs.getColor(R.styleable.RevealFAB_reveal_color, 0);
            fabLayout.setBackgroundColor(fabBackground);
        } finally {
            attrs.recycle();
        }
    }

    public void startActivityWithAnimation() {
        float finalRadius = Math.max(fabLayout.getWidth(), fabLayout.getHeight());
        animateFabReveal(0, finalRadius);
    }


    public void setOnClickListener(OnClickListener listener) {
        onClickListener = listener;
    }

    private void animateFabReveal(float initialRadius, float finalRadius) {
        int x = fab.getLeft() + fab.getMeasuredWidth() / 2;
        int y = fab.getTop() + fab.getMeasuredHeight() / 2;

        Animator animator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            animator = ViewAnimationUtils.createCircularReveal(fabLayout, x, y, initialRadius, finalRadius);
            fabLayout.setVisibility(VISIBLE);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    context.startActivity(intent);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
        } else {
            context.startActivity(intent);
        }
    }


}
