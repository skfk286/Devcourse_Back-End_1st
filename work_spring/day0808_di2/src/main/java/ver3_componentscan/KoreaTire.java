package ver3_componentscan;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Component
@Repository // 그냥 객체가 아니라 DB작업하는 애구나! 정체가 한눈에 확인 된다!
public class KoreaTire implements Tire { // Tire 표준에 맞춰서 클래스 작성.(Tire가 구현하라는 거 다 구현은 해야지)

    @Override
    public String getModel() { // insert
        return "한국"; // mysql insert 구현함.
    }
}
