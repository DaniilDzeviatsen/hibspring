package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(unique = true)
    private int series;

    @Column
    private String model;

    public Car() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car(int series, String model) {
        this.series = series;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "user=" + user +
                ", series=" + series +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return series == car.series && Objects.equals(user, car.user) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, series, model);
    }
}