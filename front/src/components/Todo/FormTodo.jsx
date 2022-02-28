import React, {useContext, useRef, useState}from "react";
import Store from "../../funciones/Store";
import HOST_API from "../../conexion/Host_API";
import Swal from "sweetalert2";

const FormTodo = (props) => {
    const formRef = useRef(null);
    const { dispatch, state: { todo } } = useContext(Store);
    const item = todo.item;
    const [state, setState] = useState(item);
  
    const onAdd = (event) => {
      event.preventDefault();

      if (state.name === "" || state.name === null || state.name === undefined) {
        Swal.fire({
          icon: "error",
          title: "El nombre de la Tarea no puede estar vacio",
          text: 'Recuerda ingresar un nombre antes de crear la tarea',
        });
        return;
      }

  
      const request = {
        name: state.name,
        id: null,
        completed: true
      };
  
  
      fetch(HOST_API + "/save/"+ props.id, {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        },
      })
        .then(response => response.json())
        .then((todo) => {
          dispatch({ type: "add-item", item: todo });
          setState({ name: "" });
          formRef.current.reset();
        });

        Swal.fire(
          '¡Tarea creada!',
          'La tarea ' + state.name + ' fue creada.'
        )
    };
  
  
      const onEdit = (event) => {
        event.preventDefault();
      if (state.name === "" || state.name === null || state.name === undefined) {
        Swal.fire({
          icon: "error",
          title: "El nombre de una Tarea no puede estar vacio",
          text:
            "Recuerda ingresar un nombre para la tarea que deseas actualizar "
        });
        return;
      }
  
      const request = {
        name: state.name,
        id: item.id,
        isCompleted: item.isCompleted,
      };

      fetch(HOST_API + "/update/todo"+ props.id, {
        method: "PUT",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((todo) => {
          dispatch({ type: "update-item", item: todo });
          setState({ name: "" });
          formRef.current.reset();
        });

        Swal.fire(
          '¡Tarea Actualizada!',
          'La tarea ' + state.name + ' se ha Actualizado.',
        )
    };
  
    return (
      <form ref={formRef}>
        <div className="container input-group mt-3">
          <input
            type="text"
            name="name"
            className="form-control"
            placeholder="¿Qué piensas hacer hoy?"
            defaultValue={item.name}
            onChange={(event) => {
              setState({ ...state, name: event.target.value });
            }}
          ></input>
          {item.id && (
            <button className="btn btn-primary" onClick={onEdit}>
              Actualizar Tarea
            </button>
          )}
          {!item.id && (
            <button className="btn btn-success" onClick={onAdd}>
              Crear Tarea
            </button>
          )}
        </div>
      </form>
    );
  
}
  export default FormTodo;