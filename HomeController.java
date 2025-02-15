package com.example.travelplanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final List<TravelPlan> travelPlans = new ArrayList<>();

    static class TravelPlan {
        public int id;
        public String destination;
        public String date;
        public double budget;

        public TravelPlan(int id, String destination, String date, double budget) {
            this.id = id;
            this.destination = destination;
            this.date = date;
            this.budget = budget;
        }
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("travelPlans", travelPlans);
        return "index"; // Loads index.html from templates/
    }

    @GetMapping("/plans")
    public String viewPlans(Model model) {
        model.addAttribute("travelPlans", travelPlans);  // Pass the list to Thymeleaf
        return "plans"; // Loads plans.html from templates/
    }

    @PostMapping("/add")
    public String addTravelPlan(@RequestParam String destination,
                                @RequestParam String date,
                                @RequestParam double budget) {
        int id = travelPlans.size() + 1; // Assign a unique ID
        travelPlans.add(new TravelPlan(id, destination, date, budget));
        return "redirect:/plans"; // Redirect to /plans page
    }
}
