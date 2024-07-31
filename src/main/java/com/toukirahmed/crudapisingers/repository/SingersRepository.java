package com.toukirahmed.crudapisingers.repository;

import com.toukirahmed.crudapisingers.entity.Singers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingersRepository extends JpaRepository<Singers, Integer> {

}
