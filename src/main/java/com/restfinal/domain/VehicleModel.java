package com.restfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modelId;

    @Column(unique = true)
    private String modelName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
    List<Vehicle> vehicleList;

//    @ManyToOne
//    VehicleMake vehicleMake;

    public VehicleModel(){}

    public VehicleModel(String modelName) {
        this.modelName = modelName;
    }

    //region GETTER / SETTER


    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    //endregion
}
