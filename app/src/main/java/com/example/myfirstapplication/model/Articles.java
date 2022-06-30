package com.example.myfirstapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Articles implements Parcelable {
    private String subCode;
    private String subName;
    private String subProductType;
    private String subCategory;
    private float subPrice;
    private int totalQtyInCart;

    public int getTotalQtyInCart(){
        return totalQtyInCart;
    }
   public void setTotalQtyInCart(int totalQtyInCart){
        this.totalQtyInCart=totalQtyInCart;
   }

    protected Articles(Parcel in) {
        subCode = in.readString();
        subName = in.readString();
        subProductType = in.readString();
        subCategory = in.readString();
        subPrice = in.readFloat();
        totalQtyInCart=in.readInt();
    }

    public static final Creator<Articles> CREATOR = new Creator<Articles>() {
        @Override
        public Articles createFromParcel(Parcel in) {
            return new Articles(in);
        }

        @Override
        public Articles[] newArray(int size) {
            return new Articles[size];
        }
    };

    public String getSubCode(){
         return subCode;
     }
     public void setSubCode(String subCode){
         this.subCode=subCode;
     }
     public String getSubName(){
         return subName;
     }
     public void setSubName(String subName){
         this.subName=subName;
     }

     public String getSubProductType(){
         return subProductType;
     }
     public void setSubProductType(String subProductType){
         this.subProductType=subProductType;
     }
     public String getSubCategory(){
         return subProductType;
     }
     public void setSubCategory(String subCategory){
         this.subCategory=subCategory;
     }
     public Float getSubPrice(){
         return subPrice;
     }
     public void setSubPrice(float subPrice){
         this.subPrice=subPrice;
     }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(subCode);
        parcel.writeString(subName);
        parcel.writeString(subProductType);
        parcel.writeString(subCategory);
        parcel.writeFloat(subPrice);
        parcel.writeInt(totalQtyInCart);
    }
}
