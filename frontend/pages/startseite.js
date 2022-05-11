import Collapsible from '../components/Collapsible'
import styles from '../styles/Startseite.module.css'
import Thumbnail from '../components/Thumbnail'
import React from 'react'
import { useSession } from 'next-auth/client'
import AccessDenied from '../components/accessDenied'
import ServerError from '../components/ServerError'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCircle } from '@fortawesome/free-solid-svg-icons'
//import apiData from '../Api.config.js'
import SingleInput from '../components/SingleInput'
import MultiInput from '../components/MultiInput'

/**
 *
 * @param context
 * @returns {Promise<{props: {allData: any}}>}
 */
   export async function getServerSideProps (context) {
      const res = await fetch(apiData.OverviewGetApi)
      const data = await res.json()
      return {
        props: { allData: data }
      }
 }
/**
 *
 * @param allData
 * @returns {JSX.Element|null}
 * @constructor
 */
function Startseite ({ allData }) {
  
    const allDataProps = Object.getOwnPropertyNames(allData)
    if (allDataProps[2] === 'error' || allDataProps == undefined) {
      return <ServerError />
    }

  const [session, loading] = useSession()
  //if (typeof window !== 'undefined' && loading) return null
  if (!session) {
    return <AccessDenied />
  }

  return (
    <div className={styles.div}>
      <h1>Übersicht</h1>
      <div>
        <FontAwesomeIcon icon={faCircle} className='icons' color='green' /><label className={styles.label}> =
          Akzeptiert
                                                                           </label>
        <FontAwesomeIcon icon={faCircle} className='icons' color='#ffaf00' /><label className={styles.label}> =
          In Verzug
                                                                             </label>
        <FontAwesomeIcon icon={faCircle} className='icons' color='red' /><label className={styles.label}> =
          Kritisch
                                                                         </label>
        <FontAwesomeIcon icon={faCircle} className='icons' color='grey' /><label className={styles.label}> =
          Unfertig
                                                                          </label>
      </div>

      {
                allDataProps.map((data,index) => {
                  return (
                    <Collapsible key={index}
                      label={decode_utf8(data)} content={
                        <div key={index}>
                          {
                                    allData[data].map((data,index) => {
                                      return (
                                        <Thumbnail
                                          id={data.projectNumber} title={decode_utf8(data.projectName)}
                                          color={ColDecide(data)} key={index}
                                        />
                                      )
                                    })
                                }
                        </div>
                        }
                    />
                  )
                })
            }
    </div>
  )

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

  function ColDecide (data) {
    switch (data.groups['D.'].fields['1.'].value[0]) {
      case 'Im Plan':
        return 'rgb(' + 89 + ',' + 87 + ',' + 87 + ')'
      case 'Teilweise kritisch':
        return 'rgb(' + 255 + ',' + 175 + ',' + 0 + ')'
      case 'Kritisch':
        return 'red'
      default:
        return 'rgb(' + 89 + ',' + 87 + ',' + 87 + ')'
    }
  }
}

export default Startseite
