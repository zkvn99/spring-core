package minwook.core.order;

import minwook.core.annotation.MainDiscountPolicy;
import minwook.core.discount.DiscountPolicy;
import minwook.core.member.Member;
import minwook.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // final - 생성자 필수
    // final로 선언함으로 다른 구현체로 변경 불가 (의도한 동작 유지, 안정성 및 오류 방지)
    // final 없이 생성자 누락 시 테스트 에러

    // 생성자 주입 (불변, 누락, final 키워드 사용 가능)
    // 수정자 주입 및 나머지 주입은 모두 생성자 이후에 호출 (final 키워드 사용 불가)
    // 기본으로 생성자 주입, 필수 값이 아닌 경우에 수정자 주입

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } // @RequiredArgsConstructor 사용 시 final이 붙은 필드를 모아서 생성자 자동 생성

    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 역할과 구현을 분리해도 클라이언트인 OrderServiceImpl 코드 수정이 필요
    // 추상(인터페이스) DiscountPolicy, 구체 클래스 FixDiscountPolicy (둘다 의존해서 발생하는 문제)
    // 즉, DIP 위반하면서 동시에 할인 정책을 고정에서 비율로 변경할 경우 OCP도 위반
    // private DiscountPolicy discountPolicy;
    // 위와 같이 작성하면 DIP를 지킬 수 있지만, NPE(Null Pointer Exception) 발생
    // 누군가 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해야함


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
