package com.cloudCar.demo.mapper;

import com.cloudCar.demo.pojo.DTO.VehicleListQuery;
import com.cloudCar.demo.pojo.entity.Car;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface carMapper {
    int insertCar(Car car);

    List<Car> selectAllCar(
            @Param("query") VehicleListQuery vehicleListQuery,
            @Param("userId") Long userId);

    int deleteCarById(Long id, Long userId);
}
