package com.example.demo.controller;

import com.example.demo.dto.CreateTariffDto;
import com.example.demo.dto.TariffDto;
import com.example.demo.entity.TariffEntity;
import com.example.demo.mapper.TariffMapper;
import com.example.demo.service.TariffService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;


@RestController
@RequiredArgsConstructor
@RequestMapping("/tariff")
public class TariffController {

    private final TariffService tariffService;
    private final TariffMapper tariffMapper;

    TariffController( TariffService tariffService1, TariffMapper tariffMapper1)
    {
        this.tariffService = tariffService1;
        this.tariffMapper = tariffMapper1;

    }

    @PostMapping("/create")
    public ResponseEntity<?> createTariff(@RequestBody CreateTariffDto createTariffDto)  throws Exception{
        TariffEntity entity = tariffMapper.createTariffDtoToTariffEntity(createTariffDto);
        Integer tariffId = tariffService.createTariff(entity);
        return ResponseEntity.ok(tariffId);
    }

    @GetMapping("/{tariffId}")
    public ResponseEntity<?>  getTariffByTariffId(@PathVariable Integer tariffId) {
        TariffDto tariffDto  = tariffMapper.tariffEntityToTariffDto(tariffService.getTariff(tariffId));
        return new ResponseEntity<>(tariffDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTariff(
            @RequestBody CreateTariffDto createTariffDto,
            @PathVariable String id
    ) {
        tariffService.changeTariff(createTariffDto, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTariff(@PathVariable("id") String id) throws Exception {
        tariffService.deleteTariff(id);
        return ResponseEntity.ok().build();
    }

}
