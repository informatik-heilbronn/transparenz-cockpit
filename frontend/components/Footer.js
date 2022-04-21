import styles from '../styles/Footer.module.css'

/**
 * The Footer Component
 * @returns {JSX.Element}
 * @constructor
 */
// min-height 100vh für main content, dann fliegt footer nicht mehr
const Footer = () => {
  return (
    <foo className={styles.foo}>
      <footer className={styles.text}>
        <p> 2021 Transparenz Cockpit by Team 6 HHN</p>
        <a className={styles.links} href='https://confluence-student.it.hs-heilbronn.de/display/LabSWTransparentCockpit/LabSW_21_WS+TransparenzCockpit'> Informationen bezüglich des Entwicklungsteams und der Entwicklung des Projekts kann man hier<br /> sehen....</a>
      </footer>
    </foo>
  )
}
export default Footer
