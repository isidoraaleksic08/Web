package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.repository.CategoryRepository;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import org.springframework.stereotype.Component;
import mk.finki.ukim.mk.lab.model.Location;


import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();
    public static List<Category> categories = new ArrayList<>();
    public static final List<EventBooking> MyBookings = new ArrayList<>();

    public static List<Location> locations = new ArrayList<>();

    public final EventRepository eventRepository;
    public final CategoryRepository categoryRepository;
    public final LocationRepository locationRepository;
    public DataHolder(EventRepository eventRepository, CategoryRepository categoryRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.locationRepository = locationRepository;
    }


    @PostConstruct
    public void init() {

        categories.add(new Category((long) (Math.random()*1000), "Sport"));
        categories.add(new Category((long) (Math.random()*1000), "Natprevar"));
        categories.add(new Category((long) (Math.random()*1000), "Filmovi"));
        categories.add(new Category((long) (Math.random()*1000), "Muzika"));
        categories.add(new Category((long) (Math.random()*1000), "Hrana"));
        categories.add(new Category((long) (Math.random()*1000), "Klubovi"));

        categoryRepository.saveAll(categories);

        locations.add(new Location((long) (Math.random() * 1000),"Arena"));
        locations.add(new Location((long) (Math.random() * 1000),"Plostad"));
        locations.add(new Location((long) (Math.random() * 1000),"Sportska sala"));
        locations.add(new Location((long) (Math.random() * 1000),"Narodno kino"));
        locations.add(new Location((long) (Math.random() * 1000),"Citalna"));

         locationRepository.saveAll(locations);

        events.add(new Event((long) (Math.random() * 1000),"Naroden Koncert", "Izvedna na narodna muzika", 8.5, categories.get(3),locations.get(0)));
        events.add(new Event((long) (Math.random() * 1000),"Denovi na med", "Izlozba na domasen med", 9.0, categories.get(4), locations.get(1)));
        events.add(new Event((long) (Math.random() * 1000),"Book club", "Mesto kade kniga gi spojuva lugjeto", 7.8, categories.get(5), locations.get(4)));
        events.add(new Event((long) (Math.random() * 1000),"Maraton na filmovi", "Imituvanje na najpoznati filmovi", 8.2, categories.get(2),locations.get(3)));
        events.add(new Event((long) (Math.random() * 1000),"Natprevar po kosarka", "Prijatelski natprevar pomegju dva kluba", 7.5, categories.get(1),locations.get(2)));
        events.add(new Event((long) (Math.random() * 1000),"Igra na golf", "Zabava preku igra na golg", 8.0, categories.get(0),locations.get(0)));
        events.add(new Event((long) (Math.random() * 1000),"Teatarska pretstava", "Izvedba na pretstava od poznati akteri", 6.9, categories.get(2),locations.get(3)));
        events.add(new Event((long) (Math.random() * 1000),"Izlozba na domasna hrana", "Probuvanje na razlicna zgotvena hrana", 7.3, categories.get(4),locations.get(1)));
        events.add(new Event((long) (Math.random() * 1000),"Rock koncert", "Uzivajte na rock izvedba", 8.7, categories.get(3),locations.get(0)));
        events.add(new Event((long) (Math.random() * 1000),"Kosarka", "Natprevar pomegju dva kluba", 8.1, categories.get(0),locations.get(2)));

        eventRepository.saveAll(events);
    }
}