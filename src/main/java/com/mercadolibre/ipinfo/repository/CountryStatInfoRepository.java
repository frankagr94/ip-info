package com.mercadolibre.ipinfo.repository;

import com.mercadolibre.ipinfo.model.CountryStatInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryStatInfoRepository extends CrudRepository<CountryStatInfo,String> {
}
