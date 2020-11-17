package com.java.xml.handler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.StringWriter;

public class JAXBConverter {
  public static void main(String[] args) throws JAXBException, IOException {
    Customer customer = new Customer();
    customer.setId(100);
    customer.setName("mkyong");
    customer.setAge(29);
    long startTime = System.currentTimeMillis();
    String result = JAXBContextParser(customer);
    long totalTime = System.currentTimeMillis() - startTime;
    System.out.println(totalTime);
    System.out.println(result);
    startTime = System.currentTimeMillis();
    result = JAXBUtils.marshal(customer);
    totalTime = System.currentTimeMillis() - startTime;
    System.out.println(totalTime);
    System.out.println(result);
  }

  private static <T> String JAXBContextParser(Object object) throws JAXBException, IOException  {
      JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());
      Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
      // output pretty printed
      jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

      JAXBElement<T> jaxbElement = new JAXBElement(new QName("", object.getClass().getSimpleName()), object.getClass(), object);

      StringWriter sw = new StringWriter();
      jaxbMarshaller.marshal(jaxbElement, sw);
      String xmlContent = sw.toString();
      sw.close();
      return xmlContent;
  }
}
