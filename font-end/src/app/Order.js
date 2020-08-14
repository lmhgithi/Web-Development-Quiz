import React, { Component } from 'react';
class Order extends Component {
    state = {
        handleFlag: false,
    }

    render() {
        return (
            <div className="order">
                <div className='info'>
                    <h2>{this.props.order.name}</h2>
                    <h2>{this.props.order.price}</h2>
                    <h2>{this.props.order.quantity}</h2>
                    <h2>{this.props.order.unit}</h2>
                </div>
            </div>
        );
    };
}

export default Order;