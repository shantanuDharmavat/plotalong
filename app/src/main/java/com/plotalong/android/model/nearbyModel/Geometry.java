package com.plotalong.android.model.nearbyModel;

/**
 * Created by shantanu on 26/7/17.
 */

import java.util.HashMap;
import java.util.Map;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geometry implements Parcelable
{

    @SerializedName("location")
    @Expose
    private Location location;
    public final static Parcelable.Creator<Geometry> CREATOR = new Creator<Geometry>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Geometry createFromParcel(Parcel in) {
            Geometry instance = new Geometry();
            instance.location = ((Location) in.readValue((Location.class.getClassLoader())));
            return instance;
        }

        public Geometry[] newArray(int size) {
            return (new Geometry[size]);
        }

    }
            ;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(location);
    }

    public int describeContents() {
        return 0;
    }

}
