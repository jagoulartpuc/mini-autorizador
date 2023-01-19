package vr.miniautorizador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import vr.miniautorizador.domain.Card;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {
}
