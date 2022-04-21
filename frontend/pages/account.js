import React from 'react'
import { useSession } from 'next-auth/client'
import AccessDenied from '../components/accessDenied'

import Image from 'next/image'
import styles from '../styles/Account.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faEdit } from '@fortawesome/free-solid-svg-icons'
import Link from 'next/link'

/**
 * Main account page
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
          <h2>Account Informationen
            <Link href='/account/edit'>
              <a>
                <button type='button' className={styles.ibtn}><FontAwesomeIcon
                  icon={faEdit} className={styles.icon}
                                                              />
                </button>
              </a>
            </Link>
          </h2>

          <div className={styles.divrightB}>
            <p>Nutzername</p>
            <label className={styles.label} contentEditable>nutzername</label>
            <p>E-Mail</p>
            <label className={styles.label}>email</label>
            <p>Sprache</p>
            <label className={styles.label}>sprache</label>
          </div>
        </div>
      </div>
    </div>
  )
};
