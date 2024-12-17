package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

//    public default List<Event> findAll() {
//        return DataHolder.events.stream().toList();
//    }

//    public default Optional<Event> findById(Long id) {
//        return DataHolder.events.stream().filter(event -> event.getId().equals(id)).findFirst();
//    }
//
//    public default Optional<Event> findByName(String name) {
//        return DataHolder.events.stream().filter(event -> event.getName().equals(name)).findFirst();
//    }
//
//
//    public default List<Event> searchEvents(String text) {
//        List<Event> initial = DataHolder.events.stream().filter(e -> e.getName().contains(text) || e.getDescription().contains(text))
//                .toList();
//        if (initial.isEmpty()) {
//            initial = DataHolder.events.stream().filter(e -> e.getPopularityScore() >= Double.parseDouble(text))
//                    .toList();
//        }
//        return initial;
//    }
//
//    public default List<Event> searchByCategory(String text) {
//        return DataHolder.events.stream().filter(event -> event.getCategory().getCategory().equals(text)).collect(Collectors.toList());
//    }
//
//    public default Optional<Event> saveEvent(String name, String description, double popularityScore, Category category, Location location) {
//        if (category == null || location == null) {
//            throw new IllegalArgumentException();
//        }
//        Event event = new Event((long) (Math.random() * 1000), name, description, popularityScore, category, location);
//        DataHolder.events.removeIf(event1 -> event1.getName().equals(name));
//        DataHolder.events.add(event);
//        return Optional.of(event);
//    }
//
//    public default void deleteById(Long id) {
//        DataHolder.events.removeIf(event -> event.getId().equals(id));
//    }

//    List<Event> findAllByLocation_Id(Long locationId);

    Optional<Event> findByName(String name);
    List<Event> searchEventByName(String name);
    List<Event> searchByCategory(Category category);
     //Optional<Event> save(Event event);

    @Query("SELECT e FROM Event e WHERE e.category.category = :category")
    List<Event> findEventByCategory(String category);

    @Query("SELECT e FROM Event e WHERE e.location.address = :address")
    List<Event> findEventByLocation(String address);

}