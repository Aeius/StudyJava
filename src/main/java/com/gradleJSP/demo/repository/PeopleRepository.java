package com.gradleJSP.demo.repository;

import com.gradleJSP.demo.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
    @Query(value = "SELECT COUNT(*) FROM information_schema.columns WHERE table_name='people'", nativeQuery = true)
    public Long getColCount();
}
