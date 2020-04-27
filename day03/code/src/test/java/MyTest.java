import com.joy.bean.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author joy
 * @version 1.0
 * @date 2020/4/28 1:36
 */
public class MyTest {
    //测试类
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ioc.xml");
        //测试是否得到数据
        final Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }
}
