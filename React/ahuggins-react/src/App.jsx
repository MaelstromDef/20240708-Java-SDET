// import Button from "./Components/Button"
// import Cat from "./Components/Cat"
// import Dog from "./Components/Dog"

import {Cat, Dog, Home, Reference} from "./Components"

import { useEffect, useState } from "react"
import {BrowserRouter, Routes, Route, Link, Navigate} from 'react-router-dom'
import Navbar from "./Components/Navigation/Navbar"

function App() {
  const url = "";

  useEffect(()=>{
    fetch(url).
      then((data) => data.json())
      .then()
      .catch(err => {alert(err); console.error(err);});
  })

  return (
    <>


      {/* <BrowserRouter>
      <Navbar/>

        <Routes>
          <Route path="/cat" element={<Cat/>}/>
          <Route path="/dog" element={<Dog/>}/>
          <Route path="/" element={<Home/>}/>
          <Route path="*" element={<Navigate to='/'/>}/>
        </Routes>
      </BrowserRouter>
      <Reference/> */}
    </>
  )
}

export default App
