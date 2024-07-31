package com.toukirahmed.crudapisingers.service;

import com.toukirahmed.crudapisingers.entity.Singers;

import java.util.List;

public interface SingersService {
    public Integer saveSingers(Singers singers);
    public void update(Singers singers);
    public void delete(Integer id);
    public Singers getOneSinger(Integer id);
    public List<Singers> getAllSingers();
    public boolean isAvailable(Integer id);
}
