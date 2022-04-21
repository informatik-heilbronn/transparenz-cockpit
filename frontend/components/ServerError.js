import styles from '../styles/Login.module.css'
import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCookieBite, faServer } from '@fortawesome/free-solid-svg-icons'
import Swal from 'sweetalert2'

/**
 * The AccessDenied Page Component
 * @returns {JSX.Element}
 * @constructor
 */
export default function AccessDenied () {
  Swal.fire({
    position: 'top-end',
    icon: 'error',
    width: 200,
    height: 200,
    showConfirmButton: false,
    timer: 1500
  })
  return (
    <div className={styles.div1}>
      <div className={styles.div2}>
        <div className={styles.main}>
          <FontAwesomeIcon icon={faServer} size='10x' className={styles.icon} />
          <h1 className={styles.h}>Server Fehler</h1>
          <a>bitte versuchen sie es zu einem späteren zeitpunkt erneut.</a>
        </div>
      </div>
    </div>
  )
}
