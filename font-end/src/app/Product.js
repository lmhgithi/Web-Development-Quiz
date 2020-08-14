import React, { Component } from 'react';
import img from '../images/cola.jpg'
class Product extends Component {
    state = {
        data: '',
    }

    render() {
        console.log(this.state.data)
        return (
            <div className="product">
                <img className='productImg' src={img} alt='placeHoder' />
                <div className='info'>
                    <h2>{this.props.product.name}</h2>
                    {/* {this.state.product.price} */}
                    <p className='unit'>单价：1/{this.props.product.unit}</p>
                </div>


            </div>
        )
    };
}

export default Product;