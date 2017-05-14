package com.vlstr.blurdialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.vlstr.blurdialog.Drawable.RoundedCornersDrawable;

import eightbitlab.com.blurview.BlurView;
import eightbitlab.com.blurview.RenderScriptBlur;

/**
 * Created by Valentin on 14/05/2017.
 */

public class BlurDialog extends BlurView {

    public static final int DURATION_INFINITE = -1;
    public static final int DURATION_SHORT = 2000;
    public static final int DURATION_LONG = 3500;

    private TextView title;
    private ImageView icon;

    private Runnable timer;
    private int duration;

    public BlurDialog(Context context) {
        super(context);
        init();
    }

    public BlurDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        initAttributes(context, attrs);
    }

    public BlurDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initAttributes(context, attrs);
    }

    private void init() {
        initViews();

        setOverlayColor(getResources().getColor(R.color.colorOverlay));

        timer = new Runnable() {
            @Override
            public void run() {
                hide();
            }
        };
    }

    private void initViews() {
        title = new TextView(getContext());
        icon = new ImageView(getContext());

        addView(title);
        addView(icon);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BlurDialog,
                0, 0);

        try {
            setIcon(a.getDrawable(R.styleable.BlurDialog_blurDialogIcon));
            setTitle(a.getText(R.styleable.BlurDialog_blurDialogTitle).toString());
            setTitleSize(a.getDimension(R.styleable.BlurDialog_blurDialogTitleSize, getResources().getDimension(R.dimen.default_blur_dialog_title_size)));

            handleRoundedCorners((int) a.getDimension(R.styleable.BlurDialog_blurDialogCornerRadius, getResources().getDimension(R.dimen.default_blur_dialog_corner_radius)));
            handleTimer(a.getInteger(R.styleable.BlurDialog_blurDialogDuration, 0));
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        LayoutParams titleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        titleLayoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        titleLayoutParams.topMargin = getMeasuredHeight() / 10;
        title.setLayoutParams(titleLayoutParams);

        int iconSize = getMeasuredHeight() / 2;
        LayoutParams iconLayoutParams = new LayoutParams(iconSize, iconSize);
        iconLayoutParams.topMargin = getMeasuredHeight() / 10;
        iconLayoutParams.gravity = Gravity.CENTER;
        icon.setLayoutParams(iconLayoutParams);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (duration != -1) this.postDelayed(timer, duration);
    }

    private void handleTimer(int durationCode) {
        switch (durationCode) {
            case -1:
                duration = DURATION_INFINITE;
                break;
            case 0:
                duration = DURATION_SHORT;
                break;
            case 1:
                duration = DURATION_LONG;
                break;
            default:
                duration = DURATION_SHORT;
                break;
        }
    }

    private void handleRoundedCorners(int cornerRadius) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setBackground(new RoundedCornersDrawable(cornerRadius));
            setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            setClipToOutline(true);
        }
    }

    public void show(){
        setVisibility(VISIBLE);
    }

    public void create(View decorView, int radius) {
        final ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);
        final Drawable windowBackground = decorView.getBackground();

        setupWith(rootView)
                .windowBackground(windowBackground)
                .blurAlgorithm(new RenderScriptBlur(getContext()))
                .blurRadius(radius);
    }

    public void hide(){
        setVisibility(INVISIBLE);
    }

    public void dismiss() {
        if (getParent() != null) ((ViewGroup) getParent()).removeView(this);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setIcon(Drawable icon) {
        this.icon.setImageDrawable(icon);
    }

    public void setTitleSize(float titleSize) {
        this.title.setTextSize(titleSize);
    }

    public void setCornerRadius(int cornerRadius) {
        handleRoundedCorners(cornerRadius);
    }

    public String getTitle() {
        return title.getText().toString();
    }

    public Drawable getIcon() {
        return icon.getDrawable();
    }

    public float getTitleSize() {
        return title.getTextSize();
    }
}
