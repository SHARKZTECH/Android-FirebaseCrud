package com.example.firebasecrud;

import android.icu.text.Replaceable;
import android.os.Parcel;
import android.os.Parcelable;

public class Courses implements Parcelable {
    private String name;
    private String description;
    private String price;
    private String suite;
    private String image;
    private String link;
    private String id;

    public Courses() {
    }

    public Courses(String name, String description, String price, String suite, String image, String link, String id) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.suite = suite;
        this.image = image;
        this.link = link;
        this.id = id;
    }

    protected Courses(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readString();
        suite = in.readString();
        image = in.readString();
        link = in.readString();
        id = in.readString();
    }

    public static final Creator<Courses> CREATOR = new Creator<Courses>() {
        @Override
        public Courses createFromParcel(Parcel in) {
            return new Courses(in);
        }

        @Override
        public Courses[] newArray(int size) {
            return new Courses[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getSuite() {
        return suite;
    }

    public String getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(price);
        parcel.writeString(suite);
        parcel.writeString(image);
        parcel.writeString(link);
        parcel.writeString(id);
    }
}
