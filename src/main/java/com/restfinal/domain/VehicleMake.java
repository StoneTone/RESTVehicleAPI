package com.restfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class VehicleMake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer makeId;

    @Column(unique = true)
    private String makeName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JsonIgnore
    private List<VehicleModel> modelList;

    public VehicleMake(){}

    public VehicleMake(String makeName) {
        this.makeName = makeName;
    }

    //region GETTER / SETTER

    public Integer getMakeId() {
        return makeId;
    }

    public void setMakeId(Integer makeId) {
        this.makeId = makeId;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public List<VehicleModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<VehicleModel> modelList) {
        this.modelList = modelList;
    }

    //endregion
}
