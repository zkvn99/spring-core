package minwook.core.singleton;

public class SingletonService { // 싱글톤 생성하는 가장 단순하고 안전한 방법

    private static final SingletonService instance = new SingletonService(); // 클래스 레벨에 올라감

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
