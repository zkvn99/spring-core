package minwook.core;

import minwook.core.Order.Order;
import minwook.core.Order.OrderService;
import minwook.core.Order.OrderServiceImpl;
import minwook.core.discount.FixDiscountPolicy;
import minwook.core.member.MemberRepository;
import minwook.core.member.MemberService;
import minwook.core.member.MemberServiceImpl;
import minwook.core.member.MemoryMemberRepository;

public class AppConfig { // 객체의 생성과 연결을 담당, 관심사의 분리[객체 생성 및 연결, 실행]

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    } // 생성자 주입

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    } // 생성자 주입
}

// Impl 입장에서 의존관계를 마치 외부에서 주입해주는 것 같음 -> DI(Dependency Injection) 의존관계 주입