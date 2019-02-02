package com.example.geoquiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Player implements Parcelable {
    String name;
    int score;

    public Player() {
    }

   /* public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return " Name: " + name +"; Score: " + score ;
    }

    Player(Parcel in) {
        name = in.readString();
        score = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(score);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}