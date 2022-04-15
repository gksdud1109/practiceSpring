package toy.practiceSpring.repository;

import org.springframework.stereotype.Repository;
import toy.practiceSpring.domain.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {

    @PersistenceContext
    EntityManager em;

    public Event save(Event event) {
        em.persist(event);

        return event;
    }

    // id로 조회
    public Event findById(Long id) {
        return em.find(Event.class, id);
    }

    // 전체 조회
    public List<Event> findAll() {
        return em.createQuery("select e from Event e", Event.class)
                .getResultList();
    }

    // 이름으로 조회
    public Optional<Event> findByName(String name) {
        List<Event> result = em.createQuery("select e from Event m where e.name = :name", Event.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}