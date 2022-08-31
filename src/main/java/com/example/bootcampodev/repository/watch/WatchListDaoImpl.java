package com.example.bootcampodev.repository.watch;

import com.example.bootcampodev.entity.WatchListEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class WatchListDaoImpl implements WatchListDao {

    private final WatchListJpaRepository watchListJpaRepository;

    public WatchListDaoImpl(WatchListJpaRepository watchListJpaRepository) {
        this.watchListJpaRepository = watchListJpaRepository;
    }

    @Override
    public WatchListEntity create(WatchListEntity watchListEntity) {
       return watchListJpaRepository.save(watchListEntity);
    }

    @Override
    public WatchListEntity retrieve(Long id) {

        WatchListEntity watchListEntity = watchListJpaRepository.findById(id).orElseThrow(() -> new NotFoundException("Id bulunamadı"));
        return watchListEntity;
    }

    @Override
    public List<WatchListEntity> findAll() {
        return watchListJpaRepository.findAll();
    }
}
