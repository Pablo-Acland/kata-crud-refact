import React, { useContext, useEffect } from "react";
import Swal from "sweetalert2";
import StoreProvider, { Store } from "../../funciones/Store";
import ListTodo from "../Todo/ListTodo";
import FormTodo from "../Todo/FormTodo";
import HOST_API from "../../conexion/Host_API";

const ListGroups = () => {
  const { state: { listTodo }, dispatch } = useContext(StoreProvider);
  const currentList = listTodo.list;

  useEffect(() => {
    fetch(HOST_API + "/Lists")
      .then((response) => response.json())
      .then((list) => {
        dispatch({ type: "update-list-group", list });
      });
  }, [dispatch]);

  const onDelete = (id) => {

    Swal.fire({
      title: '¿Estas seguro de eliminar el grupo?',
      showDenyButton: true,
      confirmButtonText: 'SI',
      denyButtonText: `NO`,
    }).then((result) => {
      if (result.isConfirmed) {
        fetch(HOST_API + "/delete/" + id, {
          method: "DELETE",
        }).then((list) => {
          dispatch({ type: "delete-group", id });
        });
        Swal.fire('¡Eliminado!', '', 'success')
      }
    })
    
  };

  return (
    <div className="text-center container input-group">
      {currentList.map((listTodo) => {
        return (
          <div key={listTodo.id} className="container text-center mt-3 p-3">
            <fieldset className="border">
              <legend className="w-auto">
                <b>Grupo: </b> {listTodo.name.toUpperCase()}
                <button className="btn btn-danger m-4" onClick={() => onDelete(listTodo.id)}>
                  Eliminar
                </button>
              </legend>
              {<FormTodo id={listTodo.id} />}
              {<ListTodo id={listTodo.id} />}
            </fieldset>
          </div>
        );
      })}
    </div>
  );
};

export default ListGroups;