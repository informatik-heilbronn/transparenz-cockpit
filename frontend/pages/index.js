import React from 'react'
import { signIn, useSession, signOut } from 'next-auth/client'
import AccessDenied from '../components/accessDenied'

import styles from '../styles/Login.module.css'
import Image from 'next/image'

/**
 *
 * @returns {JSX.Element|null}
 * @constructor
 */
export default function Home () {
  const [session, loading] = useSession()
  if (typeof window !== 'undefined' && loading) return null
  if (!session) {
    return <AccessDenied />
  }
  return (
    <div className={styles.div1}>
      <div className={styles.div2}>
        <div className={styles.main}>
          <h1 className={styles.h}>Protected Page</h1>
          <p><strong>angemeldet als {session.user.name}</strong></p>
          <button onClick={signOut} className={styles.button}>Abmelden</button>
        </div>
      </div>
    </div>
  )
}
