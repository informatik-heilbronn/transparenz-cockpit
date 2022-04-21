import Navbar from './Navbar'
import Footer from './Footer'
import styles from '../styles/Layout.module.css'

/**
 * The Layout Component
 * @param children
 * @returns {JSX.Element}
 * @constructor
 */
const Layout = ({ children }) => {
  return (
    <div className={styles.divOut}>
      <Navbar />
      <div className={styles.div}>
        {children}
      </div>
      <Footer />
    </div>
  )
}

export default Layout
