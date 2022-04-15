package toy.practiceSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import toy.practiceSpring.domain.Event;
import toy.practiceSpring.domain.Member;
import toy.practiceSpring.domain.MemberForm;
import toy.practiceSpring.domain.Tag;
import toy.practiceSpring.service.MemberService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/members/new", method = RequestMethod.GET)
    public String createForm() {
        return "members/createMemberForm";
    }

    @RequestMapping(value = "/members/new", method = RequestMethod.POST)
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @RequestMapping(value = "/members", method = RequestMethod.GET)
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @RequestMapping(value="/members/{id}", method=RequestMethod.GET)
    @ResponseBody()
    public Member memberApi(@PathVariable Long id) {
        Member member = memberService.findOne(id);
        return member;
    }

    @RequestMapping(value="/members/userInfo/{id}", method=RequestMethod.GET)
    public String editUserInfo(@PathVariable Long id, Model model){
        List<Tag> tags = memberService.getAllTags();
        model.addAttribute("tags", tags);
        return "members/userInfo";
    }

    @RequestMapping(value = "/members/userInfo/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable Long id, List<Long> selectedTags) {
        Member member = memberService.findOne(id);
        List<Tag> selectTags = new ArrayList<>();
        for ( Long tagId: selectedTags) {
            selectTags.add(memberService.findTagById(tagId));
        }
        member.setTags(selectTags);
        return "redirect:/";
    }
}