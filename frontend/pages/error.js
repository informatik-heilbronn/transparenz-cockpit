import styles from '../styles/Login.module.css'
import Link from 'next/link'

/**
 * error page
 * @returns {JSX.Element}
 * @constructor
 */
export default function Error () {
  return (
    <div className={styles.div1}>
      <div className={styles.div2}>

        <div className={styles.main}>
          <h1>Falscher Username oder Passwort.</h1>
          <Link href='/login'>
            <a><label className='error link'>Versuchen sie es erneut.</label></a>
          </Link>
        </div>
      </div>
    </div>
  )
}
