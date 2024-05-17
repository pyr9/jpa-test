package org.pyr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pyr.config.JPAConfig;
import org.pyr.entity.User;
import org.pyr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ContextConfiguration(classes = JPAConfig.class)
//@SpringBootTest
//@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JPATest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFind(){
        Optional<User> User = userRepository.findById(2L);
        System.out.println(User.get());
    }

    @Test
    public void testSave() {
        User User = new User();
        User.setCustId(2L);
        User.setCreatedAt(new Date());
        User.setCustName("lisi333,111");
        User.setAddress("中国12443");
        userRepository.save(User);
        System.out.println("DONE!-----------------");
    }


    @Test
    public void testSaveAll() {
        List<User> Users = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            User User = new User();
            User.setCustId((long) i);
            User.setCreatedAt(new Date());
            User.setCustName("lddif1" + i);
            User.setAddress("中调d04" + i);
            Users.add(User);
        }
        List<User> rs = userRepository.saveAll(Users);
        String str = rs.stream().map(e -> e.getCustId().toString()).collect(Collectors.joining(","));
        System.out.println("DONE!-----------------"+str);
    }


    @Test
    public void testUpdate() {
        User User = new User();
        User.setCustId(13L);
        User.setCustName("aa123");
        User.setCreatedAt(new Date());
        userRepository.save(User);
        System.out.println((User) null);

    }

    @Test
    public void testDelete(){
        User User = new User();
        User.setCustId(3L);
        User.setCustName("aa123");
        userRepository.delete(User);
    }

    @Test
    public void test(){
        List<Long> originAttachmentIds = new ArrayList<>();
//        originAttachmentIds.add(1L);
//        originAttachmentIds.add(2L);
        List<Long> attachmentFileIds = new ArrayList<>();
        attachmentFileIds.add(3L);
        attachmentFileIds.add(2L);
        List<Long> deletedAttachmentIds = originAttachmentIds.stream().filter(id -> !attachmentFileIds.contains(id)).collect(Collectors.toList());
        List<Long> addedAttachmentIds = attachmentFileIds.stream().filter(id -> !originAttachmentIds.contains(id)).collect(Collectors.toList());
        System.out.println(deletedAttachmentIds);
        System.out.println(addedAttachmentIds);
    }
}
