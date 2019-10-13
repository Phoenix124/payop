package io.payop.dataprovider;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
public class UsersDto {

    @XmlElement(name = "user")
    public List<UserDto> usersDto;
}
