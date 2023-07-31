package minwook.core;

import minwook.core.order.OrderService;
import minwook.core.order.OrderServiceImpl;
import minwook.core.discount.DiscountPolicy;
import minwook.core.discount.RateDiscountPolicy;
import minwook.core.member.MemberRepository;
import minwook.core.member.MemberService;
import minwook.core.member.MemberServiceImpl;
import minwook.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 정보 (바이트 코드를 조작하는 CGLIB 기술) -> 싱글톤 보장 (생성된 빈 재사용)
public class AppConfig { // 객체의 생성과 연결을 담당, 관심사의 분리[객체 생성 및 연결, 실행]

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    @Bean // 빈을 붙일 경우 스프링 컨테이너에 등록
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    } // 생성자 주입

    @Bean //(name = "aa") 이런식으로 이름 변경이 가능함 memberRepository -> aa
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    } // 생성자 주입

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy
        return new RateDiscountPolicy();
    }
}
// Impl 입장에서 의존관계를 마치 외부에서 주입해주는 것 같음 -> DI(Dependency Injection) 의존관계 주입
// 역할과 구현 클래스가 한눈에 들어옴