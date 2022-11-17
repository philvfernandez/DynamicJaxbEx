package kratos.dev.streammapsjaxb.domain;

import kratos.dev.streammapsjaxb.adapters.MapAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;

@XmlJavaTypeAdapter(MapAdapter.class)
public class Record extends HashMap<String, String> {
    private static final long serialVersionUID = 1L;

    public Record addElement(String name, String value) {
        put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return "Record" + super.toString();
    }
}
