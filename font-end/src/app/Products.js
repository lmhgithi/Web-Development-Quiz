import React, { Component } from 'react';
import Product from './Product'
import '../styles/Products.less'

class Products extends Component {
    state = {
        data: [],
    }

    componentDidMount = () => {
        URL = "http://localhost:8080/product"
        fetch(URL, {
            method:"GET",
        }).then(Response => {
            if (Response.status === 200) {
                return Response.json();
            }
            else {
                Promise.reject();
            }
        }).then(jsonData => {
            this.setState({
                data: jsonData,
            })
        })

    }
    render() {
        console.log(this.state.data)
        return (
            <div className="productList">
                {
                    this.state.data.map(product => (
                        <Product
                        key = {product.name}
                        product = {product}
                        />
                    ))
                }
            </div>
        )
    };
}

export default Products;