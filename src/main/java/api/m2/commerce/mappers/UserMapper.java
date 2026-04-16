package api.m2.commerce.mappers;

import api.m2.commerce.entities.User;
import api.m2.commerce.records.users.UserRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRecord toRecord(User user);
}
