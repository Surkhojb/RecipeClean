package com.juanjo.juanjo.clean_boilerplate.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juanjo.juanjo.clean_boilerplate.R;
import com.juanjo.presentation.base.model.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE = "RECIPE";

    @BindView(R.id.fab)
    FloatingActionButton fabAddFavorite;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.detail_image_header)
    ImageView imgRecipe;
    @BindView(R.id.detail_text_instructions)
    TextView tvInstructions;
    @BindView(R.id.detail_text_ingredients)
    TextView tvIngredients;
    @BindView(R.id.detail_text_data)
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Recipe rec = (Recipe) getIntent().getExtras().get(EXTRA_RECIPE);
        loadDetailFromRecipe(rec);
    }

    void loadDetailFromRecipe(Recipe r){
        toolbar.setTitle(r.getName());
        Glide.with(this).load(r.getImgThumb()).into(imgRecipe);
        tvData.setText(loadRecipeData(r));
        tvInstructions.setText(r.getInstruction());
        tvIngredients.setText(loadIngredientsAndMeasures(r.getIngredients(),r.getMeasures()));

    }

    String loadIngredientsAndMeasures(List<String> ingredients, List<String> measures){
        assert(ingredients.size() == measures.size());

        String ingAndMeasures = "";
        for (int i=0; i<ingredients.size(); i++) {
            ingAndMeasures += String.format("%s : %s \n",ingredients.get(i),measures.get(i));
        }
        return ingAndMeasures;
    }

    String loadRecipeData(Recipe r){
        String data = "";
        data += String.format("Country: %s",r.getCountry()) + "\n";
        data += String.format("Category: %s",r.getCategory())+ "\n";
        data += String.format("YouTube: %s",r.getYoutubeLink())+ "\n";
        data += String.format("Weblink: %s",r.getWebLink());
        return data;
    }

}
