package toy.practiceSpring.repository;

import org.springframework.stereotype.Repository;
import toy.practiceSpring.domain.Member;
import toy.practiceSpring.domain.Participation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ParticipationRepository {
    @PersistenceContext
    EntityManager em;

    public Participation save(Participation participation) {
        em.persist(participation);
        return participation;
    }

    public Optional<Participation> findById(Long id) {
        Participation participation = em.find(Participation.class, id);
        return Optional.ofNullable(participation);
    }

    public List<Participation> findAll() {
        return em.createQuery("select p from Participation p", Participation.class)
                .getResultList();
    }
}
