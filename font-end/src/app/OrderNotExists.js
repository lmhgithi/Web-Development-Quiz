import React, { Component } from 'react';
class orderNotExists extends Component {
    state = {
        handleFlag: false,
    }

    render() {
        return (
            <div className="orderNotExists">
                <h1>暂无订单，返回商城页面继续购买</h1>
            </div>
        );
    };
}

export default orderNotExists;