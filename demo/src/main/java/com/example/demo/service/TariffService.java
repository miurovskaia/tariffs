package com.example.demo.service;

import com.example.demo.dto.CreateTariffDto;
import com.example.demo.entity.TariffEntity;
import com.example.demo.repository.TariffRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class TariffService {
    private final TariffRepository tariffRepository;

    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public TariffEntity getTariff(Integer id) {
        TariffEntity tariff = tariffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tariff not found"));
        return tariff;
    }


    //edit
    public Set<TariffEntity> searchByString(String searchString){
        String[] searchWords = searchString.split(" ");
        List<String> searchWordsList = Arrays.asList(searchWords);
        Set<TariffEntity> tariffEntitySet = new HashSet<>();

        tariffEntitySet.addAll(tariffRepository.findAllByNameIn(searchWordsList));

        return tariffEntitySet;
    }

    public Integer createTariff(TariffEntity tariffEntity) {
        return (tariffRepository.save(tariffEntity)).getId();
    }

    public void changeTariff(CreateTariffDto createTariffDto, String id) {
        TariffEntity tariffEntity = tariffRepository.findById(id).orElseThrow(() -> new RuntimeException("Tariff not found"));
        tariffEntity.setName(createTariffDto.getName());
        tariffEntity.setType(createTariffDto.getType());
        tariffEntity.setConditions(createTariffDto.getConditions());
        tariffRepository.save(tariffEntity);
    }

    public void deleteTariff(String id) {
        tariffRepository.deleteById(id);
    }


}
