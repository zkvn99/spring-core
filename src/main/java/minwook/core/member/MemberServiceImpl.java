package minwook.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; // 추상(인터페이스)에만 의존 (DIP 준수)
    // 위에 어떤 구현 객체가 들어올지(주입될지)는 알 수 없음
    // 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중


    // MemberServiceImpl 을 컴포넌트 스캔으로 빈 등록을 하면
    // 의존관계 설정이 안됨, @Autowired로 자동 의존관계 설정
    // ac.getBean(MemberRepository.class) 에 몇가지 추가
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } // 다형성에 의거해서 뭐가 들어올지 모름

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
