import { signIn } from 'next-auth/client'
import styles from '../styles/Login.module.css'
import React from 'react'

/**
 * The AccessDenied Page Component
 * @returns {JSX.Element}
 * @constructor
 */
export default function AccessDenied () {
  return (
    <div className={styles.div1}>
      <div className={styles.div2}>
        <div className={styles.main}>
          <h1 className={styles.h}>Zugriff verweigert</h1>
          <button onClick={() => signIn()} className={styles.button}>Anmelden</button>
          <a>Melden sie sich an, um auf diese Seite zuzugreifen.</a>
        </div>
      </div>
    </div>
  )
}
