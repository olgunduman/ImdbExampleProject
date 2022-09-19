package com.example.bootcampodev.domain.watch;


import com.example.bootcampodev.domain.member.Member;
import com.example.bootcampodev.domain.port.MemberPersistencePort;
import com.example.bootcampodev.domain.port.WatchListPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WatchListServiceImpl implements WatchListService {

    private final WatchListPersistencePort watchListPersistencePort;

    //private final MemberPersistencePort memberPersistencePort;




    @Override
    public WatchList create(WatchList watchList) {
      //  Member member = memberPersistencePort.retrieve(watchList.getMemberId());
       // var watchListMovieEntity = watchList.convertToRateEntity(member);
       // watchListPersistencePort.create(watchListMovieEntity);
        return watchListPersistencePort.create(watchList);

    }

    @Override
    public WatchList retrieve(Long id) {return watchListPersistencePort.retrieve(id);
    }

    @Override
    public List<WatchList> findAll() {
            return watchListPersistencePort.findAll();
    }
}
