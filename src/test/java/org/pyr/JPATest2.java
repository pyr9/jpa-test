package org.pyr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pyr.config.JPAConfig;
import org.pyr.entity.User;
import org.pyr.repository.UserJPQLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@ContextConfiguration(classes = JPAConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JPATest2 {
    @Autowired
    UserJPQLRepository UserJPQLRepository;

    @Test
    public void testFindByName(){
        User User = UserJPQLRepository.findUserByCustName("徐庶143");
        System.out.println(User);
    }

    @Test
    public void testFindById(){
        User User = UserJPQLRepository.findUserByCustName2("徐庶143");
        System.out.println(User);
    }

    @Test
    public void testUpdate(){
        UserJPQLRepository.updateCutomserNameById("lisa", 2L);
    }

    @Test
    public void testDelete(){
        UserJPQLRepository.deleteCutomserById(2L);
    }


    @Test
    public void testFindUserByCustNameWithNative(){
        User User = UserJPQLRepository.findUserByCustNameWithNative("aa");
        System.out.println(User);
    }


    @Test
    public void testList(){
        User User = new User();
        User.setCustName("111");
//        User.setCustId(1L);
        Optional.ofNullable(User.getCustId()).ifPresent(id -> System.out.println("--------------------"+id));

    }
}
