### React Component Code Review

- List down all the problems in the component below
- Refactor the component to fix the mentioned problems

```jsx
import React from 'react';
import PropTypes from 'prop-types';

class SomeListComponent extends React.Component {
  constructor (props) {
    this.state = { items: props.items }
  }

  shouldComponentUpdate (nextProps) {
    return nextProps.items !== this.props.items
  }

  handleClick (index) {
    this.props.onClick(index)
  }

  renderElement (item, index) {
    return <li onClick={() => this.handleClick(index)}>{item.text}</li>
  }
  
  renderElements () => this.state.items.map((item, i) => this.renderElement(item, i));

  render () {
    return (
      <ul style={{ backgroundColor: 'red', height: 100 }}>
        // converting the re to a function,
        // as render function should be compsition of components.
        {this.renderElements()}
      </ul>
    )
  }
}

// adding default props & proptypes to avoid runtime exceptions.
SomeListComponent.defaultProps = { 
  items: [],
  onClick: () => {}
};

SomeListComponent.propTypes = {
  items: PropTypes.array,
  onClick: PropTypes.func
}

export default SomeListComponent
```
