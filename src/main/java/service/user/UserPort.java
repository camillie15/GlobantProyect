package service.user;

import model.user.User;

public interface UserPort {
    User createUser(String name, String email, String password);
    void saveUser(User user);
    boolean checkIdUser(String id);
    boolean checkEmailUser(String email);
}
