package nuris.epam.entity;

/**
 * Created by User on 09.03.2017.
 */
public class Author extends BaseEntity{

    private String firstName;

    private String lastName;

    private String middle_name;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    @Override
    public String toString() {
        return getId()+"/"+ firstName + "/"+lastName+"/"+middle_name;
    }
}
