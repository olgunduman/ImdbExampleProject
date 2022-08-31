package com.example.bootcampodev.service.watch;

import com.example.bootcampodev.entity.MemberEntity;
import com.example.bootcampodev.entity.WatchListEntity;
import com.example.bootcampodev.repository.member.MemberDao;
import com.example.bootcampodev.repository.watch.WatchListDao;
import com.example.bootcampodev.service.watch.WatchList;
import com.example.bootcampodev.service.watch.WatchListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListServiceImpl implements WatchListService {

    private final WatchListDao watchListDao;

    private final MemberDao memberDao;

    public WatchListServiceImpl(WatchListDao watchListDao, MemberDao memberDao) {
        this.watchListDao = watchListDao;
        this.memberDao = memberDao;
    }


    @Override
    public WatchList create(WatchList watchList) {
        MemberEntity memberEntity = memberDao.retrieve(watchList.getMemberId());
        var watchListMovieEntity = watchList.convertToRateEntity(memberEntity);
        watchListDao.create(watchListMovieEntity);
        return watchList;
    }

    @Override
    public WatchList retrieve(Long id) {
        WatchListEntity watchListEntity = watchListDao.retrieve(id);
        return WatchList.convertToWatchListEntity(watchListEntity);
    }

    @Override
    public List<WatchList> findAll() {
            return watchListDao.findAll().stream()
                    .map(WatchList::convertToWatchListEntity)
                    .collect(Collectors.toList());
    }
}
