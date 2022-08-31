package com.example.bootcampodev.repository.matching;

import com.example.bootcampodev.entity.MatchingEntity;

import java.util.List;

public interface MatchingDao {
    void create(List<MatchingEntity> matchingEntities);
}
