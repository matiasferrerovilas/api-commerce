package api.m2.commerce.mappers;

import api.m2.commerce.entities.User;
import api.m2.commerce.records.users.UserRecord;
import java.time.Instant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-16T17:27:32+0200",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-9.4.0.jar, environment: Java 25.0.2 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserRecord toRecord(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String email = null;
        String givenName = null;
        String familyName = null;
        Instant createdAt = null;

        id = user.getId();
        email = user.getEmail();
        givenName = user.getGivenName();
        familyName = user.getFamilyName();
        createdAt = user.getCreatedAt();

        UserRecord userRecord = new UserRecord( id, email, givenName, familyName, createdAt );

        return userRecord;
    }
}
