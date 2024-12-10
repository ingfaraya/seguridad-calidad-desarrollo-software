document.addEventListener("DOMContentLoaded", () => {
    const recetasContainer = document.getElementById("recetas-container");
    const prevPageButton = document.getElementById("prev-page");
    const nextPageButton = document.getElementById("next-page");
    let currentPage = 0;
    const pageSize = 6;

    const renderRecetas = async () => {
        const token = localStorage.getItem("token");
        if (!token) {
            $('#loginModal').modal('show');
            return;
        }

        const role = localStorage.getItem("role");
        const username = localStorage.getItem("username");

        try {
            const response = await fetch(`http://localhost:8081/api/recetas`, {
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`,
                },
            });

            if (response.ok) {
                const recetas = await response.json();
                const start = currentPage * pageSize;
                const end = Math.min(start + pageSize, recetas.length);
                recetasContainer.innerHTML = "";

                recetas.slice(start, end).forEach((receta) => {
                    recetasContainer.innerHTML += `
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">${receta.nombre}</h5>
                                    <p class="card-text">Tipo de cocina: ${receta.tipoCocina}</p>
                                    <p class="card-text">Tiempo de cocción: ${receta.tiempoCoccion}</p>
                                    ${
                                        role === "ADMIN"
                                            ? `<p class="card-text">Ingredientes: ${receta.ingredientes}</p>
                                               <p class="card-text">Instrucciones: ${receta.instrucciones}</p>
                                               <button class="btn btn-primary" onclick="editReceta(${receta.id})">Editar</button>
                                               <button class="btn btn-danger" onclick="deleteReceta(${receta.id})">Eliminar</button>`
                                            : role === "USER" && receta.usuario === username
                                            ? `<button class="btn btn-primary" onclick="editReceta(${receta.id})">Editar</button>`
                                            : ""
                                    }
                                </div>
                            </div>
                        </div>
                    `;
                });

                prevPageButton.disabled = currentPage === 0;
                nextPageButton.disabled = end >= recetas.length;
            } else {
                alert("Error al cargar recetas.");
            }
        } catch (error) {
            console.error("Error al cargar recetas:", error);
        }
    };

    prevPageButton.addEventListener("click", () => {
        if (currentPage > 0) {
            currentPage--;
            renderRecetas();
        }
    });

    nextPageButton.addEventListener("click", () => {
        currentPage++;
        renderRecetas();
    });

    renderRecetas();
});
