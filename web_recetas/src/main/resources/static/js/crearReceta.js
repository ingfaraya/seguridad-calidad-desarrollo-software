document.addEventListener("DOMContentLoaded", () => {
    const createRecipeModal = new bootstrap.Modal(document.getElementById("createRecipeModal"));
    const createRecipeForm = document.getElementById("create-recipe-form");

    document.getElementById("create-recipe-button").addEventListener("click", () => {
        document.getElementById("createRecipeModalLabel").innerText = "Crear Receta";
        createRecipeForm.reset();
        createRecipeModal.show();
    });

    createRecipeForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const token = localStorage.getItem("token");
        const recipeData = {
            nombre: document.getElementById("nombre").value,
            ingredientes: document.getElementById("ingredientes").value,
            instrucciones: document.getElementById("instrucciones").value,
            tipoCocina: document.getElementById("tipoCocina").value,
            paisOrigen: document.getElementById("paisOrigen").value,
            tiempoCoccion: document.getElementById("tiempoCoccion").value,
            dificultad: document.getElementById("dificultad").value,
            usuario: localStorage.getItem("username"),
        };

        try {
            const response = await fetch("http://localhost:8082/api/recetas", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`,
                },
                body: JSON.stringify(recipeData),
            });

            if (response.ok) {
                alert("Receta creada con éxito.");
                createRecipeModal.hide();
                window.location.reload();
            } else {
                alert("Error al guardar la receta.");
            }
        } catch (error) {
            console.error("Error al crear receta:", error);
        }
    });
});
