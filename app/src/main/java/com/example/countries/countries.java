package com.example.countries;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
@Entity
public class countries {

    @PrimaryKey(autoGenerate = true)
    private int id;

     @NonNull
    @SerializedName("name")
    @Expose
    private String name;

    @ColumnInfo
    @SerializedName("capital")
    @Expose
    private String capital;

    @ColumnInfo
    @SerializedName("region")
    @Expose
    private String region;

    @TypeConverters(Converter.class)
    @ColumnInfo
    @SerializedName("borders")
    @Expose
    private List<String> borders = null;

    @ColumnInfo
    @SerializedName("subregion")
    @Expose
    private String subregion;

     @ColumnInfo
    @SerializedName("population")
    @Expose
    private Integer population;

    @TypeConverters(Converters.class)
    @ColumnInfo
    @SerializedName("languages")
    @Expose
    private List<Language> languages;

    @ColumnInfo
    @SerializedName("flag")
    @Expose
    private String flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @TypeConverters(Converters.class)
    public List<Language> getLanguages() {
        return languages;
    }

    @TypeConverters(Converters.class)
    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    @TypeConverters(Converter.class)
    public List<String> getBorders() {
        return borders;
    }

    @TypeConverters(Converter.class)
    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

}
