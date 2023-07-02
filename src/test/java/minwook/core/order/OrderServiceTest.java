package minwook.core.order;

import minwook.core.Order.Order;
import minwook.core.Order.OrderService;
import minwook.core.Order.OrderServiceImpl;
import minwook.core.member.Grade;
import minwook.core.member.Member;
import minwook.core.member.MemberService;
import minwook.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
