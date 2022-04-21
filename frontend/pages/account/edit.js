import React from 'react'
import { useSession } from 'next-auth/client'
import AccessDenied from '../../components/accessDenied'
import Image from 'next/image'
import styles from '../../styles/Account.module.css'
import Link from 'next/link'

/**
 * Edit account page
 * @returns {JSX.Element|null}
 * @constructor
 */
export default function Account () {
  const clickEdit = async (event) => {
    alert('test test test')
  }

  const [session, loading] = useSession()

  if (typeof window !== 'undefined' && loading) return null

  if (!session) { return <AccessDenied /> }

  return (
    <div>
      <div className={styles.divouter}>

        <div className={styles.divleftA}>
          <h1>Mein Account</h1>
          <div className={styles.divleftB}>
            <Image
              src='/Unknown_person.jpg'
              alt='unknown'
              width={100}
              height={100}
              className={styles.img}
            />
            <h2><label className={styles.label}>Vorname Nachname</label></h2>
            <p>Beigetreten</p>
            <p><label className={styles.label}>datum</label></p>
            <p>Letzte Anmeldung</p>
            <p><label className={styles.label}>datum</label></p>
            <p>Rolle</p>
            <p><label className={styles.label}>rolle</label></p>
          </div>
        </div>

        <div className={styles.divrightA}>
          <h2>Account Informationen</h2>

          <div className={styles.divrightB}>
            <p>Nutzername</p>
            <input type='text' id='nutzername' name='nutzername' placeholder='nutzername' />
            <p>E-Mail</p>
            <input type='text' id='email' name='email' placeholder='email' />
            <p>Sprache</p>
            <input type='text' id='sprache' name='sprache' placeholder='sprache' />
            <p>
              <Link href='/account'>
                <a>
                  <button className={styles.button} type='submit'>
                    Änderungen Speichern
                                    </button>
                </a>
              </Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  )
};
