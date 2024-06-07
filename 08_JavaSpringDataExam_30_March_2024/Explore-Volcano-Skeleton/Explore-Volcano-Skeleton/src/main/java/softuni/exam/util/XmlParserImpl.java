package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class XmlParserImpl implements XmlParser {

    @Override
    public <T> T fromFile(Class<T> clazz, String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        T object = (T) unmarshaller.unmarshal(new File(path));
        return object;
    }
}
