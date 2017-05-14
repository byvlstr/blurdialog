package com.vlstr.blurdialog.Drawable;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by Valentin on 14/05/2017.
 */

public class RoundedCornersDrawable extends GradientDrawable {

    public RoundedCornersDrawable(int cornerRadius){
        super(Orientation.BOTTOM_TOP, new int[]{Color.TRANSPARENT, Color.TRANSPARENT, Color.TRANSPARENT});
        setCornerRadius(cornerRadius);
    }
}
