package toy.practiceSpring.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class EventForm {
    private String name;

    private Date startDate;

    private Long memberId;
}
