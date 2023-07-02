package minwook.core.Order;

import minwook.core.AppConfig;
import minwook.core.member.Grade;
import minwook.core.member.Member;
import minwook.core.member.MemberService;
import minwook.core.member.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.caculatePrice());
    }
}
