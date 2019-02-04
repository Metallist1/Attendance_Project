/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendenceproject.bll.util;

import javafx.collections.ObservableList;
import attendenceproject.be.User;

/**
 *
 * @author nedas
 */
public class searchUser {

    public ObservableList<User> search(ObservableList<User> items, String text) {
        return items.filtered((t) -> t.getName().toLowerCase().startsWith(text.toLowerCase()));
    }
}
