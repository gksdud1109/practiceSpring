package toy.practiceSpring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.practiceSpring.domain.Member;
import toy.practiceSpring.domain.Tag;
import toy.practiceSpring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 태그테이블 가져오기
    public List<Tag> getAllTags() {
        return memberRepository.getAllTags();
    }

    public Tag findTagById(Long id) {
        return memberRepository.findTagById(id);
    }

    // 멤버 정보 수정
    public void editMember(Member member) {
        memberRepository.save(member);
    }
}