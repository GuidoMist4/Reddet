package net.bcsoft.com.Reddet.service;

import net.bcsoft.com.Reddet.DTO.SubReddetDTO;
import net.bcsoft.com.Reddet.model.SubReddet;
import net.bcsoft.com.Reddet.repository.SubReddetRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

@Service
@Mapper
public class SubReddetService {

    private SubReddetRepo subReddetRepo;

    @Mapping(target = "subreddet")
    public SubReddetDTO saveSubReddet(SubReddetDTO subReddetDTO){
        return subReddetDTO;
    }
}
