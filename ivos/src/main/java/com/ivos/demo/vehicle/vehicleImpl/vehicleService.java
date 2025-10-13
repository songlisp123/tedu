package com.ivos.demo.vehicle.vehicleImpl;

import com.ivos.demo.vehicle.pojo.dto.saveVehiclePara;
import com.ivos.demo.vehicle.pojo.dto.vehicleQuery;
import com.ivos.demo.vehicle.pojo.vo.VehicleVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface vehicleService {
    List<VehicleVo> selectCarByLicenseAndBrand(vehicleQuery vehicleQuery);

    void save(saveVehiclePara saved);

    void delete(Long vehicleId);

    void unbind(Long vehicleId);

    void bind(Long geoId, Long vehicleId);
}
