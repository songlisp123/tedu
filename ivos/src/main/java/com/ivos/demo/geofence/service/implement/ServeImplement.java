package com.ivos.demo.geofence.service.implement;

import com.ivos.demo.base.exception.serviceException;
import com.ivos.demo.base.response.StatusCode;
import com.ivos.demo.geofence.mapper.GeoMapper;
import com.ivos.demo.geofence.pojo.dto.GeofenceQuery;
import com.ivos.demo.geofence.pojo.dto.GeofenceSavePara;
import com.ivos.demo.geofence.pojo.entity.Geofence;
import com.ivos.demo.geofence.pojo.vo.GeofenceVo;
import com.ivos.demo.geofence.service.Serve;
import com.ivos.demo.vehicle.mapper.vehicleMapper;
import com.ivos.demo.vehicle.pojo.dto.vehicleQuery;
import com.ivos.demo.vehicle.pojo.vo.VehicleVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ServeImplement implements Serve {

    @Autowired
    private GeoMapper mapper;

    @Autowired
    private vehicleMapper vehicleMapper;

    @Override
    public List<GeofenceVo> selectGeofence(GeofenceQuery query) {
        log.debug("业务层层面参数:{}",query);

        List<GeofenceVo> response = mapper.selectGeofence(query);
        for (GeofenceVo vo : response) {
            vehicleQuery vehicleQuery = new vehicleQuery();
            vehicleQuery.setGeofenceId(vo.getId());
            List<VehicleVo> vehicleVos =
                    vehicleMapper.selectCarByLicenseAndBrand(vehicleQuery);
            int availableNum = vehicleVos.size();
            vo.setTotalNum(vehicleVos.size());
            for (VehicleVo vehicleVo : vehicleVos) {
                if (!vehicleVo.getStatus().equals("1")) {
                    availableNum = vehicleVos.size() - 1;
                }
            }
            vo.setAvailableNum(availableNum);
            vo.setVoList(vehicleVos);
        }
        return response;
    }

    @Override
    public void updateStatus(Long geoId, String status) {
        log.debug("进入到业务层，{},{}",geoId,status);
        Geofence geofence = new Geofence();
        geofence.setId(geoId);
        geofence.setStatus(status);
        geofence.setUpdateTime(new Date());
        mapper.update(geofence);

    }


    @Override
    public void delete(Long geoId) {
        log.debug("业务层参数:{}",geoId);
        vehicleQuery query = new vehicleQuery();
        query.setGeofenceId(geoId);
        //调用汽车mapper方法
        List<VehicleVo> vehicleVos
                = vehicleMapper.selectCarByLicenseAndBrand(query);

        if (!vehicleVos.isEmpty()) {
            throw new serviceException(StatusCode.GEOFENCE_CAR_EXISTS);
        }

        mapper.delete(geoId);
    }

    @Override
    public void save(GeofenceSavePara para) {
        log.debug("业务层参数：{}",para);
        Geofence geofence = new Geofence();
        BeanUtils.copyProperties(para,geofence);
        geofence.setStatus("0");
        geofence.setCreateTime(new Date());
        geofence.setUpdateTime(new Date());
        mapper.save(geofence);
    }
}
