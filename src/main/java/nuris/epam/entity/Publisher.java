package nuris.epam.entity;

/**
 * Created by User on 14.03.2017.
 */
public class Publisher extends BaseEntity {
    private String name;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return getId()+ "/"+name+"/"+city;
    }
}
