const editReceta = (id) => {
    alert(`Editar receta con ID: ${id}`);
    // Implementar lógica para abrir el modal con los datos precargados
};

const deleteReceta = async (id) => {
    const token = localStorage.getItem("token");

    try {
        const response = await fetch(`http://localhost:8082/api/recetas/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`,
            },
        });

        if (response.ok) {
            alert("Receta eliminada con éxito.");
            window.location.reload();
        } else {
            alert("Error al eliminar receta.");
        }
    } catch (error) {
        console.error("Error al eliminar receta:", error);
    }
};
