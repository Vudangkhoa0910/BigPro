package hotelmanagementsystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_hotel")
public class Hotel {


    @Id
    private Long id;

    @Column(name = "hotel_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Room> rooms = new ArrayList<>();

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Hotel (){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
