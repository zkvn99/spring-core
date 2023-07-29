package minwook.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
        // 스프링 빈 라이프 사이클 = 객체 생성 -> 의존관계 주입
        // 예외 - 생성자 주입
        // 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 함
        // 스프링 빈에게 콜백 메서드를 통해서 초기화 시점을 알려주는 다양한 기능 제공
        // 스프링 빈의 이벤트 라이프사이클
        // 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입
        // 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료 (싱글톤 예시)
        // 초기화 콜백은 빈이 생성되고, 빈이 의존관계 주입이 완료된 후 호출
        // 소멸전 콜백은 빈이 소멸되기 직전에 호출
        // SRP -> 객체의 생성과 초기화를 분리
        // 생성자는 필수 정보(파라미터)를 받고 메모리 할당 및 객체를 생성하는 책임
        // 초기화는 생성된 값을 활용해서 외부 커넥션에 연결하는 등 무거운 동작
    }
}
