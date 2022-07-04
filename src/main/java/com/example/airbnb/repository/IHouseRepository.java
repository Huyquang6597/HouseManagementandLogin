package com.example.airbnb.repository;

import com.example.airbnb.model.House;
import com.example.airbnb.model.House;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseRepository extends PagingAndSortingRepository<House, Long> {

}
