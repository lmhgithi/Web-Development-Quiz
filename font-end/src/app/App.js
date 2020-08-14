import React, { Component } from 'react';
import '../styles/app.less';
import { Route, BrowserRouter, Link, Switch } from "react-router-dom";

import NotMatch from './NotMatch'
import Product from './Products';
import Order from './Orders';
import orderNotExists from './OrderNotExists';

class App extends Component {
  render() {
    return (
      <div className="app">
        <BrowserRouter>
          <div className="header">
            <Link className='homeLink' to="/">商城</Link>
            <Link className='orderLink' to="/order">订单</Link>
          </div>
          <div className="body">
            <Switch>
              <Route exact path='/' component={Product} />
              <Route exact path='/order' component={Order} />
              <Route exact path='/orderNotExixts' component={orderNotExists} />
              <Route component={NotMatch} />
            </Switch>
          </div>
        </BrowserRouter>
      </div>
    );
  }
}

export default App;
