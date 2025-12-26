package com.example.demo.service;

import com.example.demo.dto.CreateTariffDto;
import com.example.demo.entity.TariffEntity;
import com.example.demo.kafka.KafkaSender;
import com.example.demo.repository.TariffRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
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

    public Integer createTariff(TariffEntity tariffEntity) throws Exception {
        Integer id = (tariffRepository.save(tariffEntity)).getId();
        KafkaSender.sendMessage(id.toString(), "created");
        return id;
    }

    public void changeTariff(CreateTariffDto createTariffDto, String id) {
        TariffEntity tariffEntity = tariffRepository.findById(id).orElseThrow(() -> new RuntimeException("Tariff not found"));
        tariffEntity.setName(createTariffDto.getName());
        tariffEntity.setConditions(createTariffDto.getConditions());
        tariffRepository.save(tariffEntity);
    }

    public void deleteTariff(String id) throws Exception {
        tariffRepository.deleteById(id);
        KafkaSender.sendMessage(id.toString(), "deleted");
    }

}
