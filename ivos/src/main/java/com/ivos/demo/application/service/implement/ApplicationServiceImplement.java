package com.ivos.demo.application.service.implement;

import com.ivos.demo.application.mapper.ApplicationMapper;
import com.ivos.demo.application.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ApplicationServiceImplement implements ApplicationService {

    @Autowired
    private ApplicationMapper mapper;
}
