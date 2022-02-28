import React, { useReducer, createContext } from 'react';
import  Reducer  from "./Reducer";


  

    
     
        const initialState = {
          todo: { list: [], item: {} },
          listTodo: { list: [], item: {} }
        };  

        const Store = createContext()

      const StoreProvider = ({ children }) => {
        const [state, dispatch] = useReducer(Reducer, initialState);
      
        return <Store.Provider value={{ state, dispatch }}>
          {children}
        </Store.Provider>
      
      }
      export default Store;
      export {StoreProvider};
