package jdulal.com.np.androidshopapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataBarang implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("stok")
    @Expose
    private String stok;

    protected DataBarang(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        id = in.readInt();
        nama = in.readString();
        harga = in.readString();
        stok = in.readString();
    }

    public static final Creator<DataBarang> CREATOR = new Creator<DataBarang>() {
        @Override
        public DataBarang createFromParcel(Parcel in) {
            return new DataBarang(in);
        }

        @Override
        public DataBarang[] newArray(int size) {
            return new DataBarang[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getStok() {
        return stok;
    }

    public void setStok(String stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "data{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", harga='" + harga + '\'' +
                ", stok='" + stok + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(harga);
        dest.writeString(stok);
    }
}