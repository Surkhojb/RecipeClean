package com.juanjo.juanjo.clean_boilerplate.ui.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juanjo.juanjo.clean_boilerplate.R;
import com.juanjo.presentation.base.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by juanj on 24/01/2018.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<Recipe> list;
    RecipeClickListener recipeClickListener;

    public RecipeAdapter(){ list = new ArrayList<>();}

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeViewHolder holder, int position) {

        holder.tvTitle.setText(list.get(position).getName());

        Glide.with(holder.itemView.getContext())
                .load(list.get(position).getImgThumb())
                .into(holder.imgTitle);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void refreshData(List<Recipe> recipes){
        if(recipes != null){
            list.clear();
            list = recipes;
        }
        notifyDataSetChanged();
    }

    public void setRecipeClickListener(RecipeClickListener listener){
        this.recipeClickListener = listener;
    }

    public Recipe getRecipe(int position) {
        return list.get(position);
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_card_view)
        CardView cardView;
        @BindView(R.id.item_image)
        ImageView imgTitle;
        @BindView(R.id.item_recipe_title)
        TextView tvTitle;
        @BindView(R.id.item_favorite)
        ImageView imgFavorite;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            imgFavorite.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.item_card_view:
                    recipeClickListener.onCardViewClicked(v,getAdapterPosition());
                    break;
                case R.id.item_favorite:
                    recipeClickListener.onFavClicked(v,getAdapterPosition());
                    break;

            }

        }
    }
}
