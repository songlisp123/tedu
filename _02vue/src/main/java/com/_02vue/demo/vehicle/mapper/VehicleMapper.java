package com._02vue.demo.vehicle.mapper;

import com._02vue.demo.vehicle.pojo.dto.VehicleQuery;
import com._02vue.demo.vehicle.pojo.entity.Vehicle;
import com._02vue.demo.vehicle.pojo.vo.VehicleVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMapper {
    void save(Vehicle vehicle);

    List<VehicleVo> select(VehicleQuery query);

    void update(Vehicle vehicle);

    void delete(Long vehicleId);
}
