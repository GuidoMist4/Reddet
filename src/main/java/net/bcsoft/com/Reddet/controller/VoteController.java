package net.bcsoft.com.Reddet.controller;

import lombok.AllArgsConstructor;
import net.bcsoft.com.Reddet.DTO.VoteDTO;
import net.bcsoft.com.Reddet.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    //restituisce voto
    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDTO voteDTO) {
        voteService.vote(voteDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}