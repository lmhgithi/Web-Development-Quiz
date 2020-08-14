import React, { Component } from 'react';
import '../styles/app.less';
import { Route, BrowserRouter, Link, Switch } from "react-router-dom";

import NotMatch from './NotMatch'
import Product from './Products';

class App extends Component {
  render() {
    return (
      <div className="app">
        <BrowserRouter>
          <div className="header">
            <Link className='homeLink' to="/">商城</Link>
          </div>
          <div className="body">
            <Switch>
              <Route exact path='/' component={Product} />
              <Route component={NotMatch} />
            </Switch>
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
