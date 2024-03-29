package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    * 회원가입
    * */
    public long join(Member member) {
//        long start = System.currentTimeMillis();
//
//        try {
            validateDuplicateMember(member); // 중복회원 검증
            memberRepository.save(member);
            return member.getId();
//        }
//        finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish-start;
//            System.out.println("join = " + timeMs + "ms");
//        }
    }

    // 서비스는 비즈니스 로직스러운 이름을 써야함
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
