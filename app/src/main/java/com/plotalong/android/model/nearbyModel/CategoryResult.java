package com.plotalong.android.model.nearbyModel;

/**
 * Created by shantanu on 26/7/17.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class CategoryResult implements Parcelable
{

    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    public final static Parcelable.Creator<CategoryResult> CREATOR = new Creator<CategoryResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CategoryResult createFromParcel(Parcel in) {
            CategoryResult instance = new CategoryResult();
            instance.geometry = ((Geometry) in.readValue((Geometry.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.vicinity = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public CategoryResult[] newArray(int size) {
            return (new CategoryResult[size]);
        }

    }
            ;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(geometry);
        dest.writeValue(name);
        dest.writeValue(vicinity);
    }

    public int describeContents() {
        return 0;
    }

}
