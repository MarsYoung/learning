package com.marsyoung.learning.java8;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mazhiyu on 2017/10/20.
 */
public class Name {

    String firstName;
    String secoundName;

    public Name(String firstName, String secoundName) {
        this.firstName = firstName;
        this.secoundName = secoundName;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Name ? this.firstName.equals(((Name) obj).firstName) && this.secoundName.equals(((Name) obj).secoundName) : false;
    }

    public static void main(String[] args) {
        Set<Name> set=new HashSet<>();
        Name name=new Name("mars","young");
        set.add(name);
        System.out.println(set.contains(new Name("mars","young")));
    }
}
