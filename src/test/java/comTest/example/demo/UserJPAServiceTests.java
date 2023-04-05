package comTest.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserJPARepository;
import com.example.demo.service.UserJPAService;
import com.example.demo.service.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Either use this annotation or use the setup method with BeforeEach; Both same
public class UserJPAServiceTests {

    @Mock
    UserJPARepository userJpaRepository;

    @InjectMocks
    UserJPAService userJpaService;

    @BeforeEach // Either use this method or use the @ExtendWith(MockitoExtension.class in class level; Both same
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void get_all_users() {
        // arrange
        List<User> users = Arrays.asList(User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build(),
                User.builder().id(2).name("Bala").dob(LocalDate.now().minusYears(20)).build());
        when(userJpaRepository.findAll()).thenReturn(users);

        // act
        List<User> userList = userJpaService.getAllEmplmoyees();

        // assert
        Assertions.assertEquals(2, userList.size());
        Assertions.assertEquals("Bala", userList.get(1).getName());
    }

    @Test
    public void get_specific_user() {
        // arrange
        User user1 = User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build();
        User user2 = User.builder().id(2).name("Bala").dob(LocalDate.now().minusYears(20)).build();
        when(userJpaRepository.findById(anyInt()))
                .thenReturn(Optional.of(user1))
                .thenReturn(Optional.of(user2));
        //when(userJpaRepository.findById(1)).thenReturn(Optional.of(user1));

        // act
        User user_1 = userJpaService.getUser(1);
        User user_2 = userJpaService.getUser(2);

        // assert
        Assertions.assertEquals("Mani", user_1.getName());
        Assertions.assertEquals("Bala", user_2.getName());
        Assertions.assertEquals(2, user_2.getId());

        System.out.println("Value is: "+ userJpaRepository.findById(2));
        InOrder inorder = Mockito.inOrder(userJpaRepository);
        inorder.verify(userJpaRepository);

    }

    @Test
    public void should_delete_user() {
        userJpaService.deleteUser(1);
        verify(userJpaRepository, times(1)).deleteById(1);
        verify(userJpaRepository, atLeastOnce()).deleteById(anyInt());
    }

    @Test
    public void update_user() {
        // find user
        User user1 = User.builder().id(1).name("Mani").dob(LocalDate.now().minusYears(20)).build();
        User user2 = User.builder().id(2).name("Bala").dob(LocalDate.now().minusYears(20)).build();
        when(userJpaRepository.findById(anyInt())).thenReturn(Optional.of(user1));

        // manipulate the attributes
        User updatedUser = userJpaService.updateUser(1, user2);

        // Assertion
        Assertions.assertEquals("Bala", updatedUser.getName());
        System.out.println("User1 Name : "+user1.getName());
        System.out.println("User2 Name : "+user2.getName());

        InOrder inOrder = Mockito.inOrder(userJpaRepository);
        inOrder.verify(userJpaRepository).findById(1);
        inOrder.verify(userJpaRepository).save(user1);

    }


    @Test
    public void should_throw_an_exception(){
        //find user method
        when(userJpaRepository.findById(anyInt())).thenThrow(UserNotFoundException.class);
        Assertions.assertThrows(UserNotFoundException.class,()->userJpaService.getUser(1));
    }

}
