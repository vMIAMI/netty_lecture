package com.vime.example.example_7;

import com.vime.thrift.generated.Person;
import com.vime.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport = new TFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(protocol);
        try{
            tTransport.open();

            Person person = client.getPersonByName("t_name");
            System.out.println(person.getName());
            System.out.println(person.getAge());

            Person person1 = new Person();
            person1.setName("t_name_edit");
            person1.setAge(17);
            person1.setMarried(false);
            client.savePerson(person1);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            tTransport.close();
        }
    }
}
