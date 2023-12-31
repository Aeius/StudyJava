package com.gradleJSP.demo.service;

import com.gradleJSP.demo.entity.People;
import com.gradleJSP.demo.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    public List<People> selectAll(){
        List<People> list = peopleRepository.findAll();
        list.sort(new PeopleComparator()); // num기준 오름차순 정렬
        return list;
    }

    @Transactional
    public Optional<People> selectById(Long id) {
        return peopleRepository.findById(id);
    }

    @Transactional
    public People insert(People people){
        return peopleRepository.save(people);
    }

    @Transactional
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, People people) {
        People p = peopleRepository.findById(id).get();
        p.setName(people.getName());
        p.setAge(people.getAge());
    }

    public long getRowCount() {
        return peopleRepository.count();
    }

    public long getColCount() {
        return peopleRepository.getColCount();
    }

    class PeopleComparator implements Comparator<People> {
        @Override
        public int compare(People o1, People o2) {
            if(o1.getNum() > o2.getNum()) {
                return 1;
            } else if (o1.getNum() < o2.getNum()){
                return -1;
            }
            return 0;
        }
    }


}
