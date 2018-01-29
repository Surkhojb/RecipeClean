package com.juanjo.juanjo.clean_boilerplate.ui.adapter;

import android.view.View;

/**
 * Created by juanj on 24/01/2018.
 */

public interface RecipeClickListener {
    void onCardViewClicked(View v,int position);
    void onFavClicked(View v, int position);
}
