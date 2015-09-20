package p.lodz.pl.spjava.sdudkiewicz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import p.lodz.pl.spjava.sdudkiewicz.models.Domain;
import p.lodz.pl.spjava.sdudkiewicz.models.User;
import p.lodz.pl.spjava.sdudkiewicz.repository.UserRepository;

/**
 * Class UserController
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

  // ------------------------
    // PUBLIC METHODS
    // ------------------------
    /**
     * Create a new user with an auto-generated id and email and name as passed
     * values.
     */
    @RequestMapping(value = "/create")
    @ResponseBody
    public String create(String cn, String uid) {
        try {
            User user = new User(cn, uid);
            //userDao.create(user);
            userRepository.save(user);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created!";
    }

    /**
     * Delete the user with the passed id.
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userRepository.delete(id);
        } catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * Retrieve the id for the user with the passed email address.
     */
    @RequestMapping(value = "/get-by-cn")
    @ResponseBody
    public String getByEmail(String cn) {
        String userId;
        try {
            User user = userRepository.findByCn(cn);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found: " + ex.toString();
        }
        return "The user id is: " + userId;
    }

    /**
     * Update the email and the name for the user indentified by the passed id.
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String updateName(long id, String cn, String uid) {
        try {
            User user =userRepository.findByUid(uid);
            user.setCn(cn);
            user.setUid(uid);
            userRepository.save(user);
        } catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }


} // class UserController
