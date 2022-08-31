package com.example.bootcampodev.service.watch;

import java.util.List;

public interface WatchListService {
   WatchList create(WatchList watchList);

    WatchList retrieve(Long id);

    List<WatchList> findAll();
}
