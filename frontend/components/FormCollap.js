import React, {Component, useState} from 'react';
import styles from '../styles/Form.module.css';
import Collapsible from "./Collapsible"
import Swal from 'sweetalert2'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimesCircle} from "@fortawesome/free-regular-svg-icons";
import Form from "./Form";
import {faTrash} from "@fortawesome/free-solid-svg-icons";

/**
 * The Form Collapsible Component
 */
class FormCollap extends Component {
    state = {
        form: []
    }

    addField = async (e) => {
        const {value: titelText} = await Swal.fire({
            title: 'Neue Gruppe Hinzufügen!',
            input: 'text',
            inputPlaceholder: 'Titel der Gruppe eingeben',
            confirmButtonText: "Weiter",
            showCancelButton: true,
            cancelButtonColor: "#940909",
            cancelButtonText: "Abbrechen",
            width: 700,
            confirmButtonColor: '#25ce0c',
            inputValidator: (value) => {
                if (!value) {
                    return 'bitte text eingeben!'
                } else {
                    this.setState((prevState) => ({
                        form: [...prevState.form, {label: "", input: "", inputTitle: (value)}],
                    }));
                }
            }
        })

    }


    render() {
        let {form} = this.state
        return (
            <div>
                <div className={styles.divOut}>
                    {
                        form.map((val, idx) => {
                            let labelID = `label-${idx}`, InputID = `input-${idx}`
                            return (
                                <div key={idx} className={styles.divDyn}>
                                    <Collapsible label={val.inputTitle}
                                                 disabled={false}
                                                 name={labelID}
                                                 required={false} content={
                                        <div>
                                            <Form/>
                                        </div>
                                    }/>
                                    <div>
                                        <button className={styles.buttonsIconColl}
                                                onClick={(e) => this.handleRemove(idx)}>
                                            <FontAwesomeIcon
                                                icon={faTrash}/></button>
                                    </div>
                                </div>


                            )
                        })
                    }
                </div>
                <div className={styles.divLine}/>
                <div className={styles.divBut}>
                    <button onClick={this.addField} className={styles.button}>Neue Gruppe</button>
                </div>

            </div>
        );
    }

    handleSubmit = (e) => {
        e.preventDefault()
    }

    handleRemove(index) {
        this.state.form.splice(index, 1)
        this.setState({form: this.state.form})
    }

    handleChange = (e) => {
        this.state.form.setState(e.target.value)
        //this.state.form = e.target.value
        this.setState({singleState: this.state.form})
    }

}


export default FormCollap;