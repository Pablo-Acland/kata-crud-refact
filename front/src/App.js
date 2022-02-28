import React from 'react';

import { StoreProvider } from './funciones/Store';
import FormList from './components/List/FormList';
import ListList from './components/List/ListList';

function App() {

  return <StoreProvider>
    <div className="container">
    <h3>To-Do List</h3>
    <FormList />
    <ListList />
    </div>
  </StoreProvider>
}

export default App;
