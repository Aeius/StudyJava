package com.gradleJSP.demo.repository;

import com.gradleJSP.demo.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

}
