package nuris.epam.entity;

import java.io.Serializable;

/**
 * Created by User on 09.03.2017.
 */
public abstract class BaseEntity {
    private int id;
    public int getId() {
        return id;
    }public void setId(int id) {
        this.id = id;
    }
}
