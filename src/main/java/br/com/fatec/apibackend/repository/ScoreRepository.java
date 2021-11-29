package br.com.fatec.apibackend.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;
import br.com.fatec.apibackend.entities.Score;

@Repository
public interface ScoreRepository extends JpaRepositoryImplementation<Score, Long> {

}
