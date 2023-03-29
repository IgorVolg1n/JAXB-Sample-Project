package org.example;

import org.example.model.Book;

import javax.xml.bind.*;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
//        Creating xsd
//        JAXBContext jc = JAXBContext.newInstance(Book.class);
//        jc.generateSchema(new SchemaOutputResolver() {
//            @Override
//            public Result createOutput(String namespaceURI, String suggestedFileName)
//                    throws IOException {
//                return new StreamResult(suggestedFileName);
//            }
//        });

//        Creating entity from XSD
        try {
            File xsd = new File("BookInvalid.xsd");
            // Read XSD file as input stream
            InputStream xsdInputStream = new FileInputStream(xsd);
            // Create JAXB context and unmarshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            // Generate Java classes from XSD and unmarshal XML to Java object
            JAXBElement<Book> root = unmarshaller.unmarshal(new StreamSource(xsdInputStream), Book.class);
            Book entity = root.getValue();
            entity.setAuthor("ABTop");
            System.out.println(entity.getAuthor());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}