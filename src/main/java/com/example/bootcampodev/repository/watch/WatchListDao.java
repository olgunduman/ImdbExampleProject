package com.example.bootcampodev.repository.watch;

import com.example.bootcampodev.entity.WatchListEntity;

import java.util.List;

public interface WatchListDao {
    WatchListEntity create(WatchListEntity watchListEntity);

    WatchListEntity retrieve(Long id);

    List<WatchListEntity> findAll();
}
