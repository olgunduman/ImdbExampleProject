package com.example.bootcampodev.repository.matching;

import com.example.bootcampodev.entity.ActorEntity;
import com.example.bootcampodev.entity.MatchingEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchingDaoImpl implements MatchingDao{
    private final MatchingJpaRepository matchingJpaRepository;
    @Override
    public void create(List<MatchingEntity> matchingEntities) {
        matchingJpaRepository.saveAll(matchingEntities);
    }

    @Override
    public List<ActorEntity> retrieveActors(Long movieId) {
        return matchingJpaRepository.findAllByMovie_Id(movieId)
                .stream().map(matchingEntity -> matchingEntity.getActor())
                .collect(Collectors.toList());
    }
}
