package com.juanjo.juanjo.datasource.local.latest;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.juanjo.juanjo.datasource.local.DatabaseConverters;

import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by juanj on 26/01/2018.
 */

@Entity(tableName = "latest")
public class RecipeLatestVo {
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @NonNull
    private String idRecipe;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "category")
    private String category;
    @ColumnInfo(name = "country")
    private String country;
    @ColumnInfo(name = "instruction")
    private String instruction;
    @ColumnInfo(name = "urlImg")
    private String imgThumb;
    @ColumnInfo(name = "youtube")
    private String youtubeLink;
    @ColumnInfo(name = "ingredients")
    @TypeConverters({DatabaseConverters.class})
    private List<String> ingredients;
    @ColumnInfo(name = "measures")
    @TypeConverters({DatabaseConverters.class})
    private List<String> measures;
    @ColumnInfo(name = "webLink")
    private String webLink;

    public String getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(String idRecipe) {
        this.idRecipe = idRecipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getImgThumb() {
        return imgThumb;
    }

    public void setImgThumb(String imgThumb) {
        this.imgThumb = imgThumb;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getMeasures() {
        return measures;
    }

    public void setMeasures(List<String> measures) {
        this.measures = measures;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }
}
