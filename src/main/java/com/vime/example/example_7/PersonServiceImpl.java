package com.vime.example.example_7;

import com.vime.thrift.generated.DataException;
import com.vime.thrift.generated.Person;
import com.vime.thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByName(String name) throws DataException, org.apache.thrift.TException {
        System.out.println("getPersonByName call....");
        Person person = new Person();

        person.setName("t_name");
        person.setAge(18);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, org.apache.thrift.TException {
        System.out.println("savePerson call....");
        System.out.println(person.getName());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
