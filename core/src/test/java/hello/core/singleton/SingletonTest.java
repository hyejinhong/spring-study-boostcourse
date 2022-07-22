package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        MemberService memberService3 = appConfig.memberService();
        // 참조값 다른 것 확인
        System.out.println("memberService3 = " + memberService3);
        System.out.println("memberService2 = " + memberService2);
        System.out.println("memberService1 = " + memberService1);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService2 = " + singletonService2);
        System.out.println("singletonService1 = " + singletonService1);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
//        Assertions.assertThat(singletonService1).isEqualTo(singletonService2);
        // sameAs 와 equalTo 차이
        // same : == (인스턴스)
        // equals : 자바의 이퀄즈 (오버라이드하는 그 메소드)
    }
}
