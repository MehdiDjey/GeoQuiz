package com.example.geoquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
    private static final String TAG = "Player";
    private String name;
    private int score;

    Player() {
    }

    private Player(Parcel in) {
        this.name = in.readString();
        this.score = in.readInt();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return " Player " + name + "  ||  score: " + score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.score);
    }
}