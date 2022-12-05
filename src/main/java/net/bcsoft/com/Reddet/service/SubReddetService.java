package net.bcsoft.com.Reddet.service;


import lombok.AllArgsConstructor;
import net.bcsoft.com.Reddet.DTO.SubReddetDTO;
import net.bcsoft.com.Reddet.exception.ExceptionHandler;
import net.bcsoft.com.Reddet.mapper.SubReddetMapper;
import net.bcsoft.com.Reddet.model.SubReddet;
import net.bcsoft.com.Reddet.repository.SubReddetRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubReddetService {

    private final SubReddetRepo subReddetRepo;

    private final SubReddetMapper subReddetMapper;


    @Transactional
    public SubReddetDTO saveSubReddet(SubReddetDTO subReddetDTO)  {
        SubReddet subReddet = subReddetMapper.mapDTOToSubReddet(subReddetDTO);
        subReddetRepo.save(subReddet);
        subReddetDTO.setSubId(subReddet.getSubId());
        return subReddetDTO;
    }

    @Transactional
    public List<SubReddetDTO> getAllSubReddet() {
        List<SubReddetDTO> subReddetList = subReddetRepo
                .findAll()
                .stream()
                .map(subReddetMapper::mapSubReddetToDTO)
                .collect(Collectors.toList());
        return subReddetList;
    }

    @Transactional
    public SubReddetDTO subReddetGetById(long id){
       SubReddet subReddetDTOId = subReddetRepo.findById(id).orElseThrow(()->
               new ExceptionHandler("SubReddet not found with this id: "+id));
        return subReddetMapper.mapSubReddetToDTO(subReddetDTOId);
    }

}