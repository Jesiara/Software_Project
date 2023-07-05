package com.bawp.heartmonitor_software_project;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Userdets implements Parcelable {
    private  String date,time,systolic,diastolic,heartRate,comment;


    public Userdets(){

    }

    public Userdets(String date, String time, String systolic, String diastolic, String heartRate, String comment) {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    protected Userdets(Parcel in) {
        date = in.readString();
        time = in.readString();
        systolic = in.readString();
        diastolic = in.readString();
        heartRate = in.readString();
        comment = in.readString();
    }

    public static final Creator<Userdets> CREATOR = new Creator<Userdets>() {
        @Override
        public Userdets createFromParcel(Parcel in) {
            return new Userdets(in);
        }

        @Override
        public Userdets[] newArray(int size) {
            return new Userdets[size];
        }
    };
    public boolean isEqual(String date, String time, String systolic, String diastolic, String heartRate, String comment) {
        return this.date.equals(date) &&
                this.time.equals(time) &&
                this.systolic.equals(systolic) &&
                this.diastolic.equals(diastolic) &&
                this.heartRate.equals(heartRate) &&
                this.comment.equals(comment);
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(systolic);
        dest.writeString(diastolic);
        dest.writeString(heartRate);
        dest.writeString(comment);
    }


}
