package com.gradleJSP.demo.service;

import com.gradleJSP.demo.entity.People;
import com.gradleJSP.demo.repository.PeopleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public List<People> selectAll(){
        return peopleRepository.findAll();
    }

    public Optional<People> selectById(Long id) {
        return peopleRepository.findById(id);
    }

    public People insert(People people){
        return peopleRepository.save(people);
    }

    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }
    @Transactional
    public void update(Long id, People people) {
        People p = peopleRepository.findById(id).get();
        p.setName(people.getName());
        p.setAge(people.getAge());
    }

}
