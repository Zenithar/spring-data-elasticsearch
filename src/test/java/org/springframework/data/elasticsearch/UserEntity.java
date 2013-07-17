package org.springframework.data.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.annotation.Generated;
import java.util.UUID;

/**
 * @author Thibault Normand
 */
@Document(indexName = "test-index", type = "user", indexStoreType = "memory", shards = 1, replicas = 0, refreshInterval = "-1")
public class UserEntity {

    private String uuid;
    private String firstName;
    private String lastName;
    private String email;
    private Long version;

    /**
     * Constructors
     */

    private UserEntity() {}

    public UserEntity(String firstName, String lastName, String email) {
        this(UUID.randomUUID().toString(), firstName, lastName, email);
    }

    public UserEntity(String uuid, String firstName, String lastName, String email) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * Accessors
     */

    @Id
    public String getUuid() {
        return uuid;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Mutators
     */

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Tools
     */

    @Override
    @Generated("IDEA")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    @Generated("IDEA")
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Override
    @Generated("IDEA")
    public String toString() {
        return "UserEntity{" +
                "uuid='" + uuid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", version=" + version +
                '}';
    }
}
