import { useState } from 'react'
import styles from '../styles/Collapsible.module.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAngleDown, faChevronCircleDown } from '@fortawesome/free-solid-svg-icons'

/**
 * The Collapsible Component
 * @param props
 * @returns {JSX.Element}
 * @constructor
 */
export default function Collapsible (props) {
  const [isShowing, setIsShowing] = useState(true)

  const toggle = () => {
    setIsShowing(!isShowing)
  }

  return (
    <div className={styles.div}>
      <button
        className={styles.btn}
        onClick={toggle}
        type='button'
      >
        <div className={styles.label}>{props.label}</div>
        <FontAwesomeIcon icon={faChevronCircleDown} className={styles.icons} style={{ transform: isShowing ? 'rotate(180deg)' : 'none' }} />
      </button>
      <div
        style={{ display: isShowing ? 'block' : 'none', padding: '5px' }}
      >
        {props.content}
      </div>
    </div>
  )
}
