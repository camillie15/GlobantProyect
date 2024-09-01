package service.user;

import model.exceptions.EmailExistsException;
import model.user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService implements UserPort{
    private final Map<String, User> users = new HashMap<>();

    /**
     * This method creates a user with the received parameters and returns that user created.
     * @param name user's name
     * @param email user's email
     * @param password user's password
     * @return user created
     */
    @Override
    public User createUser(String name, String email, String password) {
        User user;
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

    /**
     * This method saves the user in the Map.
     * @param user user to be saved
     */
    @Override
    public void saveUser(User user) {
        users.put(user.getIdUser(), user);
    }

    /**
     * This method checks if the user's id is in the Map.
     * @param id id to check
     * @return boolean data of whether the id is found or not
     */
    @Override
    public boolean checkIdUser(String id) {
        return users.containsKey(id);
    }

    /**
     * This method checks if a user already has that email.
     * @param email email to check
     * @return boolean data of whether the email is found or not
     */
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

    /**
     * This method returns the registered user with the email sent as a parameter.
     * @param email email of the user to search
     * @return the user with that email
     */
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

    /**
     * This method returns the registered user with the id sent as a parameter.
     * @param id id of the user to search
     * @return the user with that id
     */
    @Override
    public User getUserById(String id) {
        return users.get(id);
    }
}
