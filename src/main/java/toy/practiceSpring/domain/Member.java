package toy.practiceSpring.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Table(name="MEMBER")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동생성
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="NAME")
    private String name;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Participation> participationList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<Tag> tags = new ArrayList<Tag>();

    /*// 다대다 매핑
    // Event테이블과 다대다 매핑, 연관관계 주인
    @ManyToMany
    @JoinTable(name = "EVENT",
            joinColumns = @JoinColumn(name = "MEMBER_ID"),
            inverseJoinColumns = @JoinColumn(name = "EVENT_ID"))
    private List<Event> events = new ArrayList<Event>();*/


    /* @ManyToMany(mappedBy = "members")
    private List<Event> events = new ArrayList<Event>();*/
}
