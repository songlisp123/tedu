package com.ivos.demo.vehicle.vehicleImpl;

import com.ivos.demo.vehicle.mapper.vehicleMapper;
import com.ivos.demo.vehicle.pojo.dto.saveVehiclePara;
import com.ivos.demo.vehicle.pojo.dto.vehicleQuery;
import com.ivos.demo.vehicle.pojo.entity.Vehicle;
import com.ivos.demo.vehicle.pojo.vo.VehicleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class vehicleServiceImp implements vehicleService {

    @Autowired
    private vehicleMapper mapper;

    @Override
    public List<VehicleVo> selectCarByLicenseAndBrand(vehicleQuery vehicleQuery) {
       //测试
        log.debug("进入到业务层……");
        List<VehicleVo> vehicleVos = mapper.selectCarByLicenseAndBrand(vehicleQuery);
        return vehicleVos;
    }

    @Override
    public void save(saveVehiclePara saved) {
        log.debug("进入到业务层，参数：{}",saved);
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(saved,vehicle);

        if (saved.getId() == null ) {
            vehicle.setUpdateTime(new Date());
            vehicle.setCreateTime(new Date());
            vehicle.setGeofenceBindStatus("0");
            vehicle.setStatus("1");
            mapper.save(vehicle);
        }
        else {
            vehicle.setUpdateTime(new Date());
            mapper.update(vehicle);
        }
    }

    @Override
    public void delete(Long vehicleId) {
        log.debug("业务层：{}",vehicleId);
        mapper.delete(vehicleId);
    }
}
