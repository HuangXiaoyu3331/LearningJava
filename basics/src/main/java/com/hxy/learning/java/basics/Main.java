package com.hxy.learning.java.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author huangxy
 * @date 2022/03/06
 */
public class Main {

    public static void main(String[] args) {
        List<User> userList = new ArrayList();
        String username = Optional.ofNullable(userList)
                .map(list -> list.get(0))
                .map(User::getUsername)
                .orElse("");
        System.out.println(username);
    }

    static class User{
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
