
function Reducer(state, action) {
    switch (action.type) {

      case "add-group":
        const listUp = state.listTodo.list;
        listUp.push(action.item);
        return { ...state, listTodo: { list:listUp, item: {} } };
  
      case "delete-group":
        const listUpDelete = state.listTodo;
        const listUpdate = listUpDelete.list.filter((item) => {
          return item.id !== action.id;
        });
        listUpDelete.list = listUpdate;
        return { ...state, listTodo: listUpDelete };
  
        case "update-list-group":
          const todoListUpList = state.listTodo;
          todoListUpList.list = action.list;
          return { ...state, listTodo: todoListUpList };


//* ************************************************************************************** */


      case 'update-item':
        const todoUpItem = state.todo;
        const listUpdateEdit = todoUpItem.list.map((item) => {
          if (item.id === action.item.id) {
            return action.item;
          }
          return item;
        });
        todoUpItem.list = listUpdateEdit;
        todoUpItem.item = {};
        return { ...state, todo: todoUpItem }
      case 'delete-item':
        const todoUpDelete = state.todo;
        const lisUpdate = todoUpDelete.list.filter((item) => {
          return item.id !== action.id;
        });
        todoUpDelete.list = lisUpdate;
        return { ...state, todo: todoUpDelete }
      case 'update-list':
        const todoUpList = state.todo;
        todoUpList.list = action.list;
        return { ...state, todo: todoUpList }
      case 'edit-item':
        const todoUpEdit = state.todo;
        todoUpEdit.item = action.item;
        return { ...state, todo: todoUpEdit }
      case 'add-item':
        const todoUp = state.todo.list;
        todoUp.push(action.item);
        return { ...state, todo: {list: todoUp, item: {}} }
      default:
        return state;
    }
  }
  export default Reducer;