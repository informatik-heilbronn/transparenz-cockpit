import styles from '../../styles/Steckbrief.module.css'
import Collapsible from '../../components/Collapsible'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPen, faSave, faTrash } from '@fortawesome/free-solid-svg-icons'
import SingleInput from '../../components/SingleInput'
import SingleSelect from '../../components/SingleSelect'
import MultiSelect from '../../components/MultiSelect'
import dataType from '../../enums/DataType'
import MultiInput from '../../components/MultiInput'
import React from 'react'
import { signOut, useSession } from 'next-auth/client'
import AccessDenied from '../../components/accessDenied'
import Swal from 'sweetalert2'
import ServerError from '../../components/ServerError'
import apiData from '../../Api.config.js'

/**
 *
 * @returns {Promise<{paths: *[], fallback: boolean}>}
 */
export async function getStaticPaths () {
  const res = await fetch(apiData.OverviewGetApi)
  const data = await res.json()
  const dataProps = Object.getOwnPropertyNames(await data)
  let paths = []
  const pathsArr = []
  dataProps.map((dataIn) => {
    {
      paths = data[dataIn].map((dataIn2) => {
        pathsArr.push({ params: { view: dataIn2.projectNumber.toString() } })
      })
    }
  })
  return {
    paths: pathsArr,
    fallback: false
  }
}

export async function Post (jsonBody, id) {
  const res = await fetch(apiData.ReportUpdateApi + '/' + id, {
    mode: 'cors',
    method: 'POST',
    cache: 'no-cache',
    body: JSON.stringify(jsonBody),
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    Swal.fire({
      position: 'top-end',
      icon: 'success',
      width: 200,
      height: 200,
      showConfirmButton: false,
      timer: 1500
    }).then(() => {
      location.href = '/startseite'
      console.log(response)
    }
    )
  })
    .catch(err => {
      Swal.fire({
        position: 'top-end',
        icon: 'error',
        width: 200,
        height: 200,
        showConfirmButton: false,
        timer: 1500
      })
      console.log(err)
    })
}

export async function getDefaultTemp () { // load default template ID
  let tempId
  const res = await fetch(apiData.TemplateDefaultGetApi)
  const data = await res.json()
  try {
    tempId = data.templateId.toString()
    return tempId
  } catch (e) {
    console.log(e)
  }
}

/**
 *
 * @param context
 * @returns {Promise<{props: {allData: any}}>}
 */
export async function getStaticProps (context) {
  const id = await context.params.view
  const res = await fetch(apiData.ReportGetApi + '/' + id)
  const res2 = await fetch(apiData.TemplateGetApi + '/' + await getDefaultTemp())
  const data = await res.json()
  const data2 = await res2.json()
  return {
    props: { allData: data, allDataTmp: data2 }
  }
}

/**
 *
 * @param event
 * @returns {Promise<void>}
 */
const onSubmit = async (body, id) => {
  await Post(body, id)
}
/**
 *
 * @param event
 * @returns {Promise<void>}
 */
const onDelete = async (data) => {
  Swal.fire({
    title: 'Bestätigung',
    text: 'möchten Sie den Steckbrief wirklich löschen? der Prozess ist unwiderruflich!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#5ad630',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Ja',
    cancelButtonText: 'Abbrechen'
  }).then((result) => {
    if (result.isConfirmed) {
      // TODO
    }
  })
}

/**
 *
 * @param allData
 * @returns {JSX.Element|null}
 * @constructor
 */
