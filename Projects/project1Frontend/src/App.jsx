import { createContext, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

const User = createContext();

function App() {
  const [user, setUser] = useState({
    id: null,
    companyName: null,
    password: null,
  })

  return (
    <>
      
    </>
  )
}

export default App
