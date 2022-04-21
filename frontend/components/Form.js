import React, {Component, useState} from 'react';
import SingleInput from './SingleInput';
import MultiInput from './MultiInput';
import dataType from '../enums/DataType';
import styles from '../styles/Form.module.css';
import SingleSelect from "./SingleSelect";
import MultiSelect from "./MultiSelect";
import Swal from 'sweetalert2'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faTimesCircle} from "@fortawesome/free-regular-svg-icons";
import {faTrash} from "@fortawesome/free-solid-svg-icons";

/**
 * The Form Component
 */
class Form extends Component {
    state = {
        form: []
    }

    addField = async (e) => {
        const inputOptions = new Promise((resolve) => {
            setTimeout(() => {
                resolve({
                    'SingleInput': 'Single Input',
                    'SingleSelect': 'Single Select',
                    'MultiInput': 'Multi Input',
                    'MultiSelect': 'Multi Select',
                })
            }, 500)
        })

        const {value: inputtype} = await Swal.fire({
            title: 'Feldtyp auswählen',
            input: 'radio',
            width: 700,
            inputOptions: inputOptions,
            confirmButtonText: "Weiter",
            confirmButtonColor: '#25ce0c',
            showCancelButton: true,
            cancelButtonColor: "#940909",
            cancelButtonText: "Abbrechen",
            inputValidator: (value) => {
                if (!value) {
                    return 'bitte auswählen!'
                }
            }
        })

        if (inputtype) {
            const {value: titelText} = await Swal.fire({
                title: 'Titel des Feldes',
                input: 'text',
                inputPlaceholder: 'Text eingeben',
                confirmButtonText: "Weiter",
                showCancelButton: true,
                cancelButtonColor: "#940909",
                cancelButtonText: "Abbrechen",
                width: 700,
                confirmButtonColor: '#25ce0c',
                inputValidator: (value) => {
                    if (!value) {
                        return 'bitte auswählen!'
                    } else {
                        this.setState((prevState) => ({
                            form: [...prevState.form, {
                                label: "",
                                input: "",
                                inputType: (inputtype),
                                inputTitle: (value)
                            }],
                        }));
                    }
                }
            })
        }


    }

    render() {
        let {form} = this.state
        const components = {
            MultiSelect: MultiSelect,
            SingleSelect: SingleSelect,
            MultiInput: MultiInput,
            SingleInput: SingleInput,
        };
        let SpecificComp = components.SingleInput;
        return (
            <div>
                <div className={styles.divOut}>
                    {
                        form.map((val, idx) => {
                            let labelID = `label-${idx}`, InputID = `input-${idx}`
                            switch (val.inputType) {
                                case "SingleInput":
                                    SpecificComp = components.SingleInput
                                    break;
                                case "SingleSelect":
                                    SpecificComp = components.SingleSelect
                                    break;
                                case "MultiInput":
                                    SpecificComp = components.MultiInput
                                    break;
                                case "MultiSelect":
                                    SpecificComp = components.MultiSelect
                                    break;
                                default:
                                    // Run code
                                    break;
                            }
                            ;
                            return (
                                <div key={idx} className={styles.divDyn}>
                                    <SpecificComp label={val.inputTitle}
                                                  disabled={false}
                                                  type={dataType.STRING}
                                                  name="freifeld"
                                                  required={false}/>
                                    <div>
                                        <button className={styles.buttonsIconSmall}
                                                onClick={(e) => this.handleRemove(idx)}>
                                            <FontAwesomeIcon
                                                icon={faTrash}/></button>
                                    </div>
                                </div>


                            )
                        })
                    }
                </div>
                <div className={styles.divBut}>
                    <button onClick={this.addField} className={styles.button}>Neues Feld</button>
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
        this.state.form.useState(e.target.value)
        //this.state.form = e.target.value
        this.setState({singleState: this.state.form})
    }


}


export default Form;