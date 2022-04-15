package toy.practiceSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toy.practiceSpring.domain.Event;
import toy.practiceSpring.domain.Member;
import toy.practiceSpring.domain.Participation;
import toy.practiceSpring.service.EventService;
import toy.practiceSpring.service.MemberService;
import toy.practiceSpring.service.ParticipationService;

import java.util.List;

@Controller
public class ParticipationController {

    @Autowired MemberService memberService;
    @Autowired EventService eventService;
    @Autowired ParticipationService participationService;

    @RequestMapping(value = "/enroll", method = RequestMethod.GET)
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Event> events = eventService.findEvents();

        model.addAttribute("members", members);
        model.addAttribute("events", events);

        return "enroll/participationForm";
    }

    @RequestMapping(value = "/enroll", method = RequestMethod.POST)
    public String enroll(@RequestParam("memberId") Long memberId,
                        @RequestParam("eventId") Long eventId) {

        participationService.enroll(memberId, eventId);
        return "redirect:/enrolls";
    }

    @RequestMapping(value = "/enrolls", method = RequestMethod.GET)
    public String participationList(Model model) {

        List<Participation> participationList = participationService.findParticipation();
        model.addAttribute("participationList", participationList);

        return "enroll/participationList";
    }
}
