package com.restfinal.configuration;

import com.restfinal.domain.Vehicle;
import com.restfinal.domain.VehicleMake;
import com.restfinal.domain.VehicleModel;
import com.restfinal.services.VehicleMakeService;
import com.restfinal.services.VehicleModelService;
import com.restfinal.services.VehicleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    private VehicleMakeService vehicleMakeService;

    private VehicleModelService vehicleModelService;

    private VehicleService vehicleService;

    public SeedData(VehicleMakeService vehicleMakeService, VehicleModelService vehicleModelService, VehicleService vehicleService) {
        this.vehicleMakeService = vehicleMakeService;
        this.vehicleModelService = vehicleModelService;
        this.vehicleService = vehicleService;
    }

    @Override
    public void run(String... args) throws Exception {
        //makes
        VehicleMake ford = new VehicleMake("Ford");
        VehicleMake infiniti = new VehicleMake("Infiniti");

        //ford models
        VehicleModel mustang = new VehicleModel("Mustang");
//        mustang.setVehicleMake(ford);
        VehicleModel f150 = new VehicleModel("F-150");
//        f150.setVehicleMake(ford);
        VehicleModel escape = new VehicleModel("Escape");
//        escape.setVehicleMake(ford);
        VehicleModel f350 = new VehicleModel("F-350");
//        f350.setVehicleMake(ford);

        //infiniti models
        VehicleModel q60 = new VehicleModel("Q60");
//        q60.setVehicleMake(infiniti);
        VehicleModel q50 = new VehicleModel("Q50");
//        q50.setVehicleMake(infiniti);
        VehicleModel g35 = new VehicleModel("G35");
//        g35.setVehicleMake(infiniti);
        VehicleModel q70 = new VehicleModel("Q70");
//        q70.setVehicleMake(infiniti);

        List<VehicleModel> fordModels = new ArrayList<>();
        fordModels.add(mustang);
        fordModels.add(f150);
        fordModels.add(escape);
        fordModels.add(f350);

        ford.setModelList(fordModels);

        List<VehicleModel> infinitiModels = new ArrayList<>();
        infinitiModels.add(q60);
        infinitiModels.add(q50);
        infinitiModels.add(q70);
        infinitiModels.add(g35);

        infiniti.setModelList(infinitiModels);


        Vehicle vehicle1 = new Vehicle();
        vehicle1.setLicensePlate("ESA-123");
        vehicle1.setVin("JN1CV6ER0A0014641");
        vehicle1.setColor("Red");
        vehicle1.setPurchasePrice("43000");
//        vehicle1.setVehicleModel(mustang);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setLicensePlate("CMA-109");
        vehicle2.setVin("1FMDU63EX2ZC00300");
        vehicle2.setColor("Blue");
        vehicle2.setPurchasePrice("23000");
//        vehicle2.setVehicleModel(q60);

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setLicensePlate("AFS-420");
        vehicle3.setVin("JN1CV6EP0A0013908");
        vehicle3.setColor("Gray");
        vehicle3.setPurchasePrice("35000");
//        vehicle3.setVehicleModel(f350);

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setLicensePlate("YUR-554");
        vehicle4.setVin("3FTWW33Y070015086");
        vehicle4.setColor("Black");
        vehicle4.setPurchasePrice("34000");
//        vehicle4.setVehicleModel(g35);

        List<Vehicle> vehicleList1 = new ArrayList<>();
        vehicleList1.add(vehicle1);

        List<Vehicle> vehicleList2 = new ArrayList<>();
        vehicleList2.add(vehicle2);

        List<Vehicle> vehicleList3 = new ArrayList<>();
        vehicleList3.add(vehicle3);

        List<Vehicle> vehicleList4 = new ArrayList<>();
        vehicleList4.add(vehicle4);


        mustang.setVehicleList(vehicleList1);
        q60.setVehicleList(vehicleList2);
        f350.setVehicleList(vehicleList3);
        g35.setVehicleList(vehicleList4);

        vehicleMakeService.saveMake(ford);
        vehicleMakeService.saveMake(infiniti);

    }
}
