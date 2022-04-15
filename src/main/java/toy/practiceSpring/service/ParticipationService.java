package toy.practiceSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toy.practiceSpring.domain.Event;
import toy.practiceSpring.domain.Member;
import toy.practiceSpring.domain.Participation;
import toy.practiceSpring.repository.EventRepository;
import toy.practiceSpring.repository.MemberRepository;
import toy.practiceSpring.repository.ParticipationRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ParticipationService {
    @Autowired MemberRepository memberRepository;
    @Autowired ParticipationRepository participationRepository;
    @Autowired EventService eventService;

    /** 행사 등록 */
    public Long enroll(Long memberId, Long eventId) {

        //엔티티 조회
        Member member = memberRepository.findById(memberId);
        Event event = eventService.findOne(eventId);

        //등록
        Participation participation = Participation.createParticipation(member, event);

        //주문 저장
        participationRepository.save(participation);
        return participation.getId();
    }

    /** 등록 전체 검색 */
    public List<Participation> findParticipation() {
        return participationRepository.findAll();
    }
}
