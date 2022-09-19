package com.example.bootcampodev.domain.port;
import com.example.bootcampodev.domain.watch.WatchList;

import java.util.List;

public interface WatchListPersistencePort {
    WatchList create(WatchList watchList);

    WatchList retrieve(Long id);

    List<WatchList> findAll();
}
