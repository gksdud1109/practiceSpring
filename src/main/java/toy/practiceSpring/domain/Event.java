package toy.practiceSpring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name="EVENT")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동생성
    @Column(name="EVENT_ID")
    private Long id;

    // 행사명
    private String eventName;

    // TemporalType.DATE 날짜
    // TemporalType.TIME 시간
    // TemporalType.TIMESTAMP 날짜와 시간
    @Temporal(TemporalType.DATE)
    private Date startDate;

    // participation과 다대일 매핑
    @OneToMany(mappedBy = "event")
    @JsonManagedReference
    private List<Participation> participationList = new ArrayList<>();

    /*//다대다 매핑
    // 멤버테이블과 다대다 매핑 -> event테이블에 참여 member의 ID를 입력해야 된다.
    @ManyToMany(mappedBy = "events")
    private List<Member> members = new ArrayList<Member>();*/

    /*
    @ManyToMany
    @JoinTable(name = "MEMBER",
            joinColumns = @JoinColumn(name = "EVENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Member> members = new ArrayList<Member>();*/
}
