package toy.practiceSpring.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @ToString
@Table(name="PARTICIPATION")
public class Participation {

    @Id @GeneratedValue
    @Column (name = "PARTICIPATION_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    @JsonBackReference
    private Member member;      //주문 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID")
    @JsonBackReference
    private Event event;      //주문 회원

    public void setMember(Member member) {
        this.member = member;
        member.getParticipationList().add(this);
    }

    public void setEvent(Event event) {
        this.event = event;
        event.getParticipationList().add(this);
    }/**/

    // 멤버와 이벤트를 받아 등록
    public static Participation createParticipation(Member member, Event event) {

        Participation participation = new Participation();
        participation.setMember(member);
        participation.setEvent(event);

        return participation;
    }
}
