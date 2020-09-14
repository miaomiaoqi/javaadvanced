package com.miaoqi.java.reflect.bean;

import java.util.List;
import java.util.Map;

public class PersonDao extends BaseDao<Person> implements Eat {

    public Person person() {
        return null;
    }

    public List<Map<Integer, Person>> genericPerson() {
        return null;
    }

}
