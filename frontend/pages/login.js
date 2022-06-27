import styles from '../styles/Login.module.css'
import Image from 'next/image'
import { getCsrfToken } from 'next-auth/client'
import SingleInput from '../components/SingleInput'

/**
 *
 * @param context
 * @returns {Promise<{csrfToken: string | null}>}
 */
SignIn.getInitialProps = async (context) => {
  return {
    csrfToken: await getCsrfToken(context)
  }
}
/**
 *
 * @param csrfToken
 * @returns {JSX.Element}
 * @constructor
 */
export default function SignIn ({ csrfToken }) {
  return (
    <div className={styles.div1}>
      <div className={styles.div2}>
        <div className={styles.img}>
          <Image
            src='/Unknown_person.jpg'
            alt='unknown'
            width={100}
            height={100}
          />
        </div>

        <form method='post' action='/api/auth/callback/credentials' className={styles.main}>
          <input name='csrfToken' type='hidden' defaultValue={csrfToken} />
          <div className={styles.labelDiv}>
            <input name='username' type='text' className={styles.inputFeld} placeholder="Benutzername" />
          </div>
          <div className={styles.labelDiv}>
            <input name='password' type='password' className={styles.inputFeld} placeholder="Passwort" />
          </div>
          <button className={styles.button} type='submit'>Anmelden</button>
        </form>

      </div>
    </div>
  )
}
