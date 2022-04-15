package toy.practiceSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import toy.practiceSpring.domain.Event;
import toy.practiceSpring.domain.EventForm;
import toy.practiceSpring.domain.Member;
import toy.practiceSpring.service.EventService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    //이벤트 추가
    @RequestMapping(value = "/events/new", method = RequestMethod.GET)
    public String createForm() {
        return "events/createEventForm";
    }

    @RequestMapping(value = "/events/new", method = RequestMethod.POST)
    public String create(EventForm form) {
        Event event = new Event();
        event.setEventName(form.getName());
        event.setStartDate(form.getStartDate());
        eventService.join(event);
        return "redirect:/";
    }

    //행사 리스트 출력
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String list(Model model) {
        List<Event> events = eventService.findEvents();
        model.addAttribute("events", events);
        return "events/eventList";
    }

    //행사 api로 반환
    @RequestMapping(value="/events/{id}", method=RequestMethod.GET)
    @ResponseBody()
    public Event eventApi(@PathVariable Long id) {
        Event event = eventService.findOne(id);
        return event;
    }


}
