import React, { Component } from 'react';
import img from '../images/cola.jpg'
class Product extends Component {
    state = {
        handleFlag: false,
    }
    handleClickaddProductButton = () => {
        this.setState({
            handleFlag: true,
        })
        URL = "http://localhost:8080/order"
        fetch(URL, {
            method:"POST",
        }).then(Response => {
            if (Response.status === 200) {
                this.setState({
                    handleFlag: false,
                })
                return Response.json();
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
            <div className="product">
                <img className='productImg' src={img} alt='placeHoder' />
                <div className='info'>
                    <h2>{this.props.product.name}</h2>
                    <p className='unit'>单价：{this.props.product.price}/{this.props.product.unit}</p>
                </div>
                <button className='addProductButton'
                    onClick={this.handleClickaddProductButton}
                    disabled={this.state.handleFlag}
                >
                    +
                </button>


            </div>
        )
    };
}

export default Product;