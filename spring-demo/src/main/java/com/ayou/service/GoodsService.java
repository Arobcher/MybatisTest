package com.ayou.service;

import com.ayou.pojo.Goods;
import com.ayou.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoodsService {
    @Autowired
    GoodsRepository goodsRepository;


    public List<Goods> findAll() {
        return goodsRepository.findAll();
    }
    
    public Optional<Goods> findById(Integer id) {
        return goodsRepository.findById(id);
    }
}
