import React, { Component } from 'react';
class Order extends Component {
    state = {
        handleFlag: false,
    }

    handleInfoClick = () => {
        URL = `http://localhost:8080/order/${this.props.order.orderId}`
        fetch(URL, {
            method:"DELETE",
        }).then(Response => {
            if (Response.status === 200) {
                this.setState({
                    handleFlag: false,
                })
            }
            else {
                this.setState({
                    handleFlag: false,
                })
                Promise.reject();
            }
        });
    }
    render() {
        return (
            <div className="order">
                <div className='info'>
                    <h2>{this.props.order.name}</h2>
                    <h2>{this.props.order.price}</h2>
                    <h2>{this.props.order.quantity}</h2>
                    <h2>{this.props.order.unit}</h2>
                    <button
                    onClick = {this.handleInfoClick}
                    >删除</button>
                </div>
            </div>
        );
    };
}

export default Order;