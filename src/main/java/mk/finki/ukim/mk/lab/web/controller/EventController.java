package mk.finki.ukim.mk.lab.web.controller;



import mk.finki.ukim.mk.lab.model.Category;
import mk.finki.ukim.mk.lab.model.Event;
import mk.finki.ukim.mk.lab.model.Location;
import mk.finki.ukim.mk.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.service.CategoryService;
import mk.finki.ukim.mk.lab.service.EventService;
import mk.finki.ukim.mk.lab.service.LocationService;
import mk.finki.ukim.mk.lab.repository.CategoryRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    public EventController(EventService eventService, LocationService locationService, CategoryService categoryService, EventRepository eventRepository, LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String search,
                                @RequestParam(required = false) String searchByCategory,
                                @RequestParam(required = false) String searchByLocation,
                                Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        } else if (search != null && !search.isEmpty()) {
            model.addAttribute("events", eventService.searchEvents(search));
        } else if (searchByCategory != null) {
            model.addAttribute("events", eventService.searchByCategory(searchByCategory));
        }   else if (searchByLocation != null) {
            model.addAttribute("events", eventService.searchByLocation(searchByLocation));
        } else {
            model.addAttribute("events", eventService.listAll());
        }
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("locations", locationService.findAll());
        return "listEvents";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        List<Location> locations = locationService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("locations", locations);
        model.addAttribute("categories", categories);
        return "add-event";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Long categoryId,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId) {
        this.eventService.save(name, description, popularityScore, categoryId, locationId);
        return "redirect:/events";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.findById(id).orElse(null);
        if (event != null) {
            List<Location> locations = locationService.findAll();
            List<Category> categories = categoryService.findAll();
            model.addAttribute("locations", locations);
            model.addAttribute("categories", categories);
            model.addAttribute("event", event);
            return "add-event";
        }
        return "redirect:/events?error=Event not found";
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Long categoryId,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setLocation(locationRepository.findById(locationId).orElse(null));
            event.setCategory(categoryRepository.findById(categoryId).orElse(null));
            eventRepository.save(event);
        }
        return "redirect:/events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        if (eventRepository.findById(id).isPresent()) {
            this.eventRepository.deleteById(id);
            return "redirect:/events";
        } else return "redirect:/events?error=Event not found";
    }

    @PostMapping("/search")
    public String getSearchedEvents(@RequestParam String search) {
        if (search != null && !search.isEmpty())
            return "redirect:/events?search=" + search;
        return "redirect:/events";
    }

    @PostMapping("/searchByCategory")
    public String getSearchedByCategoryEvents(@RequestParam Long searchByCategory) {
        if (categoryRepository.findById(searchByCategory).isPresent()) {
            Category category = categoryRepository.findById(searchByCategory).get();
            return "redirect:/events?searchByCategory=" + category.getCategory();
        }
        return "redirect:/events";
    }

    @PostMapping("/searchByLocation")
    public String getSearchedByLocationEvents(@RequestParam Long searchByLocation) {
        if (locationRepository.findById(searchByLocation).isPresent()) {
            Location location = locationRepository.findById(searchByLocation).get();
            return "redirect:/events?searchByLocation=" + location.getAddress();
        }
        return "redirect:/events";
    }

}