// import styles from "../../styles/Steckbrief.module.css";
import styles from '../../styles/Auswertung.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCompass, faCookieBite, faSave, faTrash, faChartPie } from '@fortawesome/free-solid-svg-icons'
import { useSession } from 'next-auth/client'
import AccessDenied from '../../components/accessDenied'
import FormCollap from '../../components/FormCollap'

/**
 *
 * @returns {JSX.Element}
 */
export default function form () {
  const [session, loading] = useSession()

  if (typeof window !== 'undefined' && loading) return null

  if (!session) { return <AccessDenied /> }
  return (
  // <div className={styles.div}>
  //     <div className={styles.divMid}>
  //         <h1>Steckbrief Template</h1>
  //         <h1 className={styles.h}>
  //             <div className={styles.circle}><FontAwesomeIcon icon={faSave} className={styles.icon}
  //                 // onClick={onSubmit}
  //             /></div>
  //             <div className={styles.circle}><FontAwesomeIcon icon={faTrash} className={styles.icon}
  //                 // onClick={onDelete}
  //             /></div>
  //         </h1>
  //     </div>
  //
  //     <FormCollap/>
  //
  //
  // </div>
    <div className={styles.div}>
      <FontAwesomeIcon icon={faCookieBite} size='10x' className={styles.icon} />
      <div className={styles.divLine} />
      <p>Diese Funktion befindet sich noch in Arbeit. Wir bitten um Ihr Verständnis!</p>
    </div>
  )
}
