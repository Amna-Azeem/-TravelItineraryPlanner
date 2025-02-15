import java.util.*;
import java.text.*;

class TravelItineraryPlanner {
    // Travel plan class to hold destination and related data
    static class TravelPlan {
        String destination;
        String date;
        String weather;
        double budget;
        String preferences;

        TravelPlan(String destination, String date, String weather, double budget, String preferences) {
            this.destination = destination;
            this.date = date;
            this.weather = weather;
            this.budget = budget;
            this.preferences = preferences;
        }

        @Override
        public String toString() {
            return "\n--------------------------------------------\n" +
                    "🎯 Destination: " + destination + "\n" +
                    "📅 Date: " + date + "\n" +
                    "☀️ Weather: " + weather + "\n" +
                    "💸 Budget: $" + budget + "\n" +
                    "🎒 Preferences: " + preferences + "\n" +
                    "--------------------------------------------";
        }

        // Method to update destination, date, and budget
        public void updateDetails(String destination, String date, double budget) {
            if (destination != null && !destination.isEmpty()) this.destination = destination;
            if (date != null && !date.isEmpty()) this.date = date;
            if (budget > 0) this.budget = budget;
        }
    }

    // Method to simulate getting weather information
    public static String getWeather(String destination) {
        // Mock weather data
        return "Sunny, 25°C"; // Replace this with actual API calls for real data
    }

    // Method to simulate map information (would require API integration like Google Maps)
    public static String getMapInfo(String destination) {
        // Mocking map URL as placeholder
        return "🔗 Map for " + destination + " at: [Map URL]";
    }

    // Method to validate date format (yyyy-mm-dd)
    public static boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Method to input travel details and calculate budget
    public static TravelPlan inputTravelDetails() {
        Scanner scanner = new Scanner(System.in);
    
        // Section 1: Destination Details
        System.out.println("\n🌟 Let's plan your next trip! Please enter the following details:\n");

        System.out.println("✈️ Destination Details:");
        System.out.print("Please enter your destination: ");
        String destination = scanner.nextLine();
        
        // Section 2: Date Details
        String date = "";
        while (true) {
            System.out.println("\n📅 Travel Date:");
            System.out.print("Enter your travel date (yyyy-mm-dd): ");
            date = scanner.nextLine();
            if (isValidDate(date)) {
                break;
            } else {
                System.out.println("❗ Invalid date format! Please use yyyy-mm-dd format.");
            }
        }
    
        // Section 3: Preferences
        System.out.println("\n🎒 Travel Preferences:");
        System.out.print("What are your preferences? (e.g., adventure, relaxation, cultural): ");
        String preferences = scanner.nextLine();

        // Fetch weather information (mocked)
        String weather = getWeather(destination);

        // Section 4: Budget
        double budget = 0;
        while (true) {
            System.out.println("\n💸 Budget Details:");
            System.out.print("Enter your budget for the trip ($): ");
            if (scanner.hasNextDouble()) {
                budget = scanner.nextDouble();
                if (budget > 0) break;
                else System.out.println("❗ Budget must be greater than $0.");
            } else {
                System.out.println("❗ Invalid input. Please enter a valid number for the budget.");
                scanner.next(); // Consume the invalid input
            }
        
        }
        
        // Clear the buffer after reading the double value
        scanner.nextLine();

        return new TravelPlan(destination, date, weather, budget, preferences);
    }

    // Method to display the itinerary
    public static void displayItinerary(List<TravelPlan> itinerary) {
        System.out.println("\n🎉 Here’s your personalized travel itinerary!");

        if (itinerary.isEmpty()) {
            System.out.println("❗ You haven't added any destinations yet.");
            return;
        }

        // Displaying the complete itinerary
        for (TravelPlan plan : itinerary) {
            System.out.println(plan);
            System.out.println(getMapInfo(plan.destination));
        }
    }

    // Method to edit the itinerary (destination, date, or budget)
    public static void editItinerary(TravelPlan plan) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n🖋️ You can edit the following details for: " + plan.destination);

        System.out.println("1. Edit Destination");
        System.out.println("2. Edit Date");
        System.out.println("3. Edit Budget");
        System.out.println("4. Cancel");
        System.out.print("Please select an option (1-4): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        switch (choice) {
            case 1:
                System.out.print("Enter new destination: ");
                String newDestination = scanner.nextLine();
                plan.updateDetails(newDestination, plan.date, plan.budget);
                break;
            case 2:
                String newDate = "";
                while (true) {
                    System.out.print("Enter new travel date (yyyy-mm-dd): ");
                    newDate = scanner.nextLine();
                    if (isValidDate(newDate)) {
                        plan.updateDetails(plan.destination, newDate, plan.budget);
                        break;
                    } else {
                        System.out.println("❗ Invalid date format! Please use yyyy-mm-dd format.");
                    }
                }
                break;
            case 3:
                double newBudget = 0;
                while (true) {
                    System.out.print("Enter new budget for the trip ($): ");
                    if (scanner.hasNextDouble()) {
                        newBudget = scanner.nextDouble();
                        if (newBudget > 0) {
                            plan.updateDetails(plan.destination, plan.date, newBudget);
                            break;
                        } else {
                            System.out.println("❗ Budget must be greater than $0.");
                        }
                    } else {
                        System.out.println("❗ Invalid input. Please enter a valid number for the budget.");
                        scanner.next(); // Consume the invalid input
                    }
                }
                break;
            case 4:
                System.out.println("❌ Cancelled editing.");
                break;
            default:
                System.out.println("❗ Invalid option.");
        }
    }

    // Main method to run the travel itinerary planner
    public static void main(String[] args) {
        List<TravelPlan> itinerary = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("✨ Welcome to the Travel Itinerary Planner! ✨");
        System.out.println("Let's create a memorable journey!");

        boolean addMoreDestinations = true;
        while (addMoreDestinations) {
            TravelPlan plan = inputTravelDetails();
            itinerary.add(plan);

            System.out.println("\n🔄 Would you like to add another destination? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("no")) {
                addMoreDestinations = false;
            }
        }

        // Display the complete itinerary
        displayItinerary(itinerary);

        // Edit itinerary functionality
        System.out.println("\n🔄 Do you want to edit any destination details? (yes/no): ");
        String editResponse = scanner.nextLine();
        if (editResponse.equalsIgnoreCase("yes")) {
            System.out.print("Enter the destination number to edit (1-" + itinerary.size() + "): ");
            int destinationNumber = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            if (destinationNumber > 0 && destinationNumber <= itinerary.size()) {
                editItinerary(itinerary.get(destinationNumber - 1));
            } else {
                System.out.println("❗ Invalid destination number.");
            }
        }
    
        // Display the updated itinerary
        displayItinerary(itinerary);

        // Farewell message
        System.out.println("\n👋 Safe travels! Enjoy your adventure!");
    
    }
}

