import styles from '../styles/Input.module.css'
import {faPlus, faTrash} from '@fortawesome/free-solid-svg-icons';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import React, {Component} from 'react';

/**
 * The multiple input Component
 */
class MultiInput extends Component {
    state = {
        list: [],
    };

    componentDidMount() {
        try {
            if (this.props.defaultValue && this.props.defaultValue.length > 0) { //array
                this.setState({list: this.props.defaultValue}) //array
            }
        } catch (e) {
            console.log(e)
        }
        // try {
        //     if (this.props.value.length > 0) { //array
        //         this.setState({list: this.props.value}) //array
        //     }
        // } catch (e) {
        //     console.log(e)
        // }
    }

    render() {
        return (
            <div className={styles.divs}>
                <div className={styles.divsleft}>
                    <label className={styles.inputs} name={this.props.name}
                           data-required={this.props.required}>

                        {this.props.label}:
                    </label>
                </div>
                <div className={styles.divsright}>
                    {
                        this.state.list.map((list, index) => {
                            return (
                                //value={list} duplicate in input
                                <div key={index} className={styles.divsInsideCell}>
                                    <input className={styles.inputFeld}
                                           id={this.props.id}
                                           type={this.props.type}
                                           value={list.label}
                                           defaultValue={list.label}
                                           disabled={this.props.disabled}
                                           required={this.props.required} name={this.props.name}
                                           onChange={(e) => this.handleChange(e, index)}
                                    />
                                    <button className={styles.buttonInside} hidden={this.props.disabled}
                                            onClick={() => this.handleRemove(index)}>
                                        <FontAwesomeIcon icon={faTrash}/>
                                    </button>
                                </div>
                            )
                        })
                    }
                    <div className={styles.divsbuttons} hidden={this.props.disabled}>
                        <button className={styles.buttons} onClick={(e) => this.addList(e)}><FontAwesomeIcon
                            icon={faPlus}/></button>
                        {/*<button className={styles.buttons} onClick={(e) => this.handleSubmit(e)}><FontAwesomeIcon*/}
                        {/*    icon={faSave}/></button>*/}
                    </div>
                </div>
            </div>
        );
    }

    addList(e) {
        this.setState({list: [...this.state.list, '']})
    }

    handleChange(e, index) {
        //this.state.list.splice(index, e.target.value)
        this.state.list[index].setState(e.target.value)
        //this.state.list[index] = e.target.value
        this.setState({list: this.state.list})
    }

    handleRemove(index) {
        this.state.list.splice(index, 1)
        this.setState({list: this.state.list})
    }

    handleSubmit(e) {
        console.log(this.state)
    }
}

export default MultiInput;