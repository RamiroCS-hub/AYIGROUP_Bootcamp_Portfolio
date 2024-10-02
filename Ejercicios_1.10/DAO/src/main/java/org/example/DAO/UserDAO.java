package org.example.DAO;

import org.example.Models.User;

public interface UserDAO {
    void insert(User user);
    void select();
    void update(User user);
    void delete(int id);
}
