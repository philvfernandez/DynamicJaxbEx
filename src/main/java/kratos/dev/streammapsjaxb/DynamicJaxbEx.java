package kratos.dev.streammapsjaxb;

import kratos.dev.streammapsjaxb.domain.Record;
import kratos.dev.streammapsjaxb.domain.Table;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@SpringBootApplication
public class DynamicJaxbEx {

    public static void main(String[] args) throws JAXBException {

        /*
        PVF 11/17/22:
        - JAXB example that Dynamic Unmarshalls tag/element names in XML when they are not known ahead of time.
        - This is based on example given in SOF article:
        https://stackoverflow.com/questions/58444553/jaxb-xml-dynamic-unmarshalling
         */


        //Build data to generate as XML.
        Table table1 = new Table(
                new Record().addElement("DOC_ID", "some value")
                        .addElement("ENTITY_ID", "enity value"),
                new Record().addElement("SUB_ID", "a sub id value")
                        .addElement("CASE_DOC_ID", "a case doc value")
        );
        System.out.println(table1);

        //Generate XML
        String xml;
        try {
            StringWriter out = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(table1, out);
            xml = out.toString();

        } finally {

        }

        System.out.println(xml);

        //Confirm the JAXB mappings generate the XML format we would parse.
        Table table2;
        JAXBContext jaxbContext = JAXBContext.newInstance(Table.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        table2 = (Table) unmarshaller.unmarshal(new StringReader(xml));

        System.out.println("Unmarshalled XML from what was generated:");
        System.out.println(table2);


    }

}
