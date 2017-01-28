package ch.foodchain.meattracking.transfer;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.sql.Blob;

public class FarmDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotNull
    private String address;

    @NotNull
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
