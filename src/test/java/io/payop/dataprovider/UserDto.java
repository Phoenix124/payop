package io.payop.dataprovider;

import javax.xml.bind.annotation.XmlElement;

public class UserDto {

    @XmlElement
    public String email;

    @XmlElement
    public String password;

}
