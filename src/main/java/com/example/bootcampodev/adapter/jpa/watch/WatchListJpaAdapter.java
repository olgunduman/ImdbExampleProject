package com.example.bootcampodev.adapter.jpa.watch;

import com.example.bootcampodev.domain.port.WatchListPersistencePort;
import com.example.bootcampodev.domain.watch.WatchList;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListJpaAdapter implements WatchListPersistencePort {

    private final WatchListJpaRepository watchListJpaRepository;

    public WatchListJpaAdapter(WatchListJpaRepository watchListJpaRepository) {
        this.watchListJpaRepository = watchListJpaRepository;
    }

    @Override
    public WatchList create(WatchList watchList) {
       var ceatedWatchList =  watchListJpaRepository.save(WatchListEntity.from(watchList));
         return ceatedWatchList.toModel();
    }

    @Override
    public WatchList retrieve(Long id) {

        WatchListEntity retrieveWatchList = watchListJpaRepository.findById(id).orElseThrow(() -> new NotFoundException("Id bulunamadı"));
        return retrieveWatchList.toModel();
    }

    @Override
    public List<WatchList
            > findAll() {
        return watchListJpaRepository.findAll()
                .stream()
                .map(WatchListEntity::toModel)
                .collect(Collectors.toList());
    }
}
