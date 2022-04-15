package toy.practiceSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.practiceSpring.domain.Event;
import toy.practiceSpring.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    /**
     * 행사등록
     */
    public Long join(Event event) {

        validateDuplicateMember(event); //중복 회원 검증
        eventRepository.save(event);
        return event.getId();
    }

    private void validateDuplicateMember(Event event) {
        eventRepository.findByName(event.getEventName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 행사 조회
     */
    public List<Event> findEvents() {
        return eventRepository.findAll();
    }
    public Event findOne(Long eventId) {
        return eventRepository.findById(eventId);
    }

}
