package com.example.padelversus.user;

import com.example.padelversus.mail.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    @Autowired
    private NotificationService notificationService;

    //Saves a (copy with ROLE_USER) user if succes return user name if not return null (user already exists)
    public String saveUser(String name, String mail, String pass) {
        User u = userRepository.findByName(name);
        if (u == null) {
            User u_mail = userRepository.findByMail(mail);
            if(u_mail == null) {
                User user = new User(name, mail, pass, "ROLE_USER");
                notificationService.sendNotification(user);
                userRepository.save(user);
                return user.getName();
            }else{
                return null;
            }
        } else {
            return null;
        }
    }
}