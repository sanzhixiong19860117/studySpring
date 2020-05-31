import com.joy.controller.PersonController;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author joy
 * @date 2020/5/31
 */
public class MyTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
    @Test
    public void test01(){
        //基础的使用
        PersonController personController = context.getBean("personController",PersonController.class);
        System.out.println(personController);
    }
}
