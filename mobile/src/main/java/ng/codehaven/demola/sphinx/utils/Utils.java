package ng.codehaven.demola.sphinx.utils;

import android.content.Context;
import android.content.res.TypedArray;

import java.text.NumberFormat;
import java.util.Locale;

import ng.codehaven.demola.sphinx.R;

public class Utils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }

    public static String formatNumber(double n) {
        Locale nigeria = new Locale("en", "NG");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(nigeria);

        return formatter.format(n);
    }
}
