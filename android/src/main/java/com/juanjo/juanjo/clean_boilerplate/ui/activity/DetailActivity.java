package com.juanjo.juanjo.clean_boilerplate.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.juanjo.juanjo.clean_boilerplate.R;
import com.juanjo.juanjo.clean_boilerplate.RecipeApp;
import com.juanjo.juanjo.clean_boilerplate.di.component.DaggerDetailActivityComponent;
import com.juanjo.juanjo.clean_boilerplate.di.module.DetailActivityModule;
import com.juanjo.juanjo.domain.model.event.OnRefreshFavorite;
import com.juanjo.presentation.base.model.Recipe;
import com.juanjo.presentation.detailactivity.DetailActivityContract;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class DetailActivity extends AppCompatActivity implements DetailActivityContract.View {
    public static final String EXTRA_RECIPE = "RECIPE";


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
    @BindView(R.id.detail_youtube_link)
    ImageView imYoutubeLink;
    @BindView(R.id.detail_browser_link)
    ImageView imBrowserLink;

    @Inject
    DetailActivityContract.Presenter presenter;

    @Inject
    EventBus eventBus;

    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        recipe = (Recipe) getIntent().getExtras().get(EXTRA_RECIPE);
        injectDependencies();
        loadDetailFromRecipe(recipe);
    }

    @OnClick(R.id.fab_favorite)
    public void onFabFavoriteClick(View v){
        presenter.addToFavorites(recipe);
    }

    @OnClick(R.id.detail_youtube_link)
    public void onYoutubeClick(View v){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getYoutubeLink())));
    }

    @OnClick(R.id.detail_browser_link)
    public void onBrowserClick(View v){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getWebLink())));
    }

    @Override
    public void showMessage(String message) {
        Toasty.success(getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toasty.error(getApplicationContext(),errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void eventRefresh() {
        eventBus.post(new OnRefreshFavorite());
    }

    private void injectDependencies() {
        RecipeApp app = (RecipeApp) getApplication();

        DaggerDetailActivityComponent.builder()
                .recipeAppComponent(app.getAppComponent())
                .detailActivityModule(new DetailActivityModule(this))
                .build()
                .inject(this);
    }

    void loadDetailFromRecipe(Recipe r){
        toolbar.setTitle(r.getName());
        Glide.with(this).load(r.getImgThumb()).into(imgRecipe);
        tvData.setText(loadRecipeData(r));
        showRecipeLinks(r);
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
        return data;
    }

    void showRecipeLinks(Recipe r){
        if(!r.getYoutubeLink().equals("-") && !r.getWebLink().equals("-")){
            imYoutubeLink.setVisibility(View.VISIBLE);
            imBrowserLink.setVisibility(View.VISIBLE);
        }else if(!r.getYoutubeLink().equals("-")){
            imYoutubeLink.setVisibility(View.VISIBLE);
        }else if(!r.getWebLink().equals("-")){
            imBrowserLink.setVisibility(View.VISIBLE);
        }


    }
}
