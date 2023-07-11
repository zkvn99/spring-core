package minwook.core.Order;

import minwook.core.discount.DiscountPolicy;
import minwook.core.discount.FixDiscountPolicy;
import minwook.core.discount.RateDiscountPolicy;
import minwook.core.member.Member;
import minwook.core.member.MemberRepository;
import minwook.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // final - 생성자 필수
    // final로 선언함으로 다른 구현체로 변경 불가 (의도한 동작 유지, 안정성 및 오류 방지)

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
