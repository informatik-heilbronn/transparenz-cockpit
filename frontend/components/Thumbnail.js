import styles from '../styles/Thumbnail.module.css'
import Link from 'next/link'

/**
 * The Thumbnail Component
 * @param props
 * @returns {JSX.Element}
 * @constructor
 */
export default function Thumbnail (props) {
  return (
    <Link href={'/steckbrief/' + props.id}>
      <div className={styles.div} style={{ backgroundColor: props.color }}>
        <label>{props.id}</label><label className={styles.label}>{props.title}</label>
      </div>
    </Link>
  )
}
