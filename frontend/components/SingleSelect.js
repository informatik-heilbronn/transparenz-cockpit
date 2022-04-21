import React, { Component, useState } from 'react'
import Select from 'react-select'
import styles from '../styles/Select.module.css'
// import styles from '../styles/Input.module.css'
import { faEdit, faSave } from '@fortawesome/free-regular-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

/**
 * The single select Component
 */
const customStyles = {
  control: (provided, state) => ({
    ...provided,
    backgroundColor: '#ececec',
    boxShadow: 'rgba(0, 0, 0, 0.3) 0px 3px 6px',
    border: state.isFocused && 'none'
  }),
  option: (provided, state) => ({
    ...provided,
    // borderBottom: '1px dotted pink',
    color: state.isSelected ? 'white' : 'blue',
    boxShadow: 'none',
    padding: 10
  }),
  singleValue: (provided, state) => {
    const opacity = state.isDisabled ? 0.5 : 1
    const transition = 'opacity 300ms'

    return { ...provided, opacity, transition }
  }

}

/**
 * The single select Component
 */
class SingleSelect extends Component {
  render () {
    return (
      <div className={styles.divs}>
        <div className={styles.divsleft}>
          <label
            className={styles.inputs} name={this.props.name}
            data-required={this.props.required}
          >
            {this.props.label}:
          </label>
        </div>
        <div className={styles.divsright}>
          <Select
            styles={customStyles}
            name={this.props.name}
            options={this.props.options}
            defaultValue={this.props.defaultValue}
                        // value={this.props.value}
            isDisabled={this.props.disabled}
          />
        </div>

      </div>
    )
  }
}

export default SingleSelect
