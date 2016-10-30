package com.skillbranch.thirdapp.data.storage.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductDTO implements Parcelable{

    private int id;
    private String productName;
    private String imageUrl;
    private String description;
    private int price;
    private int count;

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Конструктор
     * @param id
     * @param productName
     * @param imageUrl
     * @param description
     * @param price
     * @param count
     */
    public ProductDTO(int id, String productName, String imageUrl, String description, int price, int count) {
        this.id = id;
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.count = count;
    }
    //region ========================= Parcelable =========================
    protected ProductDTO(Parcel in) {
        id = in.readInt();
        productName = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        price = in.readInt();
        count = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(productName);
        dest.writeString(imageUrl);
        dest.writeString(description);
        dest.writeInt(price);
        dest.writeInt(count);
    }



    public static final Parcelable.Creator<ProductDTO> CREATOR = new Parcelable.Creator<ProductDTO>() {
        @Override
        public ProductDTO createFromParcel(Parcel in) {
            return new ProductDTO(in);
        }

        @Override
        public ProductDTO[] newArray(int size) {
            return new ProductDTO[size];
        }
    };

    //endregion

    //region ========================= GETTERS =========================
    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
    //endregion

    //region ========================= SETTERS =========================
    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }
    //endregion
}
