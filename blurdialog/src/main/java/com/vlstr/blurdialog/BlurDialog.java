package com.vlstr.blurdialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.vlstr.blurdialog.Drawable.RoundedCornersDrawable;

import eightbitlab.com.blurview.BlurView;

/**
 * Created by Valentin on 14/05/2017.
 */

public class BlurDialog extends BlurView {

    private TextView title;
    private ImageView icon;

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

    private void init(){
        initViews();

        setOverlayColor(getResources().getColor(R.color.colorOverlay));
    }

    private void initViews(){
        title = new TextView(getContext());
        icon = new ImageView(getContext());

        addView(title);
        addView(icon);
    }

    private void initAttributes(Context context, AttributeSet attrs){
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BlurDialog,
                0, 0);

        try {
            icon.setImageDrawable(a.getDrawable(R.styleable.BlurDialog_blurDialogIcon));
            title.setText(a.getText(R.styleable.BlurDialog_blurDialogTitle));
            title.setTextSize(a.getDimension(R.styleable.BlurDialog_blurDialogTitleSize, getResources().getDimension(R.dimen.default_blur_dialog_title_size)));

            handleElevation((int) a.getDimension(R.styleable.BlurDialog_blurDialogElevation, 0));
            handleRoundedCorners((int) a.getDimension(R.styleable.BlurDialog_blurDialogCornerRadius, getResources().getDimension(R.dimen.default_blur_dialog_corner_radius)));
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

    private void handleElevation(int elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(elevation);
        }
    }

    private void handleRoundedCorners(int cornerRadius){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setBackground(new RoundedCornersDrawable(cornerRadius));
            setOutlineProvider(ViewOutlineProvider.BACKGROUND);
            setClipToOutline(true);
        }
    }

    public void dismiss(){
        ((ViewGroup) getParent()).removeView(this);
    }
}
