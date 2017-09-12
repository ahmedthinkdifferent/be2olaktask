
package com.ahmedagamy.task.data.model.response;

import com.ahmedagamy.task.data.api.PlaceTypeConstant;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Place {

    @SerializedName("address")
    private String mAddress;
    @SerializedName("id")
    private Long mId;
    @SerializedName("latitude")
    private Double mLatitude;
    @SerializedName("longitude")
    private Double mLongitude;
    @SerializedName("name")
    private String mName;

    private int type = PlaceTypeConstant.BEY2OLAK_PLACE;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


}