function HomePage ({ allData, allDataTmp }) {
  const allDataProps = Object.getOwnPropertyNames(allData)
  const allDataProps2 = Object.getOwnPropertyNames(allDataTmp)
  if (allDataProps[2] === 'error' || allDataProps2[2] === 'error') {
    return <ServerError />
  }
  const [session, loading] = useSession()
  const lodashClonedeep = require('lodash.clonedeep')
  const allDataTemp = lodashClonedeep(allDataTmp)
  const groups = Object.values(allData.groups)
  const components = {
    SELECT_MULTI_INPUT: MultiSelect,
    SELECT_SINGLE_INPUT: SingleSelect,
    MULTI_INPUT: MultiInput,
    SINGLE_INPUT: SingleInput
  }
  let SpecificComp = components.SINGLE_INPUT
  if (typeof window !== 'undefined' && loading) return null
  if (!session) {
    return <AccessDenied />
  }
  return (
    <div className={styles.div}>
      <div className={styles.divMid}>
        <h1>{allData.projectName}</h1>
        <h1 className={styles.h}>
          <div className={styles.circle}><FontAwesomeIcon
            icon={faSave} className={styles.icon}
            onClick={fillUp}
                                         />
          </div>
          {/* <div className={styles.circle}><FontAwesomeIcon icon={faTrash} className={styles.icon} */}
          {/*                                                onClick={() => { */}
          {/*                                                    onDelete(allDataTmp) */}
          {/*                                                }}/></div> */}

        </h1>
      </div>

      {
                groups.map((dataOut,index) => {
                  const allDataProps = Object.getOwnPropertyNames(dataOut.fields)
                  return (
                    <Collapsible key={index}
                      label={dataOut.letter + ' ' + dataOut.name} content={
                            allDataProps.map((dataIn,index) => {
                              const dataDynam = dataOut.fields[dataIn]
                              SpecificComp = components[dataDynam.inputType]
                              return (
                                <SpecificComp
                                  key={index}
                                  label={LabDecide(dataDynam.number + '| ' + dataDynam.name, ReqDecide(dataDynam), DisDecide(dataDynam))}
                                  options={OptionDecide(dataDynam, dataOut.letter, dataDynam.number)}
                                        // value= {ValueDecide(dataDynam.inputType, dataDynam.value,dataDynam.values, dataDynam.type)}
                                  defaultValue={ValueDecide(dataDynam.inputType, dataDynam.value, dataDynam.values, dataDynam.type)}
                                  type={dataType[dataDynam.type]}
                                  name={dataOut.letter + ' ' + dataDynam.number}
                                  required={ReqDecide(dataDynam)}
                                  disabled={DisDecide(dataDynam)}
                                />
                              )
                            })
                        }
                    />
                  )
                })
            }
      <div className={styles.divRight}>
        <button
          onClick={fillUp}
          className={styles.button}
        ><a className={styles.template}>Speichern</a>
        </button>
      </div>
    </div>
  )

  /**
     * 
     * @param inputType
     * @param value
     * @returns {*[]|*}
     * @constructor
     */
  function ValueDecide (inputType, value, values, type) {
    const valArr = []
    switch (inputType) {
      case 'SELECT_MULTI_INPUT':
        if (typeof values !== 'undefined' && values.length > -1) {
          values.map((data) => {
            valArr.push(
              Object.create({ value: data, label: data })
            )
          })
          return valArr
        }
        break
      case 'SELECT_SINGLE_INPUT':
        if (typeof value !== 'undefined' && value.length > -1) {
          value.map((data) => {
            valArr.push(
              Object.create({ value: data, label: data })
            )
          })
          return valArr
        }
        break
      case 'MULTI_INPUT':
        if (typeof values !== 'undefined' && values.length > -1) {
          values.map((data) => {
            valArr.push(
              { label: data }
            )
          })
          return valArr
        }
        break
      case 'SINGLE_INPUT':
        if (type === 'DATE' && value) {
          const valueDate = new Date(Date.parse(value))
          if(isValidDate(valueDate)){
            return valueDate.toISOString
          } else{
            return "invalid date"
          }
          
          
        }
        return value
        break
      default:
        break
    }
  }

  function isValidDate(d) {
    return d instanceof Date && !isNaN(d);
  }

  /** manually changing letters ß,§
     * decode utf8 for (äüö etc...)
     * @param string
     * @returns {string}
     */
  function decode_utf8 (string) {
    try {
      if (string.includes('ÃŸ')) {
        return string.replace('ÃŸ', 'ß')
      }
      if (string.includes('§')) {
        return string
      }
      return decodeURIComponent(escape(string))
    } catch (e) {
      console.log(e)
    }
  }

  function encode_utf8 (string) {
    try {
      if (string.includes('ß')) {
        return string.replace('ß', 'ÃŸ')
      }
      if (string.includes('§')) {
        return string
      }
      return unescape(encodeURIComponent(string))
    } catch (e) {
      console.log(e)
    }
  }

  //Ausfüllen des Formulars und abschicken an das Backend
  async function fillUp () {
    let inputs, index, name, id, lastType, value
    let arrTmp = []
    try {
      inputs = document.getElementsByTagName('input')
      console.log(inputs)
      for (index = 0; index < inputs.length; ++index) {
        try {
          if (inputs[index].name) {
            name = inputs[index].name.split(' ')[0]
            id = inputs[index].name.split(' ')[1]
            switch (allDataTmp.groups[name].fields[id].type) {
              case 'INTEGER':
                value = Number(inputs[index].value)
                break
              case 'DATE':
                const dateObj = new Date(Date.parse(inputs[index].value))
                const month = dateObj.getUTCMonth() + 1 // months from 1-12
                const day = dateObj.getUTCDate()
                const year = dateObj.getUTCFullYear()
                const newdate = day + '.' + month + '.' + year
                value = newdate
                break
              default:
                value = inputs[index].value
                break
            }
            if (
              document.getElementsByName(inputs[index].name)[0].getAttribute('data-required') === 'true' && !document.getElementsByName(inputs[index].name)[0].textContent.includes('Autogeneriert') &&
                            !value
            ) {
              document.getElementsByName(inputs[index].name)[0].style = 'color: red'
              document.getElementsByName(inputs[index].name)[0].style.fontWeight = '900'
              await Swal.fire({
                icon: 'error',
                title: 'Fehler',
                text: 'bitte alle felder ausfüllen!'
              })
              break
            }
            if (allDataTmp.groups[name].fields[id].inputType === 'SINGLE_INPUT') {
              if (!value) {
                delete allDataTmp.groups[name].fields[id].value
              } else {
                allDataTmp.groups[name].fields[id].value = value
              }
            }
            if (allDataTmp.groups[name].fields[id].inputType === 'MULTI_INPUT') {
              if (lastType !== 'MULTI_INPUT') {
                arrTmp = []
                arrTmp.push(value)
              } else {
                arrTmp.push(value)
                allDataTmp.groups[name].fields[id].values = arrTmp
              }
            }
            if (allDataTmp.groups[name].fields[id].inputType === 'SELECT_SINGLE_INPUT') {
              if (!value) {
                delete allDataTmp.groups[name].fields[id].value
              } else {
                allDataTmp.groups[name].fields[id].value = value
              }
              delete allDataTmp.groups[name].fields[id].allowedValues
            }
            if (allDataTmp.groups[name].fields[id].inputType === 'SELECT_MULTI_INPUT') {
              if (lastType !== 'SELECT_MULTI_INPUT') {
                arrTmp = []
                arrTmp.push(value)
              } else {
                arrTmp.push(value)
                allDataTmp.groups[name].fields[id].values = arrTmp
              }
              delete allDataTmp.groups[name].fields[id].allowedValues
            }
            document.getElementsByName(inputs[index].name)[0].style = 'color: black'
            document.getElementsByName(inputs[index].name)[0].style.fontWeight = '500'
            lastType = allDataTmp.groups[name].fields[id].inputType
          }
        } catch (e) {
          console.log(e)
        }
      }
      if (index === inputs.length) {
        Swal.fire({
          title: 'Bestätigung',
          text: 'möchten Sie die änderungen wirklich Speichern! der Prozess ist unwiderruflich',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#5ad630',
          cancelButtonColor: '#d33',
          confirmButtonText: 'Ja',
          cancelButtonText: 'Abbrechen'
        }).then((result) => {
          if (result.isConfirmed) {
            onSubmit(allDataTmp, allDataTmp.groups['A.'].fields['6.'].value).then(r => {
              console.log(r)
            })
          }
        })
      }
    } catch (e) {
      console.log(e)
    }
  }

  function OptionDecide (data, grId, feId) {
    const optArr = []
    let allowedValuesMap
    if (data.inputType.includes('SELECT')) {
      allowedValuesMap = allDataTemp.groups[grId].fields[feId].allowedValues
      try {
        allowedValuesMap.map(data => {
          optArr.push({ value: data, label: data })
        })
      } catch (e) {
        console.log(e)
      }
      return optArr
    }
  }

  function ReqDecide (data) {
    if (data.modifiers.includes('REQUIRED')) {
      return true
    } else {
      return false
    }
  }

  function DisDecide (data) {
    if (data.modifiers.includes('AUTOGENERATED')) {
      return true
    } else {
      return false
    }
  }

  function LabDecide (data, bool, boolAuto) {
    if (bool && boolAuto) {
      return data + '*' + '(Autogeneriert)'
    } else if (bool) {
      return data + '*'
    } else {
      return data
    }
  }
}

export default HomePage
