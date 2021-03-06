package me.listenzz.navigation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;

/**
 * Created by Listen on 2018/1/9.
 */

public class Style implements Cloneable {

    private static int INVALID_COLOR = Integer.MAX_VALUE;
    private static Drawable defaultShadow = new ColorDrawable(Color.parseColor("#DDDDDD"));

    private int screenBackgroundColor = Color.WHITE;

    private BarStyle toolbarStyle = BarStyle.LightContent;
    private Drawable backIcon;
    private int statusBarColor = Color.TRANSPARENT;
    private int toolbarBackgroundColor = INVALID_COLOR;
    private int toolbarTintColor = INVALID_COLOR;
    private int titleTextColor = INVALID_COLOR;
    private int titleTextSize = 17;
    private int elevation = -1;
    private Drawable shadow = defaultShadow;

    private int titleGravity = Gravity.START;

    private int toolbarButtonTintColor = INVALID_COLOR;
    private int toolbarButtonTextSize = 15;

    private String bottomBarBackgroundColor = "#FFFFFF";
    private String bottomBarActiveColor = null;
    private String bottomBarInActiveColor = null;
    private Drawable bottomBarShadow = defaultShadow;

    private Context context;

    protected Style (Context context) {
        this.context = context;
        statusBarColor = AppUtils.fetchContextColor(context, R.attr.colorPrimaryDark);
        toolbarBackgroundColor = AppUtils.fetchContextColor(context, R.attr.colorPrimary);
    }

    // ------ screen ------

    public void setScreenBackgroundColor(int color) {
        screenBackgroundColor = color;
    }

    public int getScreenBackgroundColor() {
        return screenBackgroundColor;
    }

    // ----- tabBar  -----

    public void setBottomBarBackgroundColor(String bottomBarBackgroundColor) {
        this.bottomBarBackgroundColor = bottomBarBackgroundColor;
    }

    public String getBottomBarBackgroundColor() {
        return bottomBarBackgroundColor;
    }

    public void setBottomBarActiveColor(String bottomBarActiveColor) {
        this.bottomBarActiveColor = bottomBarActiveColor;
    }

    public String getBottomBarActiveColor() {
        return bottomBarActiveColor;
    }

    public String getBottomBarInActiveColor() {
        return bottomBarInActiveColor;
    }

    public void setBottomBarInActiveColor(String bottomBarInActiveColor) {
        this.bottomBarInActiveColor = bottomBarInActiveColor;
    }

    // ------- toolBar ---------

    public void setToolbarStyle(BarStyle barStyle) {
        toolbarStyle = barStyle;
    }

    public BarStyle getToolbarStyle() {
        return toolbarStyle;
    }

    public void setStatusBarColor(int color) {
        statusBarColor = color;
    }

    public int getStatusBarColor() {
        return statusBarColor;
    }

    public void setToolbarBackgroundColor(int color) {
        toolbarBackgroundColor = color;
    }

    public int getToolbarBackgroundColor() {
        if (toolbarBackgroundColor != INVALID_COLOR) {
            return toolbarBackgroundColor;
        }

        if (toolbarStyle == BarStyle.LightContent) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    public void setElevation(int dp) {
        this.elevation = AppUtils.dp2px(context, dp);
    }

    public int getElevation() {
        if (elevation != -1) {
            return elevation;
        }
        elevation = AppUtils.dp2px(context, 4);
        return elevation;
    }

    public void setToolbarTintColor(int color) {
        toolbarTintColor = color;
    }

    public int getToolbarTintColor() {
        if (toolbarTintColor != INVALID_COLOR) {
            return toolbarTintColor;
        }

        if (toolbarStyle == BarStyle.LightContent) {
            return Color.WHITE;
        }

        if (toolbarBackgroundColor == INVALID_COLOR) {
            return Color.parseColor("#666666");
        }

        return Color.BLACK;

    }

    public void setBackIcon(Drawable icon) {
        backIcon = icon;
    }

    public Drawable getBackIcon() {
        if (backIcon != null) {
            return backIcon;
        }
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.nav_ic_arrow_back);
        drawable.setColorFilter(getToolbarButtonTintColor(), PorterDuff.Mode.SRC_ATOP);
        backIcon = drawable.mutate();
        return backIcon;
    }

    public void setTitleTextColor(int color) {
        titleTextColor = color;
    }

    public int getTitleTextColor() {
        if (titleTextColor != INVALID_COLOR) {
            return titleTextColor;
        }

        return getToolbarTintColor();
    }

    public void setTitleTextSize(int dp) {
        titleTextSize = dp;
    }

    public int getTitleTextSize() {
        return titleTextSize;
    }

    public Drawable getShadow() {
        return shadow;
    }

    public void setShadow(Drawable drawable) {
        this.shadow = drawable;
    }

    public Drawable getBottomBarShadow() {
        return bottomBarShadow;
    }

    public void setBottomBarShadow(Drawable drawable) {
        this.bottomBarShadow = drawable;
    }

    public void setToolbarButtonTintColor(int color) {
        toolbarButtonTintColor = color;
    }

    public int getToolbarButtonTintColor() {
        if (toolbarButtonTintColor != INVALID_COLOR) {
            return toolbarButtonTintColor;
        }
        return getToolbarTintColor();
    }

    public void setToolbarButtonTextSize(int dp) {
        toolbarButtonTextSize = dp;
    }

    public int getToolbarButtonTextSize() {
        return toolbarButtonTextSize;
    }

    public void setTitleGravity(int gravity) {
        titleGravity = gravity;
    }

    public int getTitleGravity() {
        return titleGravity;
    }

    @Override
    public Style clone() throws CloneNotSupportedException {
        return (Style) super.clone();
    }
}
