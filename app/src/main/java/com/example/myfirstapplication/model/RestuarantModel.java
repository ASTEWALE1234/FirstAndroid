package com.example.myfirstapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RestuarantModel implements Parcelable {
    private String code;
    private String name;
    private String productType;
    private String category;
    private int delivery_charge;
    private float price;
    private int totalQtyInCart;
    private List<RestuarantModel> children;


    protected RestuarantModel(Parcel in) {
        code = in.readString();
        name = in.readString();
        productType = in.readString();
        category = in.readString();
        delivery_charge = in.readInt();
        price=in.readFloat();
        totalQtyInCart=in.readInt();
        children=in.createTypedArrayList(RestuarantModel.CREATOR);
    }

    public static final Creator<RestuarantModel> CREATOR = new Creator<RestuarantModel>() {
        @Override
        public RestuarantModel createFromParcel(Parcel in) {
            return new RestuarantModel(in);
        }

        @Override
        public RestuarantModel[] newArray(int size) {
            return new RestuarantModel[size];
        }
    };

    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code=code;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
       this.name=name;
    }
    public String getProductType(){
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }
    public int getDelivery_charge(){
        return delivery_charge;
    }
    public void setDelivery_charge(int delivery_charge){
        this.delivery_charge=delivery_charge;
    }
    public float getPrice(){
        return price;
    }
    public int getTotalQtyInCart(){
        return totalQtyInCart;
    }
    public void setTotalQtyInCart(int totalQtyInCart){
        this.totalQtyInCart=totalQtyInCart;
    }
    public void setPrice(float price){
        this.price=price;
    }
    public List<RestuarantModel> getChildren(){
        return children;
    }

    public void setChildren(List<RestuarantModel> children){
        this.children=children;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(code);
        parcel.writeString(name);
        parcel.writeString(productType);
        parcel.writeString(category);
        parcel.writeInt(delivery_charge);
        parcel.writeFloat(price);
        parcel.writeInt(totalQtyInCart);
        parcel.writeTypedList(children);
    }
}
