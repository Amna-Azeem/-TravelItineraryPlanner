document.addEventListener("DOMContentLoaded", function () {
    fetchPlans(); 

    document.getElementById("travelForm").addEventListener("submit", function (event) {
        event.preventDefault();
        
        let destination = document.getElementById("destination").value;
        let date = document.getElementById("date").value;
        let budget = document.getElementById("budget").value;

        fetch("/api/add", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `destination=${destination}&date=${date}&budget=${budget}`
        }).then(() => {
            fetchPlans();
            document.getElementById("travelForm").reset();
        });
    });
});

function fetchPlans() {
    fetch("/api/plans")
        .then(response => response.json())
        .then(data => {
            let plansList = document.getElementById("plansList");
            plansList.innerHTML = "";
            data.forEach(plan => {
                let li = document.createElement("li");
                li.textContent = `${plan.destination} - ${plan.date} - $${plan.budget}`;
                plansList.appendChild(li);
            });
        });
}
