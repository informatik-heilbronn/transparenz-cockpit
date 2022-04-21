import styles from '../styles/Input.module.css'
import React, {Component} from 'react';


/**
 * The single input Component
 */
class SingleInput extends Component {
    state = {
        singleState: 'string',
    };

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
                    <input className={styles.inputFeld} type={this.props.type} //TextareaAutosize
                        //value={this.props.value}
                           disabled={this.props.disabled}
                           defaultValue={this.props.defaultValue}
                           required={this.props.required} name={this.props.name} id={this.props.id}
                           onChange={(e) => this.handleChange(e)}
                    />
                    {/*<button className={styles.buttons}><FontAwesomeIcon icon={faEdit}*/}
                    {/*                                                    className="icons"/></button>*/}
                    {/*<button className={styles.buttons} onClick={(e) => this.handleSubmit(e)}><FontAwesomeIcon*/}
                    {/*    icon={faSave}/></button>*/}
                </div>

            </div>
        );
    }

    handleSubmit(e) {
        console.log(this.state)
    }

    handleChange(e) {
        this.setState({singleState: e.target.value})
    }
}

export default SingleInput;