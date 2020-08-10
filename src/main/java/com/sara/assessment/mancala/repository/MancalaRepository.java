package com.sara.assessment.mancala.repository;

import com.sara.assessment.mancala.model.MancalaGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MancalaRepository extends MongoRepository<MancalaGame, String> {
}
