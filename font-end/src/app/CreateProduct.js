import React, { Component } from 'react';
import '../styles/CreateProduct.less'
class CreateProduct extends Component {
    state = {
        name: '',
        price: '',
        unit: '',
        url: '',
    }
    handleClickaddProductButton = () => {
        console.log(this.state)
        URL = `http://localhost:8080/product/`
        fetch(URL, {
            method: "POST",
            body: this.state.json(),
        }).then(Response => {
            if (Response.status === 200) {
                this.setState({
                    handleFlag: false,
                })
            }
            else {
                Promise.reject();
            }
        });
    }

    handleChange = (field, event) => {
        this.setState({
            [field]: event.target.value,
        })
    }


    render() {
        return (
            <div className="product">
                <h2>添加商品</h2>
                <form onSubmit={this.handleClickaddProductButton}>
                    <label htmlFor='name'>名称</label>
                    <input type='text' name='name'
                        value={this.state.name}
                        placeholder='名称'
                        onChange={() => { this.handleChange('name', event) }}
                    />

                    <label htmlFor='name'>价格</label>
                    <input type='text' name='price'
                        value={this.state.price}
                        placeholder='价格'
                        onChange={() => { this.handleChange('price', event) }}
                    />

                    <label htmlFor='name'>单位</label>
                    <input type='text' name='unit'
                        value={this.state.unit}
                        placeholder='单位'
                        onChange={() => { this.handleChange('unit', event) }}
                    />

                    <label htmlFor='name'>图片</label>
                    <input type='text' name='url'
                        value={this.state.url}
                        placeholder='URL'
                        onChange={() => { this.handleChange('url', event) }}
                    />

                    <input className='submitButton' type='submit' name='Submit'
                        disabled={!this.state.name ||
                            !this.state.price ||
                            !this.state.unit ||
                            !this.state.url}
                        onClick={this.handleClickaddProductButton}
                    />
                </form>

            </div>
        )
    };
}

export default CreateProduct;