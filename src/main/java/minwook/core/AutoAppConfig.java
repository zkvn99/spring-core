package minwook.core;

import minwook.core.member.MemberRepository;
import minwook.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "minwook.core.member",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
// basePackages로 컴포넌트 스캔 대상 지정 가능 (선택 패키지 포함 하위 패키지 조회)
// basePackageClasses로 컴포넌트 클래스 스캔 대상 지정 가능
// default는 현재 클래스 위에 패키지 하위 전부, 최상단에 두면 편리함
// 스프링 부트 사용 시 스프링 부트의 대표 시작 정보인
// @SpringBootApplication를 프로젝트 시작 루트 위에 두는 것이 관례
// 이 설정 안에 ComponentScan이 들어있음
public class AutoAppConfig {

    // 수동 빈 등록, 자동 빈 등록 충돌 실험
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
