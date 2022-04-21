import React, { Component } from 'react'
import Select from 'react-select'
import styles from '../styles/MultiSelect.module.css'

/**
 * The multiple select Component
 */const customStyles = {
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
  },
  multiValue: (styles, { data }) => {
    return {
      ...styles,
      backgroundColor: 'white'
    }
  }

}

/**
 * The multiple select Component
 */
class MultiSelect extends Component {
  render () {
    return (
      <div className={styles.divs}>
        <div className={styles.divsleft}>
          <label
            className={styles.inputs} name={this.props.name}
            data-required={this.props.required}
          >
            {this.props.label} (Mehrfachauswahl):
          </label>
        </div>
        <div className={styles.divsright}>
          <Select
            styles={customStyles}
            name={this.props.name}
            isMulti
            options={this.props.options}
            isDisabled={this.props.disabled}
                        // value={this.props.value}
            defaultValue={this.props.defaultValue}
          />
        </div>

      </div>
    )
  }
}

export default MultiSelect
