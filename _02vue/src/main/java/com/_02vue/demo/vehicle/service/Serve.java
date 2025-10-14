package com._02vue.demo.vehicle.service;


import com._02vue.demo.vehicle.pojo.dto.RegVehiclePara;
import com._02vue.demo.vehicle.pojo.dto.VehicleQuery;
import com._02vue.demo.vehicle.pojo.vo.VehicleVo;

import java.util.List;

public interface Serve {
    void save(RegVehiclePara para);

    List<VehicleVo> select(VehicleQuery query);

    void delete(Long vehicleId);
}
