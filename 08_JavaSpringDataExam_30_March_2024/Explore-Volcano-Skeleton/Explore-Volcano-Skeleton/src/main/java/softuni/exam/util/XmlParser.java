package softuni.exam.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <T> T fromFile(Class<T> clazz, String path) throws JAXBException;
}
