import styles from '../styles/Auswertung.module.css'
import React from 'react'
import { useSession } from 'next-auth/client'
import AccessDenied from '../components/accessDenied'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faChartPie } from '@fortawesome/free-solid-svg-icons'

/**
 * auswertung page
 * @returns {JSX.Element|null}
 * @constructor
 */
export default function Auswertung () {
  const [session, loading] = useSession()

  if (typeof window !== 'undefined' && loading) return null

  if (!session) { return <AccessDenied /> }

  return (
    <div className={styles.div}>
      <FontAwesomeIcon icon={faChartPie} size='10x' className={styles.icon} />
      <div className={styles.divLine} />
      <p>Diese Funktion befindet sich noch in Arbeit. Wir bitten um Ihr Verständnis!</p>
    </div>
  )
}
