package com.example.travelplanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        return "index"; // Loads index.html from templates/
    }

    @PostMapping("/add")
    public String addTravelPlan(@RequestParam String destination, 
                                @RequestParam String date, 
                                @RequestParam double budget) {
        int id = travelPlans.size() + 1;
        travelPlans.add(new TravelPlan(id, destination, date, budget));
        return "redirect:/plans"; // Redirects to /plans
    }

    @GetMapping("/plans")
    public String viewPlans(Model model) {
        model.addAttribute("travelPlans", travelPlans);
        return "plans"; // Loads plans.html from templates/
    }
}
