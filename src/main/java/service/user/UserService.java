package service.user;

import model.exceptions.EmailExistsException;
import model.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService implements UserPort{
    private final Map<String, User> users = new HashMap<>();

    @Override
    public User createUser(String name, String email, String password) {
        User user = null;
        if(checkEmailUser(email)){
            String idUser;
            do {
                idUser = UUID.randomUUID().toString().substring(0, 8);
            } while (checkIdUser(idUser));
            user = new User(name, email, password, idUser);
        }else{
            throw new EmailExistsException();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        users.put(user.getIdUser(), user);
    }

    @Override
    public boolean checkIdUser(String id) {
        return users.containsKey(id);
    }

    @Override
    public boolean checkEmailUser(String email) {
        int counter = 0;
        for(User user: users.values()){
            if (user.getEmail().equals(email)){
                counter += 1;
            }
        }
        return counter == 0;
    }

    @Override
    public User getUserByEmail(String email) {
        User userFind = null;
        for (User user : users.values()){
            if(user.getEmail().equals(email)){
                userFind = new User(user.getName(), user.getEmail(), user.getPassword(), user.getIdUser(), user.getWallet(), user.getTransactions());
                break;
            }
        }
        return userFind;
    }

    @Override
    public User getUserById(String id) {
        return users.get(id);
    }
}
