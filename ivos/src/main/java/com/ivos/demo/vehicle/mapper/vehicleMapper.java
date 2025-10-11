package com.ivos.demo.vehicle.mapper;

import com.ivos.demo.vehicle.pojo.dto.vehicleQuery;
import com.ivos.demo.vehicle.pojo.entity.Vehicle;
import com.ivos.demo.vehicle.pojo.vo.VehicleVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface vehicleMapper {
    List<VehicleVo> selectCarByLicenseAndBrand(vehicleQuery vehicleQuery);

    void save(Vehicle vehicle);

    void update(Vehicle vehicle);

    void delete(Long vehicleId);
}
