import Link from 'next/link'
import Image from 'next/image'
import { useRouter } from 'next/router'
import styles from '../styles/Navbar.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUserCircle, faCompass } from '@fortawesome/free-regular-svg-icons'
import { faSearch } from '@fortawesome/free-solid-svg-icons'
import { signIn, signOut, useSession, getSession } from 'next-auth/client'
import React from 'react'
import Swal from 'sweetalert2'

/**
 * The top navigation bar Component
 * @param props
 * @returns {JSX.Element}
 * @constructor
 */
const Navbar = (props) => {
  const router = useRouter()
  const [session, loading] = useSession()
  return (
    <nav className={styles.nav}>
      <div className={styles.logoImage}>
        <Image
          src='/logo.png'
          alt='Stadt Heilbronn'
          width={52}
          height={52}
        />
      </div>
      <div className={styles.vl} />
      <div className={styles.leftLinks}>
        <Link href='/startseite'><a
          className={router.pathname == '/startseite' ? (styles.selected) : (styles.links)}
                                 >Startseite
        </a>
        </Link>
        <Link href='/auswertung'><a
          className={router.pathname == '/auswertung' ? (styles.selected) : (styles.links)}
                                 > Auswertung
        </a>
        </Link>
        <Link href='/steckbrief/erstellen'><a
          className={router.pathname == '/steckbrief/erstellen' ? (styles.selected) : (styles.links)}
                                           >Neue
          Steckbriefe
                                           </a>
        </Link>
        <Link href='/steckbrief/form'><a
          className={router.pathname == '/steckbrief/form' ? (styles.selected) : (styles.links)}
                                      >Neue
          Templates
                                      </a>
        </Link>

      </div>
      <div className={styles.vl} />
      <div className={styles.rightLinks}>

        <label>
          {!session &&
            <button onClick={() => signIn()} className={styles.button}>Anmelden</button>}
          {session &&
            <button
              className='nav-link' onClick={() => {
                Swal.fire({
                  title: 'Bestätigung',
                  text: 'möchten Sie sich wirklich abmelden?',
                  icon: 'warning',
                  showCancelButton: true,
                  confirmButtonColor: '#5ad630',
                  cancelButtonColor: '#d33',
                  confirmButtonText: 'Ja',
                  cancelButtonText: 'Abbrechen'
                }).then((result) => {
                  if (result.isConfirmed) {
                    signOut()
                  }
                })
              }}
            >
              Abmelden
            </button>}
        </label>

        <Link href=''><a className={styles.links}><FontAwesomeIcon
          icon={faSearch}
          className='icons'
                                                  />
        </a>
        </Link>
        <Link href='/account'><a className={styles.links}><FontAwesomeIcon
          icon={faUserCircle}
          className='icons'
                                                          />
        </a>
        </Link>
      </div>
    </nav>
  )
}
export default Navbar
