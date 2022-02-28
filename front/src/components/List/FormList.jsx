import React, { useRef, useState, useContext } from "react";
import Store from "../../funciones/Store";
import HOST_API from "../../conexion/Host_API";
import Swal from "sweetalert2";

function FormList() {
    const formRef = useRef(null);
    const {
        dispatch, state: { listTodo },
    } = useContext(Store);
    const item = listTodo.item;
    const [state, setState] = useState(item);

    const onAdd = (event) => {
        event.preventDefault();

        if (state.name === "" || state.name === null || state.name === undefined) {
            Swal.fire({
                icon: 'error',
                title: 'El nombre de un grupo no puede ser vacío',
                text: 'Recuerda ingresar un nombre para la Lista"',
            });
            return;
        }

        const request = {
            name: state.name,
            id: null,
        };

        fetch(HOST_API + "/save", {
            method: "POST",
            body: JSON.stringify(request),
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then((response) => response.json())
            .then((list) => {
                dispatch({ type: "add-group", item: list });
                setState({ name: "" });
                formRef.current.reset();
            }).catch(error => console.log(error.message));

        Swal.fire(
            '¡Grupo creado!',
            'El grupo ' + state.name + ' se ha creado.',
        );
    };

    return (
        <form ref={formRef}>
            <div className="container input-group mt-3">
                <input
                    type="text"
                    name="name"
                    className="form-control"
                    placeholder="Ingresa el nombre del grupo de tareas"
                    defaultValue={item.name}
                    onChange={(event) => {
                        setState({ ...state, name: event.target.value });
                    } }
                ></input>
                <button className="btn btn-success" onClick={onAdd}>
                    Agregar Lista
                </button>
            </div>
        </form>
    );
};

export default FormList;