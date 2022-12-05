package net.bcsoft.com.Reddet.controller;

import lombok.AllArgsConstructor;
import net.bcsoft.com.Reddet.DTO.SubReddetDTO;
import net.bcsoft.com.Reddet.service.SubReddetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Api/SubReddet")
public class SubReddetController {

    private SubReddetService subReddetService;

    @PostMapping("/save")
    public ResponseEntity<SubReddetDTO> saveSubReddet(@RequestBody SubReddetDTO subReddetDTO){
        //subReddetService.saveSubReddet(subReddetDTO);
        //return  new ResponseEntity<>(subReddetDTO, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(subReddetService.saveSubReddet(subReddetDTO));
    }
    @GetMapping("/GetAllSubReddet")
    public ResponseEntity<List<SubReddetDTO>> getAllSubReddet() {
        List<SubReddetDTO> subReddetDTOList = subReddetService.getAllSubReddet();
        return new ResponseEntity<>(subReddetDTOList, HttpStatus.OK);
    }
    @GetMapping("/subReddetGetById/{id}")
    public ResponseEntity<SubReddetDTO> subReddetGetById(@PathVariable long id){
        SubReddetDTO subReddetDTO = subReddetService.subReddetGetById(id);
        return new ResponseEntity<>(subReddetDTO, HttpStatus.OK);
    }

}
