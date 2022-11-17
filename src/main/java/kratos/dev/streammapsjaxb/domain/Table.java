package kratos.dev.streammapsjaxb.domain;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "TABLE")
@XmlAccessorType(XmlAccessType.FIELD)
public class Table {

    //Note: Because we use XmlAccessorType annotation above, the XmlElementWrapper and XmlElement
    //annotations below must be left at the property level and NOT the getter method.  Otherwise,
    //a duplicate name exception will be thrown.  This is a JAXB requirement.
    @XmlElementWrapper(name = "RECORDS")
    @XmlElement(name = "RECORD")
    private List<Record> records = new ArrayList<>();

    public Table() {
        this.records = new ArrayList<>();
    }

    public Table(Record addElement, Record addElement1) {
        records.add(addElement);
        records.add(addElement1);
    }

    public List<Record> getRecords() {
        return this.records;
    }
    public void setRecords(List<Record> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Table" + this.records.toString();
    }
}
