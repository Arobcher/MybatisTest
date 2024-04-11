package com.ayou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String message;

    public void show() {
        System.out.println(name + "说: " + message);
    }
}
