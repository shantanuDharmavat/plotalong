package com.plotalong.android.model.nearbyModel;

/**
 * Created by shantanu on 26/7/17.
 */

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Nearby implements Parcelable {

    public final static Parcelable.Creator<Nearby> CREATOR = new Creator<Nearby>() {
        public Nearby createFromParcel(Parcel in) {
            Nearby instance = new Nearby();
            instance.categoryType = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.categoryResult, (CategoryResult.class.getClassLoader()));
            return instance;
        }

        public Nearby[] newArray(int size) {
            return (new Nearby[size]);
        }
    };
    @SerializedName("category_type")
    @Expose
    private String categoryType;
    @SerializedName("category_result")
    @Expose
    private List<CategoryResult> categoryResult = null;

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public List<CategoryResult> getCategoryResult() {
        return categoryResult;
    }

    public void setCategoryResult(List<CategoryResult> categoryResult) {
        this.categoryResult = categoryResult;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(categoryType);
        dest.writeList(categoryResult);
    }

    public int describeContents() {
        return 0;
    }
}