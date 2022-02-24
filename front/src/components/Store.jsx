import React, { useReducer, createContext } from 'react';
import  Reducer  from "../funciones/Reducer";


  const Store = createContext()

    const StoreProvider = ({ children }) => {
     
        const initialState = {
          todo: { list: [], item: {} }
        };  

        const [state, dispatch] = useReducer(Reducer, initialState);
      
        return <Store.Provider value={{ state, dispatch }}>
          {children}
        </Store.Provider>
      
      }
      export default StoreProvider;
      export {Store};
