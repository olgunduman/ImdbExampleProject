package com.example.bootcampodev.repository.matching;

import com.example.bootcampodev.entity.MatchingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingDaoImpl implements MatchingDao{
    private final MatchingJpaRepository matchingJpaRepository;
    @Override
    public void create(List<MatchingEntity> matchingEntities) {
        matchingJpaRepository.saveAll(matchingEntities);
    }
}
