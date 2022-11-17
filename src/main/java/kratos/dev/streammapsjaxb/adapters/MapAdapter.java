package kratos.dev.streammapsjaxb.adapters;

import org.w3c.dom.Element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
MapAdapter that converts the Map to a MapWrapper contain XML elements.
 */
public class MapAdapter extends XmlAdapter<MapWrapper, Map<String, String>> {

    @Override
    public MapWrapper marshal(Map<String, String> m) throws Exception {
        MapWrapper wrapper = new MapWrapper();
        List<JAXBElement<String>> elements = new ArrayList<JAXBElement<String>>();

        if(m !=  null && !m.isEmpty()) {
            for(Map.Entry<String, String> property : m.entrySet()) {
                elements.add(new JAXBElement<String>(new QName(property.getKey()),
                        String.class, property.getValue().toString()));
            }
        }
        wrapper.setElements(elements);
        return wrapper;
    }
    @Override
    public Map<String, String> unmarshal(MapWrapper v) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        if(v != null && v.elements != null && !v.elements.isEmpty()) {
            for(Object object : v.elements) {
                Element element = (Element) object;
                map.put(element.getNodeName(), element.getTextContent());
            }
        }
        return map;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class MapWrapper {
    @XmlAnyElement(lax = true)
    protected List<JAXBElement<String>> elements;

    public List<JAXBElement<String>> getElements() {
        return elements;
    }

    public void setElements(List<JAXBElement<String>> elements) {
        this.elements = elements;
    }
}
