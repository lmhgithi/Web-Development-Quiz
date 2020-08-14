import React, { Component } from 'react';
import Order from './Order'
import '../styles/Orders.less'
import OrderNotExists from './OrderNotExists'

class Orders extends Component {
    state = {
        data: [],
    }

    componentDidMount = () => {
        URL = "http://localhost:8080/order"
        fetch(URL, {
            method: "GET",
        }).then(Response => {
            if (Response.status === 200) {
                return Response.json();
            }
            else {
                Promise.reject();
            }
        }).then(jsonData => {
            console.log(jsonData)
            if (jsonData.length === 0) {
                this.props.history.push('/orderNotExixts')
            }
            else {
                this.setState({
                    data: jsonData,
                })
            }
        })

    }

    render() {
        console.log(this.state.data)
        return (
            <div className="orderList">
                <div className='info'>
                    <h2>名字</h2>
                    <h2>单价</h2>
                    <h2>数量</h2>
                    <h2>单位</h2>
                    <h2>操作</h2>
                </div>
                {
                    this.state.data.map(order => (
                        <Order
                            key={order.name}
                            order={order}
                        />
                    ))
                }
            </div>
        )
    };
}

export default Orders;