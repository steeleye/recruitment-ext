import React, {h} from 'react'
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { getItems, onClick } from "Actions/items_action";

class SomeListComponent extends React.Component {
    constructor (props) {
        super(props);
        this.state = { items: '' }
    }

    componentWillMount() {
        this.props.dispatch(getItems());
    }

    componentDidUpdate() {
        this.state = { items: this.props.itemsList }
    }

    shouldComponentUpdate (nextProps) {
        return nextProps.itemsList !== this.props.itemsList
    }

    handleClick (index) {
        this.props.dispatch(onClick(index));
    }

    renderElement (item, index) {
        return <li onClick={() => this.handleClick(index)}>{item.text}</li>
    }

    render () {
        if(this.state.items) {
            return (
                <ul style={{backgroundColor: 'red', height: 100}}>
                    {this.state.items.map((item, i) => this.renderElement(item, i))}
                </ul>
            )
        }
    }
}

function mapstateToProps(state) {
    return {
        itemsList: state.itemreducer.items,
    };
}
const SomeListComponentRouting = withRouter(SomeListComponent);

export default connect(mapstateToProps)(SomeListComponentRouting);
