package com.training.service;

import com.training.dao.PagesRepository;
import com.training.model.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

/**
 * Created by Mschneider on 04-06-17.
 */
@Component
public class PagesServiceImpl implements PagesService {
    @Autowired
    private PagesRepository pagesRepository;

    @Override
    public Collection<Pages> list() {
        Collection<Pages> pages = pagesRepository.findAll();
        return pages;
    }

    @Override
    public Pages get(Long id) {

        return pagesRepository.findOne(id);
    }

    @Override
    public Pages create(Pages pages) {
        if (pages.getId() != null) {
            return null;
        }
        else {
            return pagesRepository.save(pages);
        }
    }

    @Override
    public Pages update(Pages pages) {
        if (pages.getId() == null) {
            return null;
        } else {
            return pagesRepository.save(pages);
        }
    }

    @Override
    public boolean delete(Long id) {
        pagesRepository.delete(id);
        return true;
    }

    @Override
    public void topFivePages(){
        List<Object[]> soldPages=pagesRepository.totalSoldPages();
        Map<String, Long> map = new HashMap<>();
        for (Object o[] : soldPages) {
            Pages page = (Pages) o[0];
            long quantity = (long) o[1];
            //all the classes: Course, Lesson, Progress and User have the toString() overridden with the database ID;
            if(map.get(page.getTitle())==null){
                map.put(page.getTitle(),quantity);
            }
            else{
                map.put(page.getTitle(),map.get(page.getTitle())+quantity);
            }

        }
        Set<Map.Entry<String, Long>> set = map.entrySet();
        List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Long>>()
        {
            public int compare( Map.Entry<String, Long> o1, Map.Entry<String, Long> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        for(Map.Entry<String, Long> entry:list){
            System.out.println(entry.getKey()+" ==== "+entry.getValue());
        }

    }
}
