package io.payop.dataprovider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

import static org.testng.Assert.fail;

@SuppressWarnings("unchecked")
public class DtoController<T> {

    private T dtoObject;
    private final Class<T> dtoTypeClass;

    public DtoController(Class<T> dtoTypeClass) {
        this.dtoTypeClass = dtoTypeClass;
    }

    public T getDataFromFile(String valuesFileName) {
        String valuesDirectory = "src/main/resources/";
        File valuesFile = new File(valuesDirectory, valuesFileName);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(dtoTypeClass);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            dtoObject = (T) jaxbUnmarshaller.unmarshal(valuesFile);
        } catch (JAXBException e) {
            fail("Can not parse file '" + valuesFileName + "'\n" + e.getMessage());
        }

        return dtoObject;
    }
}
