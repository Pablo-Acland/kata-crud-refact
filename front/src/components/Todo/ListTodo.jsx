import React, {useContext, useEffect, } from "react";
import Store from "../../funciones/Store";
import HOST_API from "../../conexion/Host_API";
import Swal from "sweetalert2";

const ListTodo = (props) => {
    
    const { dispatch, state: { todo } } = useContext(Store);
    const currentList = todo.list.filter(todo => {
      return todo.Idlist === props.idlist;
    });
  
    useEffect(() => {
      fetch(HOST_API + "/todo/all")
        .then(response => response.json())
        .then((list) => {
          dispatch({ type: "update-list", list })
        })
    }, [dispatch]);
  
  
    const onDelete = (id) => {
      Swal.fire({
        title: '¿Estas seguro de querer eliminar la tarea?',
        showDenyButton: true,
        confirmButtonText: 'SI',
        denyButtonText: `NO`,
      }).then((result) => {
          if (result.isConfirmed) {
          fetch(HOST_API + "/delete/"+ id, {
            method: "DELETE"
          }).then((list) => {
            dispatch({ type: "delete-item", id })
          })
          Swal.fire('¡Eliminada!')
        }
      })
    };
  
    const onEdit = (todo) => {
      dispatch({ type: "edit-item", item: todo })
    };
  
    const onChange = (event, todo) => {
      const request = {
        name: todo.name,
        id: todo.id,
        completed: event.target.checked
      };
      fetch(HOST_API + "/update/todo/"+ props.id, {
        method: "PUT",
        body: JSON.stringify(request),
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => response.json())
        .then((todo) => {
          dispatch({ type: "update-item", item: todo });
        });
    };
  
    const decorationDone = {
      textDecoration: 'line-through'
    };
    return <div>
      <table >
        <thead>
          <tr>
            <td>ID</td>
            <td>Tarea</td>
            <td>¿Completado?</td>
          </tr>
        </thead>
        <tbody>
          {currentList.map((todo) => {
            return <tr key={todo.id} style={todo.completed ? decorationDone : {}}>
              <td>{todo.id}</td>
              <td>{todo.name}</td>
              <td><input type="checkbox" defaultChecked={todo.completed} onChange={(event) => onChange(event, todo)}></input></td>
              <td><button className="btn btn-danger" onClick={() => onDelete(todo.id)}>Eliminar</button></td>
              <td><button className="btn btn-primary" onClick={() => onEdit(todo)}>Editar</button></td>
            </tr>
          })}
        </tbody>
      </table>
    </div>

}

  export default ListTodo;