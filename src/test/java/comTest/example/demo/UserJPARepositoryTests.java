package comTest.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserJPARepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserJPARepositoryTests{

    @Autowired
    UserJPARepository userJPARepository;


    @Test
    public void should_get_all_users(){
        //arrange
        User lakshmi = User.builder().id(1).name("Lakshmi").dob(LocalDate.now().minusYears(15)).build();
        userJPARepository.save(lakshmi);
        //act
        List<User> all = userJPARepository.findAll();
        //assert
        Assertions.assertEquals(4,all.size());
    }



    @Test
    public void  should_get_one_user(){
        User lakshmi = User.builder().id(1).name("Lakshmi").dob(LocalDate.now().minusYears(15)).build();
        userJPARepository.save(lakshmi);
        userJPARepository.findById(1);
    }

    @Test
    public void should_delete_one_user(){
        User lakshmi = User.builder().id(1).name("Lakshmi").dob(LocalDate.now().minusYears(15)).build();
        userJPARepository.save(lakshmi);

        List<User> all = userJPARepository.findAll();
        //assert
        Assertions.assertEquals(4,all.size());

        userJPARepository.deleteById(1);

        all = userJPARepository.findAll();
        Assertions.assertEquals(3,all.size());

    }


    @Test
    public void should_update_user(){
        User lakshmi = User.builder().id(1).name("Lakshmi").dob(LocalDate.now().minusYears(15)).build();
        userJPARepository.save(lakshmi);

        User user = userJPARepository.findById(1).get();
        user.setName("Lakshminarayanan");
        user.setDob(LocalDate.now().minusYears(45));
        User updatedUser = userJPARepository.save(user);

        Assertions.assertEquals("Lakshminarayanan",updatedUser.getName());
        Assertions.assertEquals(LocalDate.now().minusYears(45),updatedUser.getDob());

    }





}
