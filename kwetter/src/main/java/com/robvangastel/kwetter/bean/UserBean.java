package com.robvangastel.kwetter.bean;

        import com.robvangastel.kwetter.domain.Tweet;
        import com.robvangastel.kwetter.domain.User;
        import com.robvangastel.kwetter.exception.UserException;
        import com.robvangastel.kwetter.service.UserService;

        import javax.enterprise.context.RequestScoped;
        import javax.inject.Inject;
        import javax.inject.Named;
        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Rob on 23-3-2017.
 */

@Named(value = "UserBean")
@RequestScoped
public class UserBean implements Serializable {

    @Inject
    private UserService userService;

    public List<User> getUsers() {
        return userService.findAll();
    }

    public void deleteUser(long id) throws UserException {
        userService.delete(id);
    }
}
